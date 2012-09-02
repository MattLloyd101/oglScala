package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.ARBShaderObjects._

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */

trait ShaderParameters {

    val shaderId:Int

    def parami(param:Int):Int = glGetObjectParameteriARB(shaderId, param)
    def paramf(param:Int):Float = glGetObjectParameterfARB(shaderId, param)
    def parami(id:Int, param:Int):Int = glGetObjectParameteriARB(id, param)
    def paramf(id:Int, param:Int):Float = glGetObjectParameterfARB(id, param)
}
