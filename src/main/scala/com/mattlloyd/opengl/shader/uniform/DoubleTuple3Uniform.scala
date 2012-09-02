package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.FloatBuffer
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */

class DoubleTuple3Uniform(shader:ShaderProgram, name:String, defaultValue:(Double, Double, Double)) extends Tuple3Uniform[FloatBuffer, Double](shader, name, defaultValue) {

    def flush {
        if(buffUpdated) {
            val (fst, snd, trd) = value
            __internalBuff.clear()
            __internalBuff.put(fst.toFloat)
            __internalBuff.put(snd.toFloat)
            __internalBuff.put(trd.toFloat)
        }
        __internalBuff.flip()
        glUniform3ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(3)

}
