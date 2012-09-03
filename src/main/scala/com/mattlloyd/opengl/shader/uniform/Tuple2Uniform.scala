package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded, Shader}
import java.nio.{Buffer, IntBuffer, FloatBuffer}




abstract class Tuple2Uniform[Internal <: Buffer, T](shader:ShaderProgram, name:String, defaultValue:(T, T)) extends Uniform[Internal, (T, T)](shader, name, defaultValue) {

    class SubProperty1 {
        def update (v:T) { set( (v, value._2) ) }
        def apply:T = value._1
    }

    class SubProperty2 {
        def update (v:T) { set( (value._1, v) ) }
        def apply:T = value._2
    }

}



