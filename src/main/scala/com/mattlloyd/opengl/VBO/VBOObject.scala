package com.mattlloyd.opengl.VBO

import com.mattlloyd.opengl.{Renderable, Initable}


trait VBOObject {
    self: Initable[_] with Renderable[_] =>

    //val vbo = new VBO()

    preInit.add("VBOInit") {
        case _ => //vbo.init
    }



}
