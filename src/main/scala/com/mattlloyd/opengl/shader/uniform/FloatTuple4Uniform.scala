package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.{FloatBuffer, IntBuffer}
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */

class FloatTuple4Uniform(shader:ShaderProgram, name:String, defaultValue:(Float, Float, Float, Float)) extends Tuple4Uniform[FloatBuffer, Float](shader, name, defaultValue) {

    def flush {
        if(buffUpdated) {
            val (fst, snd, trd, fth) = value
            __internalBuff.clear()
            __internalBuff.put(fst)
            __internalBuff.put(snd)
            __internalBuff.put(trd)
            __internalBuff.put(fth)
        }
        __internalBuff.flip()
        glUniform4ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(4)

}


