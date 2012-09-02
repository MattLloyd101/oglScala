package com.mattlloyd.opengl.data.resources

import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 27/08/12
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
abstract class FileResource[T](file:File) extends Resource[T] {

    def id = file.getPath.replace("\\", "/")

}
