package com.mattlloyd.opengl.shader

import org.lwjgl.opengl.ARBFragmentShader
import com.mattlloyd.opengl.data.resources.Resource

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 12/08/12
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */

class FragmentShader(val shaderSourceResource:Resource[String]) extends Shader {

    val shaderType = ShaderType.Fragment
    val shaderTypeID = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB

}
