package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded, Shader}



object RGBAUniform {
    def apply(shader:ShaderProgram, name:String, defaultColour:(Double, Double, Double, Double) = defaultColour) = new RGBAUniform(shader, name, defaultColour)
    //247 G: 148 B: 29
    val defaultColour = (1.0, 1.0, 1.0, 1.0)
}
class RGBAUniform(shader:ShaderProgram, name:String, default:(Double, Double, Double, Double) = RGBAUniform.defaultColour) extends DoubleTuple4Uniform(shader, name, default) {

    def r_= (value:Double) = {
        _r.update(value)
        _r()
    }

    def r = _r()

    def g_= (value:Double) = {
        _g.update(value)
        _g()
    }

    def g = _g()

    def b_= (value:Double) = {
        _b.update(value)
        _b()
    }

    def b = _b()

    def a_= (value:Double) = {
        _a.update(value)
        _a()
    }

    def a = _a()

    object _r extends SubProperty1
    object _g extends SubProperty2
    object _b extends SubProperty3
    object _a extends SubProperty4
}
