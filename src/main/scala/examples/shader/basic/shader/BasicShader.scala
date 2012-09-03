package examples.shader.basic.shader

import com.mattlloyd.opengl.shader.{FragmentShader, VertexShader, ShaderProgram}
import com.mattlloyd.opengl.shader.uniform._
import com.mattlloyd.opengl.data.resources.ResourceImplicits._
import scala.Some


class BasicShader extends ShaderProgram {

    override lazy val vertShader = Some(new VertexShader("target/classes/examples/shader/basic/shaders/basic.vert.glsl"))
    override lazy val fragShader = Some(new FragmentShader("target/classes/examples/shader/basic/shaders/basic.frag.glsl"))

    val newCol = RGBAUniform(this, "newCol")
    val intensity = DoubleUniform(this, "intensity", 1)
    val pos = PositionUniform(this)
    val rot = RotationUniform(this, "rotation", (0,0,0.001))

}
