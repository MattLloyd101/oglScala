package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.IntBuffer
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils



class IntTuple2Uniform(shader:ShaderProgram, name:String, defaultValue:(Int, Int)) extends Tuple2Uniform[IntBuffer, Int](shader, name, defaultValue) {

    def flush {
        if(buffUpdated) {
            val (fst, snd) = value
            __internalBuff.clear()
            __internalBuff.put(fst)
            __internalBuff.put(snd)
        }
        __internalBuff.flip()
        glUniform2ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createIntBuffer(2)

}

