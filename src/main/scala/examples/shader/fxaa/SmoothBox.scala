package examples.shader.fxaa

import com.mattlloyd.opengl.shader._
import org.lwjgl.opengl.GL11
import shader.FxaaShader
import com.mattlloyd.opengl.VBO.VBOObject
import com.mattlloyd.opengl.{Renderable, Updatable, Initable}

class SmoothBox extends Renderable[Unit, Unit] with Initable[Unit, Unit] with Updatable[Unit, Unit] with Shaded with VBOObject {

    val shaderProgram = new FxaaShader

    val pos = shaderProgram.pos
    val rot = shaderProgram.rot

    def _init(a:Unit) {

    }

    def _update(ms: Unit) {

    }

    def _render(a:Unit) {
        GL11.glLoadIdentity()
        GL11.glBegin(GL11.GL_QUADS)
        GL11.glVertex3f(-1.0f, 1.0f, 0.0f)
        GL11.glVertex3f(1.0f, 1.0f, 0.0f)
        GL11.glVertex3f(1.0f, -1.0f, 0.0f)
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f)
        GL11.glEnd()
    }
}
