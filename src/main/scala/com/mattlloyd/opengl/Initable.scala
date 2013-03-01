package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal


trait Initable[Arg, T] {

    val preInit = Signal[Arg]()
    val postInit = Signal[T]()

    protected def _init(arg:Arg):T

    def init(arg:Arg) = {
        preInit(arg)
        val ret = _init(arg)
        postInit(ret)
        ret
    }
}
