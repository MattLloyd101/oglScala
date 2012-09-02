package com.mattlloyd.opengl.shader

import com.mattlloyd.util.signal.Signal
import uniform.Uniform
import io.Source
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.{ARBFragmentShader, ARBVertexShader}
import com.mattlloyd.opengl.{Renderable, Initable}

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */

trait Shaded extends ShaderParameters {
    self: Renderable[_] =>

    val shaderProgram:ShaderProgram

    lazy val shaderId = shaderProgram.shaderId

    preRender.add("ShaderPreRender") {
        case _ => shaderProgram.prepareShader(true)
    }

    postRender.add("ShaderPostRender") {
        case _ => shaderProgram.cleanupShader
    }

    def usingShader[T](body: Int => T):T = usingShader(true)(body)

    def usingShader[T](flushUniforms:Boolean)(body: Int => T):T = {
        shaderProgram.prepareShader(flushUniforms)
        val tmp = body(shaderId)
        shaderProgram.cleanupShader
        tmp
    }
}
