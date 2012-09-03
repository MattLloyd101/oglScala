package com.mattlloyd.opengl.data.resources


trait ResourceConverter[InType, OutResource] {

    def canConvert(obj:InType):Boolean
    def convert(obj:InType):Resource[OutResource]

}
