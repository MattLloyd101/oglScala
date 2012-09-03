package com.mattlloyd.opengl.data.immutable.vector

import java.nio.FloatBuffer

object Vector2f {
    def apply(x:Float = 0.0f, y:Float = 0.0f) = new Vector2f(x, y)
    def apply(buff:FloatBuffer) = new Vector2f(buff.get, buff.get)
}

class Vector2f(val x:Float = 0.0f, val y:Float = 0.0f) extends VectorLike[Vector2f] {

    def lengthSquared = x * x + y * y

    def translate(dX:Float, dY:Float) = new Vector4f(x + dX, y + dY)

    def +(v:Vector2f) = new Vector2f(x + v.x, y + v.y)

    def -(v:Vector2f) = new Vector2f(x - v.x, y - v.y)

    def negate = new Vector2f(-x, -y)

    def normalize = {
        val l = length
        new Vector2f((x/l).toFloat, (y/l).toFloat)
    }

    def dot(v:Vector2f) = x * v.x + y * v.y

    def scale(n:Float) = new Vector2f(x * n, y * n)

    def store(buff:FloatBuffer) = {
        buff.put(x)
        buff.put(y)
        buff
    }

    def load(buff: FloatBuffer) = new Vector2f(buff.get, buff.get)
}
