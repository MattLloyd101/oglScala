package com.mattlloyd.opengl.data.resources

import java.io.File
import io.{Codec, Source}

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 27/08/12
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
class TextFileResource(file:File)(implicit codec: Codec) extends FileResource[String](file) {

    protected var text:Option[String] = None

    def safeGet = text

    def load {
        text = Some(Source.fromFile(file)(codec).getLines().mkString("\r\n"))
    }

    def unload {
        text = None
    }

    def canConvert(obj: File) = obj.getName.split("\\.").last.toLowerCase == "txt"

    def convert(obj: File) = new TextFileResource(obj)
}
