package com.mattlloyd.opengl.VBO

import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl.GL15._
import java.nio.{FloatBuffer, IntBuffer, Buffer}
import com.mattlloyd.util.signal.Signal
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.{GL15, ARBBufferObject, ARBVertexBufferObject}
import com.mattlloyd.opengl.Initable



object VAOAccessType extends Enumeration {
    type VAOAccessType = Value
    val STREAM_DRAW = Value(GL_STREAM_DRAW)
    val STREAM_READ = Value(GL_STREAM_READ)
    val STREAM_COPY = Value(GL_STREAM_COPY)
    val STATIC_DRAW = Value(GL_STATIC_DRAW)
    val STATIC_READ = Value(GL_STATIC_READ)
    val STATIC_COPY = Value(GL_STATIC_COPY)
    val DYNAMIC_DRAW = Value(GL_DYNAMIC_DRAW)
    val DYNAMIC_READ = Value(GL_DYNAMIC_READ)
    val DYNAMIC_COPY = Value(GL_DYNAMIC_COPY)
}
abstract class VBO extends Initable[Int] {

    lazy val id = runInit

    lazy val vaoId = initVAO

    val vertices:FloatBuffer

    val vertexAccessType:VAOAccessType.VAOAccessType

    val indicies:FloatBuffer

    val indexAccessType:VAOAccessType.VAOAccessType

    def initVAO = {
        val vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        vaoId
    }

    def initVBO(vboId:Int) = {
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertices, vertexAccessType.id)
        vboId
    }

    def initIBO(iboId:Int) = {
        glBindBuffer(GL_ARRAY_BUFFER, iboId)
        glBufferData(GL_ARRAY_BUFFER, indicies, indexAccessType.id)
        iboId
    }

    def init = {
        // force VAO to be created.
        vaoId

        val buffers = BufferUtils.createIntBuffer(2)
        ARBBufferObject.glGenBuffersARB(buffers)
        val vboId = initVBO(buffers.get(0))
        val iboId = initIBO(buffers.get(1))
//
//                //==============================================================
//                // Now we tell OpenGL that we will use POSITION_INDEX and COLOR_INDEX
//                // to communicate respectively the vertex positions and vertex colors
//                // to our shaders.
//                {
//                    // First we enable the indices. This will affect the vertex
//                    // array object from above.
//                    glEnableVertexAttribArray(POSITION_INDEX);
//
//                    glEnableVertexAttribArray(COLOR_INDEX);
//
//                    // Then we tell OpenGL how it should read the GL_ARRAY_BUFFER
//                    // (to which we have bound our vertex data, see above).
//
//                    // The position data starts at the beginning of the vertex data
//                    glVertexAttribPointer(POSITION_INDEX, 4, GL_FLOAT, false,
//                        2 * VEC4_BYTES, 0);
//
//                    // The color data starts after the first 4 floats of position data
//                    glVertexAttribPointer(COLOR_INDEX, 4, GL_FLOAT, false,
//                        2 * VEC4_BYTES, VEC4_BYTES);
//                }
        id
    }

