package com.mattlloyd.opengl

import com.mattlloyd.util.signal.Signal

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 23/08/12
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
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
