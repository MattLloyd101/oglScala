package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.ARBShaderObjects._



trait ShaderParameters {

    val shaderId:Int

    def parami(param:Int):Int = glGetObjectParameteriARB(shaderId, param)
    def paramf(param:Int):Float = glGetObjectParameterfARB(shaderId, param)
    def parami(id:Int, param:Int):Int = glGetObjectParameteriARB(id, param)
    def paramf(id:Int, param:Int):Float = glGetObjectParameterfARB(id, param)
}
