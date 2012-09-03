package com.mattlloyd.opengl.data.immutable.matrix



object Matrix3x3 {
    def determinant3x3(t00: Double, t01: Double, t02: Double,
                       t10: Double, t11: Double, t12: Double,
                       t20: Double, t21: Double, t22: Double) = t00 * (t11 * t22 - t12 * t21) +
                                                                t01 * (t12 * t20 - t10 * t22) +
                                                                t02 * (t10 * t21 - t11 * t20)
}
