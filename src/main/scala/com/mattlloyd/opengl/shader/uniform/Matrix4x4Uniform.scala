package com.mattlloyd.opengl.shader.uniform

import java.nio.FloatBuffer
import com.mattlloyd.opengl.data.immutable.matrix.Matrix4x4
import com.mattlloyd.opengl.shader.Shaded
import org.lwjgl.opengl.ARBShaderObjects._



//class Matrix4x4Uniform(parentShader: Shaded, name: String, defaultValue:Matrix4x4 = Matrix4x4.identity, var transpose:Boolean = false) extends Uniform[FloatBuffer,Matrix4x4](parentShader, name, defaultValue) {
//
//    protected var __internalBuff = defaultValue.data
//
//    override def set(newValue: Matrix4x4):Matrix4x4Uniform = {
//        value = newValue
//        __internalBuff = newValue.data
//        buffUpdated = true
//        this
//    }
//
//    def flush {
//        glUniformMatrix4ARB(0, transpose, __internalBuff)
//    }
//}
