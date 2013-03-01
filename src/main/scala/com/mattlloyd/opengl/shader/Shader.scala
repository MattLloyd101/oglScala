package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.{ARBFragmentShader, ARBVertexShader, GL11, ARBShaderObjects}
import GL11._
import org.lwjgl.opengl.ARBShaderObjects._
import java.io.{FileReader, BufferedReader}
import io.Source
import com.mattlloyd.util.signal.Signal
import uniform.Uniform
import com.mattlloyd.opengl.Initable
import com.mattlloyd.opengl.data.resources.{Resource, ShaderResource}



object ShaderType extends Enumeration {
    type ShaderType = Value
    val Vertex = Value("Vertex")
    val TessControl = Value("TessControl")
    val TessEval = Value("TessEval")
    val Geometry = Value("Geometry")
    val Fragment = Value("Fragment")
}
trait Shader extends ShaderParameters with Initable[Unit, Int] {

    val preAttach = Signal[Unit]()
    val postAttach = Signal[Unit]()

    val shaderSourceResource:Resource[String]

    lazy val shaderSource:String = shaderSourceResource.loadAndGet

    val shaderType:ShaderType.ShaderType

    val shaderTypeID:Int

    lazy val shaderId = init()

    def _init(arg:Unit) = {
        glCreateShaderObjectARB(shaderTypeID) match {
            case 0 => throw ShaderError(0, "Unable to create "+shaderType+" shader program at glCreateShaderObjectARB")
            case shaderId => {
                glShaderSourceARB(shaderId, shaderSource)
                glCompileShaderARB(shaderId)
                parami(shaderId, GL_OBJECT_COMPILE_STATUS_ARB) match {
                    case GL_FALSE => throw ShaderError(shaderId, "Unable to compile "+shaderType+" shader at initShader")
                    case _ => shaderId
                }
            }
        }
    }

}
