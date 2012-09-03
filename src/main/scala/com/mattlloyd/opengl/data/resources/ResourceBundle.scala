package com.mattlloyd.opengl.data.resources


trait ResourceBundle extends Resource[Unit] {

    val id:String = "_BUNDLE"

    def resources:Set[Resource[_]]

    def load {
        resources foreach { _.load }
    }
}
