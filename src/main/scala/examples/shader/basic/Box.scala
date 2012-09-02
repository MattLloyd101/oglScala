package examples.shader.basic

import com.mattlloyd.opengl.shader._
import org.lwjgl.opengl.{GL12, GL11}
import shader.BasicShader
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

class Box extends Renderable[Unit] with Initable[Unit] with Updatable[Unit] with Shaded with VBOObject {

    lazy val shaderProgram = new BasicShader

    val newCol = shaderProgram.newCol
    val intensity = shaderProgram.intensity
    val pos = shaderProgram.pos
    val rot = shaderProgram.rot

    var n = 0.0
    var mult = 1.01

    def init = {

    }

    def update(ms:Long) {
        if (rot.z > 500)
            mult = 0.99
        else if (rot.z < 0.001)
            mult = 1.01

        rot.z = rot.z * mult
        n += 0.001
        intensity := 0.25 + abs(sin(n))
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
