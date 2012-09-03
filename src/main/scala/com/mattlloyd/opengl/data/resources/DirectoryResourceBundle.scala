package com.mattlloyd.opengl.data.resources

import java.io.File

object DirectoryResourceBundle {
    var fileConverters:Seq[ResourceConverter[File, _]] = ShaderResource :: Nil
}
class DirectoryResourceBundle(pathStr:String, recursive:Boolean, autoAdd:Boolean = true) extends ResourceBundle {

    if(autoAdd) {
        ResourceManager += this
    }

    lazy val path:File = new File(pathStr)

    lazy val files:Set[File] = {
        def recursiveListFiles(f: File): Set[File] = {
            val files = f.listFiles
            (files ++ files.filter(_.isDirectory).flatMap(recursiveListFiles)).toSet
        }

        if(recursive)
            recursiveListFiles(path)
        else
            path.listFiles.filter { f => ! f.isDirectory }.toSet
    }

    lazy val resources = files flatMap { file =>
        DirectoryResourceBundle.fileConverters.find(conv => conv.canConvert(file)).map { _.convert(file) }
    }

    def unload {
        resources foreach { _.unload }
    }

    def safeGet = null
}
