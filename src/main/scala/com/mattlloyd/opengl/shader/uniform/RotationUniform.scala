package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded, Shader}

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 29/07/12
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */

object RotationUniform {
    val rotationUniformName = "rotation"
    def apply(shader:ShaderProgram, name:String = rotationUniformName, defaultValue:(Double, Double, Double) = (0, 0, 0)) = new RotationUniform(shader, name, defaultValue)
}
class RotationUniform(shader:ShaderProgram, name:String = RotationUniform.rotationUniformName, defaultValue:(Double, Double, Double) = (0, 0, 0)) extends DoubleTuple3Uniform(shader, name, defaultValue) {

    def x_= (value:Double) = {
        _x.update(value)
        _x()
    }

    def x:Double = _x()

    def y_= (value:Double) = {
        _y.update(value)
        _y()
    }

    def y:Double = _y()

    def z_= (value:Double) = {
        _z.update(value)
        _z()
    }

    def z:Double = _z()

    object _x extends SubProperty1
    object _y extends SubProperty2
    object _z extends SubProperty3

}
