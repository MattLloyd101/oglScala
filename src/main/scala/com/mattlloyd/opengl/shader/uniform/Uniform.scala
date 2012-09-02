package com.mattlloyd.opengl.shader.uniform

import org.lwjgl.opengl.ARBShaderObjects
import ARBShaderObjects._
import java.nio.Buffer
import com.mattlloyd.opengl.shader.{ShaderProgram, Shaded}

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 29/07/12
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */

abstract class Uniform[Internal <: Buffer, External](parentShader: ShaderProgram, name: String, defaultValue:External) extends AbstractUniform(parentShader, name) {

    var value:External = defaultValue

    protected var __internalBuff:Internal

    protected var buffUpdated:Boolean = true

    def :=(value: External) = set(value)

    def set(newValue: External):Uniform[Internal, External] = {
        value = newValue
        buffUpdated = true
        this
    }

    def setRaw(newValue: Internal):Uniform[Internal, External] = {
        __internalBuff = newValue
        // bypasses the update method if false.
        buffUpdated = false
        this
    }

}

