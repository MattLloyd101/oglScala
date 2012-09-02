package examples.shader.fxaa

import com.mattlloyd.opengl.shader._
import org.lwjgl.opengl.GL11
import shader.{FxaaShader}
import com.mattlloyd.opengl.VBO.VBOObject
import com.mattlloyd.opengl.{Renderable, Updatable, Initable}
import scala.math._

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 29/07/12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */

class SmoothBox extends Renderable[Unit] with Initable[Unit] with Updatable[Unit] with Shaded with VBOObject {

    val shaderProgram = new FxaaShader

    val pos = shaderProgram.pos
    val rot = shaderProgram.rot

    def init = {

    }

    def update(ms: Long) {

    }

    def render {
        GL11.glLoadIdentity
        GL11.glBegin(GL11.GL_QUADS)
        GL11.glVertex3f(-1.0f, 1.0f, 0.0f)
        GL11.glVertex3f(1.0f, 1.0f, 0.0f)
        GL11.glVertex3f(1.0f, -1.0f, 0.0f)
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f)
        GL11.glEnd
    }
}
