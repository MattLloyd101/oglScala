package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.ARBVertexShader
import com.mattlloyd.opengl.data.resources.Resource

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */

class VertexShader(val shaderSourceResource:Resource[String]) extends Shader {

    val shaderType = ShaderType.Vertex
    val shaderTypeID = ARBVertexShader.GL_VERTEX_SHADER_ARB
}
