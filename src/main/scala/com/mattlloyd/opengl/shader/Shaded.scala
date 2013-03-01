package com.mattlloyd.opengl.shader

import com.mattlloyd.opengl.Renderable



trait Shaded extends ShaderParameters {
    self: Renderable[_, _] =>

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
