package com.mattlloyd.opengl.data.immutable.vector

import scala.math._
import java.nio.FloatBuffer

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 13/08/12
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
object Vector4f {
    def apply(x:Float = 0.0f, y:Float = 0.0f, z:Float = 0.0f, w:Float = 0.0f) = new Vector4f(x, y, z, w)
    def apply(buff:FloatBuffer) = new Vector4f(buff.get, buff.get, buff.get, buff.get)
}

class Vector4f(val x:Float = 0.0f, val y:Float = 0.0f, val z:Float = 0.0f, val w:Float = 0.0f) extends VectorLike[Vector4f] {

    def lengthSquared = x * x + y * y + z * z + w * w

    def translate(dX:Float, dY:Float, dZ:Float, dW:Float) = new Vector4f(x + dX, y + dY, z + dZ, w + dW)

    def +(v:Vector4f) = new Vector4f(x + v.x, y + v.y, z + v.z, w + v.w)

    def -(v:Vector4f) = new Vector4f(x - v.x, y - v.y, z - v.z, w - v.w)

    def negate = new Vector4f(-x, -y, -z, -w)

    def normalize = {
        val l = length
        new Vector4f((x/l).toFloat, (y/l).toFloat, (z/l).toFloat, (w/l).toFloat)
    }

    def dot(v:Vector4f) = x * v.x + y * v.y + z * v.z + w * v.w

    def scale(n:Float) = new Vector4f(x * n, y * n, z * n, w * n)

    def store(buff:FloatBuffer) = {
        buff.put(x)
        buff.put(y)
        buff.put(z)
        buff.put(w)
        buff
    }

    def load(buff: FloatBuffer) = new Vector4f(buff.get, buff.get, buff.get, buff.get)
}
