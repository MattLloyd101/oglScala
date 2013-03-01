package projects.mnLibrary.shaders

import com.mattlloyd.opengl.shader.{FragmentShader, VertexShader, ShaderProgram}
import com.mattlloyd.opengl.shader.uniform.{RotationUniform, PositionUniform, RGBAUniform}
import com.mattlloyd.opengl.data.resources.ResourceImplicits._

class BoxShader extends ShaderProgram {

    override lazy val vertShader = Some(new VertexShader("target/classes/projects/mnLibrary/shaders/box.vert.glsl"))
    override lazy val fragShader = Some(new FragmentShader("target/classes/projects/mnLibrary/shaders/box.frag.glsl"))

    val vertColor = RGBAUniform(this, "vertColor")
    val position = PositionUniform(this, "position", (0,0,0))
    val rotation = RotationUniform(this, "rotation", (0,0,0))

}