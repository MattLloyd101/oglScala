package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.{ByteBuffer, IntBuffer}
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils



class IntTuple4Uniform(shader:ShaderProgram, name:String, defaultValue:(Int, Int, Int, Int)) extends Tuple4Uniform[IntBuffer, Int](shader, name, defaultValue) {

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

    var __internalBuff = BufferUtils.createIntBuffer(4)

}
