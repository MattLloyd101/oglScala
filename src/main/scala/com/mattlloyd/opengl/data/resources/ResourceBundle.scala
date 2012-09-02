package com.mattlloyd.opengl.data.resources

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 27/08/12
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
trait ResourceBundle extends Resource[Unit] {

    val id:String = "_BUNDLE"

    def resources:Set[Resource[_]]

    def load {
        resources foreach { _.load }
    }
}
