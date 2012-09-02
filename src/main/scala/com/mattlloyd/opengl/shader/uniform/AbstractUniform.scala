package com.mattlloyd.opengl.shader.uniform

import org.lwjgl.opengl.ARBShaderObjects._
import com.mattlloyd.opengl.shader.ShaderProgram
import java.lang.reflect.Method

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 24/08/12
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
object AbstractUniform {
    def isUniformField(field:Method) = {
        def testType(ttype: Class[_]):Boolean = (ttype, ttype.getSuperclass) match {
            case (t, _) if t == classOf[AbstractUniform] => true
            case (_, null) => false
            case (_, parent) => testType(parent)
            case _ => false
        }
        field.getParameterTypes.length == 0 && testType(field.getReturnType)
    }
}
abstract class AbstractUniform(parentShader: ShaderProgram, name:String) {

    val id = glGetUniformLocationARB(parentShader.shaderId, name)

    def flush
}
