package examples.shader.basic.shader

import com.mattlloyd.opengl.shader.{FragmentShader, VertexShader, ShaderProgram}
import com.mattlloyd.opengl.shader.uniform._
import com.mattlloyd.opengl.data.resources.ResourceImplicits._
import scala.Some

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 24/08/12
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
class BasicShader extends ShaderProgram {

    override lazy val vertShader = Some(new VertexShader("target/classes/examples/shader/basic/shaders/basic.vert.glsl"))
    override lazy val fragShader = Some(new FragmentShader("target/classes/examples/shader/basic/shaders/basic.frag.glsl"))

    val newCol = RGBAUniform(this, "newCol")
    val intensity = DoubleUniform(this, "intensity", 1)
    val pos = PositionUniform(this)
    val rot = RotationUniform(this, "rotation", (0,0,0.001))

}
