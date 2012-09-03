package com.mattlloyd.opengl.shader.uniform

import com.mattlloyd.opengl.shader.ShaderProgram
import org.lwjgl.opengl.{GL13, GL11}


class TextureUniform(parentShader: ShaderProgram, name: String, textureUnit: Int) extends IntUniform(parentShader, name, textureUnit) {
    override def flush {
        //        GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureUnit)
        //        GL11.glBindTexture(GL11.GL_TEXTURE_2D, )
        super.flush
    }

    /*
   glUseProgramObjectARB(my_program);
   int my_sampler_uniform_location = glGetUniformLocationARB(my_program, my_color_texture);
   glActiveTexture(GL_TEXTURE0 + i);
   glBindTexture(GL_TEXTURE_2D, my_texture_object);

   glUniform1iARB(my_sampler_uniform_location, i);
    */
}
