package examples.shader.testing.shader

import com.mattlloyd.opengl.shader.{FragmentShader, VertexShader, ShaderProgram}
import com.mattlloyd.opengl.shader.uniform._
import com.mattlloyd.opengl.data.resources.ResourceImplicits._
import scala.Some


class TestingShader extends ShaderProgram {

    override lazy val vertShader = Some(new VertexShader("target/classes/examples/shader/basic/shaders/basic.vert.glsl"))
    override lazy val fragShader = Some(new FragmentShader("target/classes/examples/shader/basic/shaders/basic.frag.glsl"))

    val vertColor = RGBAUniform(this, "vertColor")
    val position = PositionUniform(this, "position", (0,0,0))
    val rotation = RotationUniform(this, "rotation", (0,0,0))

}
