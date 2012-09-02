package examples.shader.fxaa

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 28/07/12
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */

import org.lwjgl._
import opengl._
import GL11._
import com.mattlloyd.util.KeyboardUtil
import com.mattlloyd.util.KeyboardUtil.KeyState
import com.mattlloyd.opengl.util.FPSCounter
import examples.shader.fxaa.SmoothBox
import com.mattlloyd.opengl.data.resources.DirectoryResourceBundle
import org.lwjgl.util.glu.GLU

object FXAAMain {
    var done: Boolean = false
    var box: SmoothBox = null

    def update {
        FPSCounter.update
        KeyboardUtil.updateKeyboard
        box.runUpdate(FPSCounter.msToRenderLastFrame)
    }

    def render {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glLoadIdentity
        box.runRender
    }

    def init {
        new DirectoryResourceBundle("target/", true)
        val w: Int = 1024
        val h: Int = 768

        try {
            Display.setDisplayMode(new DisplayMode(w, h))
            Display.setVSyncEnabled(true)
            Display.setTitle("Shader Setup")
            Display.create
        }
        catch {
            case e: Exception => {
                System.out.println("Error setting up display")
                System.exit(0)
            }
        }
        glViewport(0, 0, w, h)
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity
        GLU.gluPerspective(45.0f, (w.asInstanceOf[Float] / h.asInstanceOf[Float]), 0.1f, 100.0f)
        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity
        glShadeModel(GL_SMOOTH)
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        glClearDepth(1.0f)
        glEnable(GL_DEPTH_TEST)
        glDepthFunc(GL_LEQUAL)
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST)

        box = new SmoothBox

        KeyboardUtil.onKeyChange.add("Box Handler") {
            case ("UP", KeyState.KEY_DOWN, _) =>
            //box.pos.y += 0.01
            case ("DOWN", KeyState.KEY_DOWN, _) =>
            //box.pos.y = box.pos.y - 0.01
            case ("LEFT", KeyState.KEY_DOWN, _) =>
            //    box.pos.x = box.pos.x - 0.01
            case ("RIGHT", KeyState.KEY_DOWN, _) =>
            //    box.pos.x += 0.01
        }
    }

    def main(args: Array[String]) {
        init
        while (!done) {
            if (Display.isCloseRequested) done = true
            update
            render
            Display.update
        }
        Display.destroy
    }
}
