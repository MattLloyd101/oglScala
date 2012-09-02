package com.mattlloyd.opengl.VBO

import com.mattlloyd.opengl.{Renderable, Initable}

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 23/08/12
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
trait VBOObject {
    self: Initable[_] with Renderable[_] =>

    //val vbo = new VBO()

    preInit.add("VBOInit") {
        case _ => //vbo.init
    }



}
