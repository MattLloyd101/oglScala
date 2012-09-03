package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.FloatBuffer
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils



class DoubleTuple4Uniform(shader:ShaderProgram, name:String, defaultValue:(Double, Double, Double, Double)) extends Tuple4Uniform[FloatBuffer, Double](shader, name, defaultValue) {

    def flush {
        if(buffUpdated) {
            val (fst, snd, trd, fth) = value
            __internalBuff.clear()
            __internalBuff.put(fst.toFloat)
            __internalBuff.put(snd.toFloat)
            __internalBuff.put(trd.toFloat)
            __internalBuff.put(fth.toFloat)
        }
        __internalBuff.flip()
        glUniform4ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(4)

}
