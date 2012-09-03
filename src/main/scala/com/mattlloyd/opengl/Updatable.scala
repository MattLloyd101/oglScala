package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal


trait Updatable[T] {

    val preUpdate = Signal[Long]()
    val postUpdate = Signal[T]()

    def update(ms:Long):T

    def runUpdate(ms:Long) = {
        preUpdate(ms)
        val ret = update(ms)
        postUpdate(ret)
        ret
    }

}
