package com.mattlloyd.util.signal

import collection.immutable.Queue

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 28/07/12
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */

object Signal {
    def apply[Arg]() = new Signal[Arg]
}

// NOT THREAD SAFE.
// Possability to loose added callbacks. Need to add Synchronization.
class Signal[Arg] {

    type IsOneTimeCallback = Boolean
    type SignalID = String

    var callbacks:Queue[(Option[SignalID], IsOneTimeCallback, PartialFunction[Arg, Unit])] = Queue.empty

    def add(id:Option[SignalID], removeAfterOnce:IsOneTimeCallback, callback: PartialFunction[Arg, Unit]):Signal[Arg] = {
        callbacks = callbacks.enqueue((id, removeAfterOnce, callback))
        this
    }

    def add(id:SignalID)(callback: PartialFunction[Arg, Unit]):Signal[Arg] = add(Some(id), false, callback)
    def add(callback: PartialFunction[Arg, Unit]):Signal[Arg] = add(None, false, callback)

    def addOnce(id:SignalID)(callback: PartialFunction[Arg, Unit]):Signal[Arg] = add(Some(id), true, callback)
    def addOnce(callback: PartialFunction[Arg, Unit]):Signal[Arg] = add(None, true, callback)

    //def + = add(_)

    def remove(id: SignalID):Signal[Arg] = {
        callbacks.find { tup =>
            tup._1 match {
                case Some(id) =>
                    callbacks = callbacks.filterNot( _ == tup)
                    true
                case None => false
            }
        }

        this
    }

    def willRespondTo(arg:Arg):Boolean =
        callbacks.exists { tup =>
            val (_, _, pf) = tup
            pf.isDefinedAt(arg)
        }

    def dispatch(arg:Arg) {
        callbacks = callbacks.filter { tup =>
            val (_, _, callback) = tup
            callback.isDefinedAt(arg)
        }.foldLeft(callbacks) { (q, tup) =>
            val (_, isOnce, callback) = tup

            callback(arg)

            if(isOnce) { q.filterNot( _ == callback) } else q
        }
    }

    def apply(arg:Arg) = dispatch(arg)
}
