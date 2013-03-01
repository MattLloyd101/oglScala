package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal


trait Renderable[Arg, T] {

    val preRender = Signal[Arg]()
    val postRender = Signal[T]()

    protected def _render(arg: Arg): T

    def render(arg: Arg) = {
        preRender(arg)
        val ret = _render(arg)
        postRender(ret)
        ret
    }

}
