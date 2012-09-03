package com.mattlloyd.opengl.data.immutable.vector

import math._
import java.nio.FloatBuffer



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
