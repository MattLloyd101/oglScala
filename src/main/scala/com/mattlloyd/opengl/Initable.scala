package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal


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
