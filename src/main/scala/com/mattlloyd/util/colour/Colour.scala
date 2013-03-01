package com.mattlloyd.util.colour

object Colour {

    val BLACK = Colour(0.0f, 0.0f, 0.0f, 1.0f)

    def fromInt(colour: Int, alpha: Float = 1.0f): Colour = fromRGBA((colour >> 16) & 0xFF, (colour >> 8) & 0xFF, colour & 0xFF, alpha)

    def fromRGB(r: Int, g: Int, b: Int): Colour = Colour(r / 255.0f, g / 255.0f, b / 255.0f, 1.0f)

    def fromRGBA(r: Int, g: Int, b: Int, a: Int): Colour = Colour(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f)

    def fromRGBA(r: Int, g: Int, b: Int, a: Float): Colour = Colour(r / 255.0f, g / 255.0f, b / 255.0f, a)

    def fromHSV(h: Float, s: Float, v: Float, a: Float): Colour = HSVColour(h, s, v, a)

    def apply(r: Float, g: Float, b: Float, a: Float): Colour = RGBColour(r, g, b, a)
}

trait Colour {
    val r: Float
    val g: Float
    val b: Float
    val a: Float

    val h:Float
    val s:Float
    val v:Float
}

case class RGBColour(r: Float, g: Float, b: Float, a: Float) extends Colour {

    private lazy val max:Float = Math.max(Math.max(r, g), b)
    private lazy val min:Float = Math.min(Math.min(r, g), b)
    private lazy val delta:Float = max - min

    private var _h: Float = 0.0f
    private var _s: Float = 0.0f
    private var _v: Float = 0.0f

    private def solveHSV() {
        if(max == min) {
            _h = 0
            _s = 0
            _v = max
        } else {
            max match {
                case m if m == r =>
                    _h = ((g - b) / delta) * 60
                case m if m == g =>
                    _h = (((b - r) / delta) + 2) * 60
                case m if m == b =>
                    _h = (((r - g) / delta) + 4) * 60
            }
            if (_h < 0) _h += 360
            _s = delta / max
            _v = max / 255
        }
    }

    lazy val h = {
        solveHSV()
        _h
    }
    lazy val s = {
        solveHSV()
        _s
    }
    lazy val v = {
        solveHSV()
        _v
    }
}

case class HSVColour(h: Float, s: Float, v: Float, a: Float) extends Colour {

    private lazy val h2: Float = {
        def loop(h: Float): Float = if (h < 0) loop(h + 360) else h % 360
        loop(h)
    }
    private lazy val hi: Float = Math.round(h2 / 60)
    private lazy val f: Float = (h2 / 60) - hi
    private lazy val p: Float = (v * (1 - s))
    private lazy val q: Float = (v * (1 - (f * s)))
    private lazy val t: Float = (v * (1 - ((1 - f) * s)))

    private var _r: Float = 0.0f
    private var _g: Float = 0.0f
    private var _b: Float = 0.0f

    private def solveRGB() {
        if (s == 0) {
            _r = v.toFloat
            _g = _r
            _b = _r
        } else {
            hi match {
                case 0 => _r = v; _g = t; _b = p
                case 1 => _r = q; _g = v; _b = p
                case 2 => _r = p; _g = v; _b = t
                case 3 => _r = p; _g = q; _b = v
                case 4 => _r = t; _g = p; _b = v
                case 5 => _r = v; _g = p; _b = q
            }
        }
    }

    lazy val r = {
        solveRGB()
        _r
    }
    lazy val g = {
        solveRGB()
        _g
    }
    lazy val b = {
        solveRGB()
        _b
    }
}