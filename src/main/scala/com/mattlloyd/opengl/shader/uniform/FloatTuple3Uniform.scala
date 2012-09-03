package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.{FloatBuffer, IntBuffer}
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils



class FloatTuple3Uniform(shader:ShaderProgram, name:String, defaultValue:(Float, Float, Float)) extends Tuple3Uniform[FloatBuffer, Float](shader, name, defaultValue) {

    def flush {
        if(buffUpdated) {
            val (fst, snd, trd) = value
            __internalBuff.clear()
            __internalBuff.put(fst)
            __internalBuff.put(snd)
            __internalBuff.put(trd)
        }
        __internalBuff.flip()
        glUniform3ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(3)

}
