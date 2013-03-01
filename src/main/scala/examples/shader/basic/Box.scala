package examples.shader.basic

import com.mattlloyd.opengl.shader._
import org.lwjgl.opengl.GL11._
import shader.BasicShader
import com.mattlloyd.opengl.VBO.VBOObject
import com.mattlloyd.opengl.{Renderable, Updatable, Initable}
import scala.math._


class Box extends Renderable[Unit, Unit] with Initable[Unit, Unit] with Updatable[Unit, Unit] with Shaded {

    lazy val shaderProgram = new BasicShader

    val pos = shaderProgram.position
    val rot = shaderProgram.rotation

    def _init(a:Unit) = {

    }

    def _update(a:Unit) {

    }

    def _render(a:Unit) {
        glLoadIdentity
        glBegin(GL_QUADS)
        glVertex3f(-1.0f, 1.0f, 0.0f)
        glVertex3f(1.0f, 1.0f, 0.0f)
        glVertex3f(1.0f, -1.0f, 0.0f)
        glVertex3f(-1.0f, -1.0f, 0.0f)
        glEnd
    }
}
