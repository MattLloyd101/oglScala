package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 23/08/12
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
trait Initable[T] {

    val preInit = Signal[Unit]()
    val postInit = Signal[T]()

    def init:T

    def runInit = {
        preInit()
        val ret = init
        postInit(ret)
        ret
    }
}
