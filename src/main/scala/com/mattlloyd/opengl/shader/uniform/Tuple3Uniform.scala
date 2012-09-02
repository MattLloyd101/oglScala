package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded, Shader}
import org.lwjgl.opengl.ARBShaderObjects._
import java.nio.Buffer

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 29/07/12
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */

abstract class Tuple3Uniform[Internal <: Buffer, T](shader:ShaderProgram, name:String, defaultValue:(T, T, T)) extends Uniform[Internal, (T, T, T)](shader, name, defaultValue:(T, T, T)) {

    class SubProperty1 {
        def update(v:T) { set( (v, value._2, value._3) ) }
        def apply():T = value._1
    }

    class SubProperty2 {
        def update(v:T) { set( (value._1, v, value._3) ) }
        def apply():T = value._2
    }

    class SubProperty3 {
        def update(v:T) { set( (value._1, value._2, v) ) }
        def apply():T = value._3
    }

}
