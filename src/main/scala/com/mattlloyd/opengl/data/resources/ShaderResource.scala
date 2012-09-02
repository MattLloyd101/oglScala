package com.mattlloyd.opengl.data.resources

import java.io.File
import io.Source

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 27/08/12
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
object ShaderResource extends ResourceConverter[File, String] {
    override def convert(obj: File) = new ShaderResource(obj)

    override def canConvert(obj: File) = obj.getName.split("\\.").last.toLowerCase == "glsl"
}

class ShaderResource(shader:File) extends TextFileResource(shader) {

    def processInclude(line:String):String = {
        Source.fromFile(line.substring(line.indexOf("\"")+ 1, line.lastIndexOf("\""))).getLines().mkString("\r\n")
    }

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
