package com.mattlloyd.opengl.VBO

import com.mattlloyd.opengl.{Renderable, Initable}


trait VBOObject {
    self: Initable[_, _] with Renderable[_, _] =>

    //val vbo = new VBO()

    preInit.add("VBOInit") {
        case _ => //vbo._init
    }



}
