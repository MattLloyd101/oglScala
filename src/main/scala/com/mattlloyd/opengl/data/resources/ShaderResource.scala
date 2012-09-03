package com.mattlloyd.opengl.data.resources

import java.io.File
import io.Source


object ShaderResource extends ResourceConverter[File, String] {
    override def convert(obj: File) = new ShaderResource(obj)

    override def canConvert(obj: File) = obj.getName.split("\\.").last.toLowerCase == "glsl"
}

class ShaderResource(shader:File) extends TextFileResource(shader) {

    def processInclude(line:String):String = ResourceManager.getResourceById[String](line.substring(line.indexOf("\"")+ 1, line.lastIndexOf("\""))).loadAndGet

    // OpenGL doesn't allow for #include type includes.
    // Instead were just dumping the code in there by pre-processing the source.
    def processIncludes(s:String):String = {
        s.lines.map { line =>
            if(line.startsWith("#include"))
                processInclude(line)
            else
                line
        }.mkString("\r\n")
    }

    override def load {
        super.load
        text = text.map(processIncludes)
    }
}
