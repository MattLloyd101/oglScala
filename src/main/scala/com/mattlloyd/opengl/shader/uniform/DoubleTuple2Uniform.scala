package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import java.nio.FloatBuffer
import org.lwjgl.opengl.ARBShaderObjects._
import org.lwjgl.BufferUtils



class DoubleTuple2Uniform(shader:ShaderProgram, name:String, defaultValue:(Double, Double)) extends Tuple2Uniform[FloatBuffer, Double](shader, name, defaultValue) {

    def flush {
        if(buffUpdated) {
            val (fst, snd) = value
            __internalBuff.clear()
            __internalBuff.put(fst.toFloat)
            __internalBuff.put(snd.toFloat)
        }
        __internalBuff.flip()
        glUniform2ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createFloatBuffer(2)

}
