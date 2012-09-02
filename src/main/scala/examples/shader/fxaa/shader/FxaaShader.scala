package examples.shader.fxaa.shader

import com.mattlloyd.opengl.shader.{FragmentShader, VertexShader, ShaderProgram}
import com.mattlloyd.opengl.shader.uniform._
import scala.Some
import com.mattlloyd.opengl.data.resources.ResourceImplicits._

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 24/08/12
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
class FxaaShader extends ShaderProgram {
    override lazy val vertShader = Some(new VertexShader("target/classes/examples/shader/fxaa/shaders/fxaa.vert.glsl"))
    override lazy val fragShader = Some(new FragmentShader("target/classes/examples/shader/fxaa/shaders/fxaa.frag.glsl"))

    /*
    uniform sampler2D u_texture;
uniform vec2 g_Resolution;
varying vec2 v_texCoords;

uniform float m_Subpix;
uniform float m_EdgeThreshold;
uniform float m_EdgeThresholdMin;

     */

    val pos = PositionUniform(this)
    val rot = RotationUniform(this, "rotation", (0,0,0.001))

    // TODO: This needs to be re-inited when we change resolution.
    val resolution = new FloatTuple2Uniform(this, "g_Resolution", (1024, 768))
    val subPix = FloatUniform(this, "m_Subpix", 0.75f)
    val edgeThreshold = FloatUniform(this, "m_EdgeThreshold", 0.166f)
    val edgeThresholdMin = FloatUniform(this, "m_EdgeThresholdMin", 0.0833f)

}
