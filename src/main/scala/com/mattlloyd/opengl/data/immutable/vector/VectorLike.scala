package com.mattlloyd.opengl.data.immutable.vector

import math._
import java.nio.FloatBuffer

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 18/08/12
 * Time: 23:52
 * To change this template use File | Settings | File Templates.
 */

trait VectorLike[T <: VectorLike[T]] {
    def length = sqrt(lengthSquared)

    def lengthSquared:Float

    def +(v: T): T

    def -(v: T): T

    def negate: T

    def normalize: T

    def dot(v: T): Float

    def angle(v: T) = acos(max(min(dot(v) / (length * v.length), -1), 1))

    def scale(n:Float) : T

    def store(buff:FloatBuffer): FloatBuffer
    def load(buff:FloatBuffer): T
}
