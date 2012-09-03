package com.mattlloyd.opengl.shader

import com.mattlloyd.util.signal.Signal
import uniform.{AbstractUniform, Uniform}
import org.lwjgl.opengl.ARBShaderObjects._
import scala.Some
import org.lwjgl.opengl.GL11._
import scala.Some
import examples.shader.basic.shader.BasicShader
import java.lang.reflect.Method


class ShaderProgram extends ShaderParameters {

    val preInit = Signal[Unit]()
    val postInit = Signal[Unit]()
    val preLink = Signal[Unit]()
    val postLink = Signal[Unit]()
    val preValidate= Signal[Unit]()
    val postValidate = Signal[Unit]()

    lazy val uniforms:List[AbstractUniform] = (this.getClass.getMethods.filter { f => AbstractUniform.isUniformField(f) }
                                                                                  map { m =>
        println("m> "+m)
        m.invoke(this).asInstanceOf[AbstractUniform] }).toList

    lazy val vertShader:Option[VertexShader] = None

    lazy val fragShader:Option[FragmentShader] = None

    // TODO: This architecture only allows attaching once.
    // If we ever need to un-attach, this will need re-thinking
    lazy val shaderId = {
        preInit()
        val id = initShaders
        postInit()
        id
    }

    def attachShader(programId:Int, shader:Option[Shader]) = shader match {
        case None =>
        case Some(shader) =>
            shader.preAttach()
            // id either works or throws an error
            glAttachObjectARB(programId, shader.shaderId)
            shader.postAttach()
    }


    def validateShader(shaderId:Int) = {
        (parami(shaderId, GL_OBJECT_LINK_STATUS_ARB), parami(shaderId, GL_OBJECT_VALIDATE_STATUS_ARB)) match {
            case (GL_FALSE, GL_FALSE) => throw ShaderError(shaderId, "Unable to link or validate shader at initShader")
            case (GL_FALSE, _) => throw ShaderError(shaderId, "Unable to link shader at initShader")
            case (_, GL_FALSE) => throw ShaderError(shaderId, "Unable to validate shader at initShader")
            case (_, _) => shaderId
        }
    }

    def linkShader(shaderId:Int) {
        preLink()
        glLinkProgramARB(shaderId)
        postLink()
    }

    def initShaders = {
        glCreateProgramObjectARB match {
            case 0 => throw new ShaderError(0, "Unable to create shader program at glCreateProgramObjectARB")
            case shaderId =>
                attachShader(shaderId, vertShader)
                attachShader(shaderId, fragShader)
                linkShader(shaderId)
                validateShader(shaderId)
        }
    }

    def prepareShader(flushUniforms:Boolean) {
        glUseProgramObjectARB(shaderId)

        if(flushUniforms) uniforms foreach { _.flush }
    }

    def cleanupShader {
        // TODO: This might be in-efficient, especially if we are loading in a new program right after...
        glUseProgramObjectARB(0)
    }

}
