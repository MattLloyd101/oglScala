package com.mattlloyd.util.colour

import util.Random

object ColourShade {
    val LIGHT: ColourShade = ColourShade(0.3f, 0.7f, 0.9f, 1f, 0.15f, 0.30f)
    val DARK: ColourShade = ColourShade(0.7f, 1f, 0.15f, 0.4f, 0f, 0f, 0.5f, 0.75f)
    val BRIGHT: ColourShade = ColourShade(0.8f, 1f, 0.8f, 1f)
    val WEAK: ColourShade = ColourShade(0.15f, 0.3f, 0.7f, 1f, 0.2f, 0.2f)
    val NEUTRAL: ColourShade = ColourShade(0.25f, 0.35f, 0.3f, 0.7f, 0.15f, 0.15f, 0.9f, 1f)
    val FRESH: ColourShade = ColourShade(0.4f, 0.8f, 0.8f, 1f, 0.05f, 0.3f, 0.8f, 1f)
    val SOFT: ColourShade = ColourShade(0.2f, 0.3f, 0.6f, 0.9f, 0.05f, 0.15f, 0.6f, 0.9f)
    val HARD: ColourShade = ColourShade(0.9f, 1f, 0.4f, 1f)
    val WARM: ColourShade = ColourShade(0.6f, 0.9f, 0.4f, 0.9f, 0.2f, 0.2f, 0.8f, 1f)
    val COOL: ColourShade = ColourShade(0.05f, 0.2f, 0.9f, 1f, 0f, 0f, 0.95f, 1f)
    val INTENSE: List[ColourShade] = List(ColourShade(0.9f, 1f, 0.2f, 0.35f), ColourShade(0.9f, 1f, 0.8f, 1f))
}

case class ColourShade(saturationMin: Float, saturationMax: Float, valueMin: Float, valueMax: Float, blackMin: Float = 0f, blackMax: Float = 0f, whiteMin: Float = 1f, whiteMax: Float = 1f) {
//
//    def getColour(rng:Random, colour:Int, variance:Double = 0.035):Colour = {
//        val hsv:Colour = Colour.fromInt(colour)
//
//        if (hsv.s < 0.01) {
//            if (hsv.v == 1) {
//                // Colour is white
//                return new ColourHSV(hsv.h, 0, rng.nextFloatBetween(whiteMin, whiteMax)).toHex();
//            } else if (hsv.v < 0.08) {
//                // Colour is black
//                return new ColourHSV(hsv.h, 0, rng.nextFloat() < 0.5 ? rng.nextFloatBetween(whiteMin, whiteMax) : rng.nextFloatBetween(blackMin, blackMax)).toHex();
//            }
//            // Colour is grey
//            return new ColourHSV(hsv.h, 0, rng.nextFloatBetween(blackMin, blackMax)).toHex();
//        }
//
//        return new ColourHSV(
//            hsv.h + rng.nextFloatBetween(-variance, variance) * 360,
//            rng.nextFloatBetween(saturationMin, saturationMax),
//            rng.nextFloatBetween(valueMin, valueMax)
//        ).toHex();
//    }
}