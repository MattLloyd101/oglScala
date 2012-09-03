package com.mattlloyd.util



import scala.collection._
import scala.collection.generic._
import scala.collection.mutable.{Builder, ListBuffer}
import java.nio.{Buffer, FloatBuffer}
import org.lwjgl.BufferUtils
import scala.Float

//class FloatBufferPlus[A](buff: FloatBuffer) extends Traversable[A]
//                                           with GenericTraversableTemplate[A, FloatBufferPlus]
//                                           with TraversableLike[A, FloatBufferPlus[A]]
//                                           with IterableLike[A, FloatBufferPlus[A]]
//                                           with GenIterable[A] {
//    override def companion = FloatBufferPlus
//
//    def iterator = new Iterator[A] {
//        def hasNext = buff.hasRemaining
//
//        def next() = buff.get.asInstanceOf[A]
//    }
//}
//
//object FloatBufferPlus extends TraversableFactory[FloatBufferPlus] {
//    implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, FloatBufferPlus[A]] = new GenericCanBuildFrom[A]
//
//    def newBuilder[A] = new ListBuffer[A] mapResult { x =>
//        val buff = BufferUtils.createFloatBuffer(x.length)
//        buff.put(x.toArray.asInstanceOf[Array[Float]])
//        new FloatBufferPlus(buff) }
//}
