package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal


trait Renderable[T] {

    val preRender = Signal[Unit]()
    val postRender = Signal[T]()

    def render:T

    def runRender = {
        preRender()
        val ret = render
        postRender(ret)
        ret
    }

}
