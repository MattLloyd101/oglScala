package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 23/08/12
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
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
