package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.ARBVertexShader
import com.mattlloyd.opengl.data.resources.Resource



class VertexShader(val shaderSourceResource:Resource[String]) extends Shader {

    val shaderType = ShaderType.Vertex
    val shaderTypeID = ARBVertexShader.GL_VERTEX_SHADER_ARB
}
