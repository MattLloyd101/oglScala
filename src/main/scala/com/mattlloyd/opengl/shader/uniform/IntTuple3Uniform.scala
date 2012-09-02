package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.IntBuffer
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 12:43
 * To change this template use File | Settings | File Templates.
 */

class IntTuple3Uniform(shader:ShaderProgram, name:String, defaultValue:(Int, Int, Int)) extends Tuple3Uniform[IntBuffer, Int](shader, name, defaultValue) {

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

    var __internalBuff = BufferUtils.createIntBuffer(3)

}
