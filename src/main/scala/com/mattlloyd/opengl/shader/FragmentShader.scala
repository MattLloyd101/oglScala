package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.ARBFragmentShader
import com.mattlloyd.opengl.data.resources.Resource



class FragmentShader(val shaderSourceResource:Resource[String]) extends Shader {

    val shaderType = ShaderType.Fragment
    val shaderTypeID = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB

}
