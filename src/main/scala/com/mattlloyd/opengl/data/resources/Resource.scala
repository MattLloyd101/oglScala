package com.mattlloyd.opengl.data.resources


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