//    void CreateVBO() {
//
//        // Create a "vertex array" object.
//        // This OpenGL-object will group the "in_Position" and "in_Color"
//        // vertex attributes.
//
//        VaoId = glGenVertexArrays();
//        Util.checkGLError();
//
//        glBindVertexArray(VaoId);
//        Util.checkGLError();
//
//        //==============================================================
//        // Next we create the vertex position and color data that we
//        // will pass to the shaders.
//        // In the array below, we interleave the position and color data:
//        // first vec4 for position, then vec4 for color, vec4 for position, etc.
//
//        float[] VerticesArray = new float[]{
//            0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
//            // Top
//            -0.2f, 0.8f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f,
//            0.2f, 0.8f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
//            0.0f, 0.8f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
//            0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
//            // Bottom
//            -0.2f, -0.8f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
//            0.2f, -0.8f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f,
//            0.0f, -0.8f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
//            0.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
//            // Left
//            -0.8f, -0.2f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f,
//            -0.8f, 0.2f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
//            -0.8f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
//            -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
//            // Right
//            0.8f, -0.2f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
//            0.8f, 0.2f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f,
//            0.8f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
//            1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f
//        };
//
//        // Now we put this data in a FloatBuffer, that will be passed to LWJGL.
//        // Importantly: use the class BufferUtils, because then your FloatBuffer
//        // will be "direct" AND the byte ordering will be native (=required).
//        // Using native byte ordering is something that you might easily forget!
//
//        FloatBuffer Vertices = BufferUtils.createFloatBuffer(VerticesArray.length);
//        Vertices.put(VerticesArray);
//        Vertices.rewind(); // rewind, otherwise LWJGL thinks our buffer is empty
//
//        // We now create a new OpenGL object: a "buffer object" to hold all
//        // vertex data from above.
//        // We bind the new object to the target "GL_ARRAY_BUFFER" so that we can
//        // transfer our above data to OpenGL.
//        {
//            VboId = glGenBuffers();
//            Util.checkGLError();
//
//            glBindBuffer(GL_ARRAY_BUFFER, VboId);
//            Util.checkGLError();
//
//            // Transfer all data to the OpenGL-object, saying that it will
//            // be used for "static drawing", meaning we will not modify
//            // our vertex data in the future (so OpenGL can do optimizations).
//            glBufferData(GL_ARRAY_BUFFER, Vertices, GL_STATIC_DRAW);
//            Util.checkGLError();
//        }
//
//        //==============================================================
//        // Now we do something similar as above, but now for the indices,
//        // and we use the OpenGL target GL_ELEMENT_ARRAY_BUFFER instead
//        // of GL_ARRAY_BUFFER.
//
//        int IndicesArray[] = {
//            // Top
//            0, 1, 3,
//            0, 3, 2,
//            3, 1, 4,
//            3, 4, 2,
//            // Bottom
//            0, 5, 7,
//            0, 7, 6,
//            7, 5, 8,
//            7, 8, 6,
//            // Left
//            0, 9, 11,
//            0, 11, 10,
//            11, 9, 12,
//            11, 12, 10,
//            // Right
//            0, 13, 15,
//            0, 15, 14,
//            15, 13, 16,
//            15, 16, 14
//        };
//
//        IntBuffer Indices = BufferUtils.createIntBuffer(IndicesArray.length);
//        Indices.put(IndicesArray);
//        Indices.rewind();
//
//        IndexBufferId = glGenBuffers();
//        Util.checkGLError();
//
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IndexBufferId);
//        Util.checkGLError();
//
//        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Indices, GL_STATIC_DRAW);
//        Util.checkGLError();
//
//        //==============================================================
//        // Now we tell OpenGL that we will use POSITION_INDEX and COLOR_INDEX
//        // to communicate respectively the vertex positions and vertex colors
//        // to our shaders.
//        {
//            // First we enable the indices. This will affect the vertex
//            // array object from above.
//            glEnableVertexAttribArray(POSITION_INDEX);
//            Util.checkGLError();
//
//            glEnableVertexAttribArray(COLOR_INDEX);
//            Util.checkGLError();
//
//            // Then we tell OpenGL how it should read the GL_ARRAY_BUFFER
//            // (to which we have bound our vertex data, see above).
//
//            // The position data starts at the beginning of the vertex data
//            glVertexAttribPointer(POSITION_INDEX, 4, GL_FLOAT, false,
//                2 * VEC4_BYTES, 0);
//            Util.checkGLError();
//
//            // The color data starts after the first 4 floats of position data
//            glVertexAttribPointer(COLOR_INDEX, 4, GL_FLOAT, false,
//                2 * VEC4_BYTES, VEC4_BYTES);
//            Util.checkGLError();
//        }
//
//        // Just to be VERY clean, we will unbind the vertex attribute object
//        // and only bind it when we render. This way we cannot accidentally modify
//        // it anymore.
//        glBindVertexArray(0);
//        Util.checkGLError();
//
//        // Only after the vertex array is disabled, we unbind the buffers
//        // to the GL_ARRAY_BUFFER and GL_ELEMENT_ARRAY_BUFFER targets, because
//        // otherwise the vertex array object would become invalid again.
//        glBindBuffer(GL_ARRAY_BUFFER, 0);
//        Util.checkGLError();
//
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
//        Util.checkGLError();
//    }

}
