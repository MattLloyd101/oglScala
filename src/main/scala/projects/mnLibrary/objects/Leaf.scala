package projects.mnLibrary.objects

import com.mattlloyd.opengl.{Updatable, Initable, Renderable}
import com.mattlloyd.opengl.shader.Shaded
import org.lwjgl.opengl.GL11._
import projects.mnLibrary.shaders.BoxShader
import com.mattlloyd.util.colour.Colour

class Leaf extends Renderable[Unit, Unit] with Initable[Unit, Unit] with Updatable[((Double, Double, Double), (Double, Double, Double)), Unit] with Shaded {

    lazy val shaderProgram = new BoxShader

    val pos = shaderProgram.position
    val rot = shaderProgram.rotation
    val col = shaderProgram.vertColor

    val colour = Colour.fromInt(0x6c7803)

    protected def _init(arg:Unit) = {
        col.r = colour.r
        col.g = colour.g
        col.b = colour.b
    }

    protected def _update(data:updateType) {
    }

    def renderMesh {
        val offset = Math.Pi / 2
        val d = 0.01f
        val rx = 0.5f
        val ry = 0.75f
        var t = offset
        var t2 = t + d
        while (t2 <= Math.Pi * 2 + offset) {
            val x = Math.cos(t).toFloat
            if(Math.abs(x) >= (0.3)) {
                val offset = ((if(x < 0) 1 else -1) * 0.3).toFloat * rx
                glVertex3f(offset + (x * rx).toFloat, (Math.sin(t) * ry).toFloat, 0.0f)
                glVertex3f(offset + (Math.cos(t2) * rx).toFloat, (Math.sin(t2) * ry).toFloat, 0.0f)
                glVertex3f(offset, 0.0f, 0.0f)
            }
            t += d
            t2 += d
        }
    }

    protected def _render(arg:Unit) {
        glLoadIdentity
        glBegin(GL_TRIANGLES)
        renderMesh
        glEnd
    }
}