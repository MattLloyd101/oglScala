package com.mattlloyd.opengl.util

import compat.Platform
import collection.mutable


object FPSCounter {

    val MAX_SAMPLES = 2
    var lastTime = Platform.currentTime
    var tickIndex = 0
    var tickSum = 0.0
    var msToRenderLastFrame = 0:Long
    val tickList = mutable.MutableList.fill(MAX_SAMPLES)(0:Long)

    def avgMsToRenderFrame = tickSum/MAX_SAMPLES

    def fps = 1000.0 / avgMsToRenderFrame

    def update
    {
        msToRenderLastFrame = Platform.currentTime - lastTime
        tickSum -= tickList(tickIndex)
        tickSum += msToRenderLastFrame
        tickList(tickIndex) = msToRenderLastFrame
        tickIndex += 1
        if(tickIndex == MAX_SAMPLES)
            tickIndex=0;

        lastTime = Platform.currentTime
    }
}
