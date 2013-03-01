package projects.mnLibrary.objects

import com.mattlloyd.opengl.{Updatable, Initable, Renderable}
import com.mattlloyd.opengl.shader.Shaded
import org.lwjgl.opengl.GL11._
import projects.mnLibrary.shaders.BoxShader

class Box extends Renderable[Unit, Unit] with Initable[Unit, Unit] with Updatable[((Double, Double, Double), (Double, Double, Double)), Unit] with Shaded {

    lazy val shaderProgram = new BoxShader

    val pos = shaderProgram.position
    val rot = shaderProgram.rotation
    val col = shaderProgram.vertColor

    protected def _init(arg:Unit) = {
        col.r = 1.0
        col.g = 0.0
        col.b = .25
    }

    protected def _update(data:updateType) {
        val (position, colour) = data
        val (x, y, z) = position
        pos.x = x
        pos.y = y
        pos.z = z

        val (r, g, b) = colour
        col.r = r
        col.g = g
        col.b = b
    }

    protected def _render(arg:Unit) {
        glLoadIdentity
        glBegin(GL_QUADS)
        glVertex3f(-.5f, .5f, 0.0f)
        glVertex3f(.5f, .5f, 0.0f)
        glVertex3f(.5f, -.5f, 0.0f)
        glVertex3f(-.5f, -.5f, 0.0f)
        glEnd
    }
}