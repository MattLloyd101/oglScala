package com.mattlloyd.opengl.util

import org.lwjgl.opengl.{DisplayMode, Display}
import collection.immutable.SortedMap

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 28/07/12
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */

object DisplayUtil {

    def getRestrictedDisplayModes: Map[(Int, Int), List[DisplayMode]] = {
        val targFreq = Display.getDesktopDisplayMode.getFrequency;
        val targBpp = Display.getDesktopDisplayMode.getBitsPerPixel;
        (Display.getAvailableDisplayModes filter {
            mode => mode.getBitsPerPixel == targBpp
        }).toList
            .sortBy {
            mode => (mode.getWidth, mode.getHeight, mode.getFrequency)
        }
            .groupBy(mode => (mode.getWidth, mode.getHeight))
    }

}
