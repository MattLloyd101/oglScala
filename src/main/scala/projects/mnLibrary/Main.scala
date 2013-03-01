package projects.mnLibrary

import leap.LeapController
import objects.{Leaf, Box}
import org.lwjgl._
import input.Keyboard
import opengl._
import GL11._
import com.mattlloyd.util.KeyboardUtil
import com.mattlloyd.util.KeyboardUtil.KeyState
import com.mattlloyd.opengl.util.FPSCounter
import com.mattlloyd.opengl.data.resources.DirectoryResourceBundle
import Colours._
import util.glu.GLU
import com.leapmotion.leap.Controller

object Main {
    type Pos = (Double, Double, Double)
    var done: Boolean = false
    var displayList = List[Leaf]()

    val leapController = new LeapController

    var position = (0.0, 0.0, 0.0)
    var max = (-5000.0, -5000.0, 0.0)
    var min = (5000.0, 5000.0, 0.0)

    var colour = (1.0, 0.0, 0.0)

    def update() {
        FPSCounter.update
        KeyboardUtil.updateKeyboard
        displayList map { _.update(position, colour) }
    }

    def render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glLoadIdentity()
        displayList map { _.render() }
    }

    def scale(x:Double, srcMin:Double, srcMax:Double, trgMin:Double, trgMax:Double):Double = {
        val o = (((x - srcMin) / (srcMax - srcMin)) * (trgMax - trgMin)) + trgMin
        if(o.isNaN) 0 else o
    }

    def scale(x:Pos, srcMin:Pos, srcMax:Pos, trgMin:Pos, trgMax:Pos):Pos = (scale(x._1, srcMin._1, srcMax._1, trgMin._1, trgMax._1),
                                                                            scale(x._2, srcMin._2, srcMax._2, trgMin._2, trgMax._2),
                                                                            scale(x._3, srcMin._3, srcMax._3, trgMin._3, trgMax._3))

    def scaleToScreen(x:Pos, min:Pos, max:Pos):Pos = scale(x, min, max, (-1.0, -1.0, 1.0), (1.0, 1.0, 1.0))

    def init() {
        leapController.init()
        //leapController.onFrameSignal.add("onFrameHandler")(handleLeapFrame)
        new DirectoryResourceBundle("target", true)
        val w: Int = 1024
        val h: Int = 768

        try {
            Display.setDisplayMode(new DisplayMode(w, h))
            Display.setVSyncEnabled(true)
            Display.setTitle("mN Library")
            Display.create()
        }
        catch {
            case e: Exception => {
                System.out.println("Error setting up display")
                System.exit(0)
            }
        }

        glViewport(0, 0, w, h)
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        //GLU.gluPerspective(45.0f, (w.asInstanceOf[Float] / h.asInstanceOf[Float]), 1.0f, 20.0f)
        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity()
        glShadeModel(GL_SMOOTH)
        glClearColor(BACKGROUND_COLOUR.r, BACKGROUND_COLOUR.g, BACKGROUND_COLOUR.b, BACKGROUND_COLOUR.a)
        glClearDepth(1.0f)
        glEnable(GL_DEPTH_TEST)
        glDepthFunc(GL_LEQUAL)
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST)

        displayList = new Leaf() :: displayList

        displayList map { _.init() }

        KeyboardUtil.onKeyChange.add("Box Handler") {
            case (Keyboard.KEY_ESCAPE, KeyState.KEY_DOWN, _) =>
                done = true
        }
    }

    def main(args: Array[String]) {
        init()
        while (!done) {
            if (Display.isCloseRequested) done = true
            update()
            render()
            Display.update()
        }
        leapController.destroy()
        Display.destroy()
    }
}
