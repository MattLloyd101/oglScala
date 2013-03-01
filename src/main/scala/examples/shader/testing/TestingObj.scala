package examples.shader.testing

import com.mattlloyd.opengl.{Updatable, Initable, Renderable}
import com.mattlloyd.opengl.shader.Shaded
import org.lwjgl.opengl.GL11._
import shader.TestingShader

class TestingObj extends Renderable[Unit, Unit] with Initable[Unit, Unit] with Updatable[Unit, Unit] with Shaded {

    lazy val shaderProgram = new TestingShader

    val pos = shaderProgram.position
    val rot = shaderProgram.rotation

    def _init(a:Unit) = {

    }

    def _update(a: Unit) {

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
