package projects.mnLibrary.leap

import com.leapmotion.leap.{Frame, Vector, Controller, Listener}
import java.lang.Math
import scala.Math
import com.mattlloyd.util.signal.Signal

object LeapController {
    val controller = new Controller()
}
class LeapController extends Listener {

    val onInitSignal = Signal[Controller]()
    val onConnectSignal = Signal[Controller]()
    val onDisconnectSignal = Signal[Controller]()
    val onExitSignal = Signal[Controller]()
    val onFrameSignal = Signal[Controller]()

    def init() {
        LeapController.controller.addListener(this)
    }

    def destroy() {
        LeapController.controller.removeListener(this)
    }

    override def onInit(controller:Controller) { onInitSignal.dispatch(controller) }

    override def onConnect(controller:Controller) { onConnectSignal.dispatch(controller) }

    override def onDisconnect(controller:Controller) { onDisconnectSignal.dispatch(controller) }

    override def onExit(controller: Controller) { onExitSignal.dispatch(controller) }

    override def onFrame(controller: Controller) { onFrameSignal.dispatch(controller) }

}
