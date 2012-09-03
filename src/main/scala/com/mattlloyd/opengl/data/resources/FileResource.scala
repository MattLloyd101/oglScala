package com.mattlloyd.opengl.data.resources

import java.io.File


abstract class FileResource[T](file:File) extends Resource[T] {

    def id = file.getPath.replace("\\", "/")

}
