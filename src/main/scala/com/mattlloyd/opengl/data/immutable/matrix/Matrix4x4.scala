package com.mattlloyd.opengl.data.immutable.matrix

import java.nio.FloatBuffer
import com.mattlloyd.opengl.data.immutable.vector._


object Matrix4x4 {
    lazy val zero: Matrix4x4 = new Matrix4x4()
    lazy val identity: Matrix4x4 = new Matrix4x4(m00 = 1, m11 = 1, m22 = 1, m33 = 1)

    // column major ordering.
    def apply(seq: Double*) = new Matrix4x4(seq(0), seq(1), seq(2), seq(3),
                                            seq(4), seq(5), seq(6), seq(7),
                                            seq(8), seq(9), seq(10), seq(11),
                                            seq(12), seq(13), seq(14), seq(15))

    def apply(buff:FloatBuffer) = new Matrix4x4(buff.get, buff.get, buff.get, buff.get,
                                                buff.get, buff.get, buff.get, buff.get,
                                                buff.get, buff.get, buff.get, buff.get,
                                                buff.get, buff.get, buff.get, buff.get)
}

class Matrix4x4(val m00: Double = 0.0, val m01: Double = 0.0, val m02: Double = 0.0, val m03: Double = 0.0,
                val m10: Double = 0.0, val m11: Double = 0.0, val m12: Double = 0.0, val m13: Double = 0.0,
                val m20: Double = 0.0, val m21: Double = 0.0, val m22: Double = 0.0, val m23: Double = 0.0,
                val m30: Double = 0.0, val m31: Double = 0.0, val m32: Double = 0.0, val m33: Double = 0.0) {

    // Column major ordering.
    // [ 0, 4,  8, 12,
    //   1, 5,  9, 13,
    //   2, 6, 10, 14,
    //   3, 7, 11, 15 ]

    def +(m: Matrix4x4) = {
        new Matrix4x4(m00 + m.m00, m01 + m.m01, m02 + m.m02, m03 + m.m03,
                      m10 + m.m10, m11 + m.m11, m12 + m.m12, m13 + m.m13,
                      m20 + m.m20, m21 + m.m21, m22 + m.m22, m23 + m.m23,
                      m30 + m.m30, m31 + m.m31, m32 + m.m32, m33 + m.m33)
    }

    def -(m: Matrix4x4) = {
        new Matrix4x4(m00 - m.m00, m01 - m.m01, m02 - m.m02, m03 - m.m03,
                      m10 - m.m10, m11 - m.m11, m12 - m.m12, m13 - m.m13,
                      m20 - m.m20, m21 - m.m21, m22 - m.m22, m23 - m.m23,
                      m30 - m.m30, m31 - m.m31, m32 - m.m32, m33 - m.m33)
    }
    
    def *(m: Matrix4x4) = {
        new Matrix4x4(m00 * m.m00 + m10 * m.m01 + m20 * m.m02 + m30 * m.m03,
                      m01 * m.m00 + m11 * m.m01 + m21 * m.m02 + m31 * m.m03,
                      m02 * m.m00 + m12 * m.m01 + m22 * m.m02 + m32 * m.m03,
                      m03 * m.m00 + m13 * m.m01 + m23 * m.m02 + m33 * m.m03,
                      m00 * m.m10 + m10 * m.m11 + m20 * m.m12 + m30 * m.m13,
                      m01 * m.m10 + m11 * m.m11 + m21 * m.m12 + m31 * m.m13,
                      m02 * m.m10 + m12 * m.m11 + m22 * m.m12 + m32 * m.m13,
                      m03 * m.m10 + m13 * m.m11 + m23 * m.m12 + m33 * m.m13,
                      m00 * m.m20 + m10 * m.m21 + m20 * m.m22 + m30 * m.m23,
                      m01 * m.m20 + m11 * m.m21 + m21 * m.m22 + m31 * m.m23,
                      m02 * m.m20 + m12 * m.m21 + m22 * m.m22 + m32 * m.m23,
                      m03 * m.m20 + m13 * m.m21 + m23 * m.m22 + m33 * m.m23,
                      m00 * m.m30 + m10 * m.m31 + m20 * m.m32 + m30 * m.m33,
                      m01 * m.m30 + m11 * m.m31 + m21 * m.m32 + m31 * m.m33,
                      m02 * m.m30 + m12 * m.m31 + m22 * m.m32 + m32 * m.m33,
                      m03 * m.m30 + m13 * m.m31 + m23 * m.m32 + m33 * m.m33)
    }

    def transpose = new Matrix4x4(  m00, m10, m20, m30,
                                    m01, m11, m21, m31,
                                    m02, m12, m22, m32,
                                    m03, m13, m23, m33)

    def determinant =   m00 * ((m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32) - (m13 * m22 * m31 - m11 * m23 * m32 - m12 * m21 * m33)) -
                        m01 * ((m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32) - (m13 * m22 * m30 - m10 * m23 * m32 - m12 * m20 * m33)) +
                        m02 * ((m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31) - (m13 * m21 * m30 - m10 * m23 * m31 - m11 * m20 * m33)) -
                        m03 * ((m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31) - (m12 * m21 * m30 - m10 * m22 * m31 - m11 * m20 * m32))

    def inverse:Option[Matrix4x4] = determinant match {
        case 0 =>
            None
        case d =>
            val dInv = 1.0/d
            Some(new Matrix4x4(
                Matrix3x3.determinant3x3(m11, m12, m13, m21, m22, m23, m31, m32, m33)*dInv,
                -Matrix3x3.determinant3x3(m10, m12, m13, m20, m22, m23, m30, m32, m33)*dInv,
                Matrix3x3.determinant3x3(m10, m11, m13, m20, m21, m23, m30, m31, m33)*dInv,
                -Matrix3x3.determinant3x3(m10, m11, m12, m20, m21, m22, m30, m31, m32)*dInv,

                -Matrix3x3.determinant3x3(m01, m02, m03, m21, m22, m23, m31, m32, m33)*dInv,
                Matrix3x3.determinant3x3(m00, m02, m03, m20, m22, m23, m30, m32, m33)*dInv,
                -Matrix3x3.determinant3x3(m00, m01, m03, m20, m21, m23, m30, m31, m33)*dInv,
                Matrix3x3.determinant3x3(m00, m01, m02, m20, m21, m22, m30, m31, m32)*dInv,

                Matrix3x3.determinant3x3(m01, m02, m03, m11, m12, m13, m31, m32, m33)*dInv,
                -Matrix3x3.determinant3x3(m00, m02, m03, m10, m12, m13, m30, m32, m33)*dInv,
                Matrix3x3.determinant3x3(m00, m01, m03, m10, m11, m13, m30, m31, m33)*dInv,
                -Matrix3x3.determinant3x3(m00, m01, m02, m10, m11, m12, m30, m31, m32)*dInv,

                -Matrix3x3.determinant3x3(m01, m02, m03, m11, m12, m13, m21, m22, m23)*dInv,
                Matrix3x3.determinant3x3(m00, m02, m03, m10, m12, m13, m20, m22, m23)*dInv,
                -Matrix3x3.determinant3x3(m00, m01, m03, m10, m11, m13, m20, m21, m23)*dInv,
                Matrix3x3.determinant3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22)*dInv
            ))
    }

    def negate = new Matrix4x4(-m00, -m01, -m02, -m03,
                               -m10, -m11, -m12, -m13,
                               -m20, -m21, -m22, -m23,
                               -m30, -m31, -m32, -m33)

    def translate(v:Vector3f) = new Matrix4x4(m30=m00 * v.x + m10 * v.y + m20 * v.z,
                                              m31=m01 * v.x + m11 * v.y + m21 * v.z,
                                              m32=m02 * v.x + m12 * v.y + m22 * v.z,
                                              m33=m03 * v.x + m13 * v.y + m23 * v.z)

    def translate(v:Vector2f) = new Matrix4x4(m30 = m00 * v.x + m10 * v.y,
                                              m31 = m01 * v.x + m11 * v.y,
                                              m32 = m02 * v.x + m12 * v.y,
                                              m33 = m03 * v.x + m13 * v.y)

    def storeRowMajor(buff:FloatBuffer) = {
        buff.put(m00.toFloat); buff.put(m01.toFloat); buff.put(m02.toFloat); buff.put(m03.toFloat)
        buff.put(m10.toFloat); buff.put(m11.toFloat); buff.put(m12.toFloat); buff.put(m13.toFloat)
        buff.put(m20.toFloat); buff.put(m21.toFloat); buff.put(m22.toFloat); buff.put(m23.toFloat)
        buff.put(m30.toFloat); buff.put(m31.toFloat); buff.put(m32.toFloat); buff.put(m33.toFloat)

        buff
    }

    def storeColMajor(buff:FloatBuffer) = {
        buff.put(m00.toFloat); buff.put(m10.toFloat); buff.put(m20.toFloat); buff.put(m30.toFloat)
        buff.put(m01.toFloat); buff.put(m11.toFloat); buff.put(m21.toFloat); buff.put(m31.toFloat)
        buff.put(m02.toFloat); buff.put(m12.toFloat); buff.put(m22.toFloat); buff.put(m32.toFloat)
        buff.put(m03.toFloat); buff.put(m13.toFloat); buff.put(m23.toFloat); buff.put(m33.toFloat)

        buff
    }
}
