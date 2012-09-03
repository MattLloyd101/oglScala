package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import org.lwjgl.opengl.ARBShaderObjects._
import java.nio.{IntBuffer, FloatBuffer}
import org.lwjgl.BufferUtils


object DoubleUniform {
    def apply(parentShader: ShaderProgram, name: String, defaultValue:Int) = new DoubleUniform(parentShader, name, defaultValue)
}
class DoubleUniform(parentShader: ShaderProgram, name: String, defaultValue:Double) extends Uniform[FloatBuffer, Double](parentShader, name, defaultValue) {
    def flush {
        if(buffUpdated) {
            __internalBuff.clear()
            __internalBuff.put(value.toFloat)
        }
        __internalBuff.flip()
        glUniform1ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(1)
}
