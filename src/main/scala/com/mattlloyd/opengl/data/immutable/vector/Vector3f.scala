
/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 18/08/12
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */

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
object Vector3f {
    def apply(x:Float = 0.0f, y:Float = 0.0f, z:Float = 0.0f) = new Vector3f(x, y, z)
    def apply(buff:FloatBuffer) = new Vector3f(buff.get, buff.get, buff.get)
}

class Vector3f(val x:Float = 0.0f, val y:Float = 0.0f, val z:Float = 0.0f) extends VectorLike[Vector3f] {

    def lengthSquared = x * x + y * y + z * z

    def translate(dX:Float, dY:Float, dZ:Float) = new Vector4f(x + dX, y + dY, z + dZ)

    def +(v:Vector3f) = new Vector3f(x + v.x, y + v.y, z + v.z)

    def -(v:Vector3f) = new Vector3f(x - v.x, y - v.y, z - v.z)

    def negate = new Vector3f(-x, -y, -z)

    def normalize = {
        val l = length
        new Vector3f((x/l).toFloat, (y/l).toFloat, (z/l).toFloat)
    }

    def dot(v:Vector3f) = x * v.x + y * v.y + z * v.z

    def scale(n:Float) = new Vector3f(x * n, y * n, z * n)

    def store(buff:FloatBuffer) = {
        buff.put(x)
        buff.put(y)
        buff.put(z)
        buff
    }

    def load(buff: FloatBuffer) = new Vector3f(buff.get, buff.get, buff.get)
}
