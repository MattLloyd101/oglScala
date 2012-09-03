package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded, Shader}
import java.nio.{Buffer, IntBuffer, FloatBuffer}
import org.lwjgl.opengl.ARBShaderObjects._



abstract class Tuple4Uniform[Internal <: Buffer, T](shader:ShaderProgram, name:String, defaultValue: (T, T, T, T)) extends Uniform[Internal, (T, T, T, T)](shader, name, defaultValue) {

    class SubProperty1 {
        def update (v:T) { set( (v, value._2, value._3, value._4) ) }
        def apply() = value._1
    }

    class SubProperty2 {
        def update (v:T) { set( (value._1, v, value._3, value._4) ) }
        def apply() = value._2
    }

    class SubProperty3 {
        def update (v:T) { set( (value._1, value._2, v, value._4) ) }
        def apply() = value._3
    }

    class SubProperty4 {
        def update (v:T) { set( (value._1, value._2, value._3, v) ) }
        def apply() = value._4
    }

}
