package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal


trait Updatable[Arg, T] {

    type updateType = Arg
    val preUpdate = Signal[Arg]()
    val postUpdate = Signal[T]()

    protected def _update(arg: Arg): T

    def update(arg: Arg) = {
        preUpdate(arg)
        val ret = _update(arg)
        postUpdate(ret)
        ret
    }

}
