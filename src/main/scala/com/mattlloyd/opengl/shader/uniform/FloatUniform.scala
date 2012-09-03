package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import org.lwjgl.opengl.ARBShaderObjects._
import java.nio.{FloatBuffer, IntBuffer}
import org.lwjgl.BufferUtils


object FloatUniform {
    def apply(parentShader: ShaderProgram, name: String, defaultValue:Float) = new FloatUniform(parentShader, name, defaultValue)
}
class FloatUniform(parentShader: ShaderProgram, name: String, defaultValue:Float) extends Uniform[FloatBuffer, Float](parentShader, name, defaultValue) {
    def flush {
        if(buffUpdated) {
            __internalBuff.clear()
            __internalBuff.put(value)
        }
        __internalBuff.flip()
        glUniform1ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(1)
}

