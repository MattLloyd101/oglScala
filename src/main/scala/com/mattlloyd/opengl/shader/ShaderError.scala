package com.mattlloyd.opengl.shader

import java.nio.{ByteBuffer, IntBuffer}
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.ARBShaderObjects

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 29/07/12
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */

object ShaderError {

    def apply(shaderId:Int, msg:String) = new ShaderError(shaderId:Int, msg:String)

    def compileErrorMessage(shaderId:Int, msg:String) = {
        val iVal: IntBuffer = BufferUtils.createIntBuffer(1)

        ARBShaderObjects.glGetObjectParameterARB(shaderId, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB, iVal)

        val length: Int = iVal.get
        if (length > 1) {
            val infoLog: ByteBuffer = BufferUtils.createByteBuffer(length)
            iVal.flip
            ARBShaderObjects.glGetInfoLogARB(shaderId, iVal, infoLog)
            val infoBytes: Array[Byte] = new Array[Byte](length)
            infoLog.get(infoBytes)
            msg + " - [" + new String(infoBytes) + "]"
        }
        else msg
    }
}

class ShaderError(shaderId:Int, errMsg:String) extends Error(ShaderError.compileErrorMessage(shaderId, errMsg))
