package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded, Shader}


object PositionUniform {
    val positionUniformName = "position"
    val defaultPosition = (0., 0., -5.)
    def apply(shader:ShaderProgram, name:String = positionUniformName, defaultValue:(Double, Double, Double) = PositionUniform.defaultPosition) = new PositionUniform(shader, name, defaultValue)
}
class PositionUniform(shader:ShaderProgram, name:String = PositionUniform.positionUniformName, defaultValue:(Double, Double, Double) = PositionUniform.defaultPosition) extends DoubleTuple3Uniform(shader, name, defaultValue) {

    def x_= (value:Double) = {
        _x.update(value)
        _x()
    }

    def x = _x()

    def y_= (value:Double) = {
        _y.update(value)
        _y()
    }

    def y = _y()

    def z_= (value:Double) = {
        _z.update(value)
        _z()
    }

    def z = _z()

    object _x extends SubProperty1
    object _y extends SubProperty2
    object _z extends SubProperty3

}
