package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}
import org.lwjgl.opengl.ARBShaderObjects
import ARBShaderObjects._
import java.nio.IntBuffer
import org.lwjgl.BufferUtils

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */

object IntUniform {
    def apply(parentShader: ShaderProgram, name: String, defaultValue:Int) = new IntUniform(parentShader, name, defaultValue)
}
class IntUniform(parentShader: ShaderProgram, name: String, defaultValue:Int) extends Uniform[IntBuffer, Int](parentShader, name, defaultValue) {
    def flush {
        if(buffUpdated) {
            __internalBuff.clear()
            __internalBuff.put(value)
        }
        __internalBuff.flip()
        glUniform1ARB(id, __internalBuff)
    }

    var __internalBuff = BufferUtils.createIntBuffer(1)

}
