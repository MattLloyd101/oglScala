package examples.shader.fxaa.shader

import com.mattlloyd.opengl.shader.{FragmentShader, VertexShader, ShaderProgram}
import com.mattlloyd.opengl.shader.uniform._
import scala.Some
import com.mattlloyd.opengl.data.resources.ResourceImplicits._



    val pos = PositionUniform(this)
    val rot = RotationUniform(this, "rotation", (0,0,0.001))

    // TODO: This needs to be re-inited when we change resolution.
    val resolution = new FloatTuple2Uniform(this, "g_Resolution", (1024, 768))
    val subPix = FloatUniform(this, "m_Subpix", 0.75f)
    val edgeThreshold = FloatUniform(this, "m_EdgeThreshold", 0.166f)
    val edgeThresholdMin = FloatUniform(this, "m_EdgeThresholdMin", 0.0833f)

}
