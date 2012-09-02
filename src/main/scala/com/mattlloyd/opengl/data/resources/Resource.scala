package com.mattlloyd.opengl.data.resources

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 27/08/12
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
trait Resource[T] {

    def id:String

    def load

    def unload

    def safeGet:Option[T]

    def get:T = safeGet.get

    def loadAndGet:T = {
        load
        get
    }

}
