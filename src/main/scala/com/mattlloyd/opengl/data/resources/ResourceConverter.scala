package com.mattlloyd.opengl.data.resources

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 27/08/12
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
trait ResourceConverter[InType, OutResource] {

    def canConvert(obj:InType):Boolean
    def convert(obj:InType):Resource[OutResource]

}
