package com.mattlloyd.util

import org.lwjgl.input.Keyboard
import signal.Signal

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 29/07/12
 * Time: 00:14
 * To change this template use File | Settings | File Templates.
 */

object KeyboardUtil {

    object KeyState extends Enumeration {
        type KeyState = Value
        val KEY_UP, KEY_DOWN = Value
    }

    type KeyValue = Int
    type KeyValueMapping = Map[KeyValue, KeyState.KeyState]
    type Key = String
    type KeyboardState = Map[Key, KeyState.KeyState]
    type KeyEvent = (Key, KeyState.KeyState, KeyboardState)
    val onKeyChange = Signal[KeyEvent]()

    val allKeys = List(Keyboard.KEY_NONE, Keyboard.KEY_ESCAPE, Keyboard.KEY_1, Keyboard.KEY_2, Keyboard.KEY_3,
        Keyboard.KEY_4, Keyboard.KEY_5, Keyboard.KEY_6, Keyboard.KEY_7, Keyboard.KEY_8, Keyboard.KEY_9, Keyboard.KEY_0,
        Keyboard.KEY_MINUS, Keyboard.KEY_EQUALS, Keyboard.KEY_BACK, Keyboard.KEY_TAB, Keyboard.KEY_Q, Keyboard.KEY_W,
        Keyboard.KEY_E, Keyboard.KEY_R, Keyboard.KEY_T, Keyboard.KEY_Y, Keyboard.KEY_U, Keyboard.KEY_I, Keyboard.KEY_O,
        Keyboard.KEY_P, Keyboard.KEY_LBRACKET, Keyboard.KEY_RBRACKET, Keyboard.KEY_RETURN, Keyboard.KEY_LCONTROL,
        Keyboard.KEY_A, Keyboard.KEY_S, Keyboard.KEY_D, Keyboard.KEY_F, Keyboard.KEY_G, Keyboard.KEY_H, Keyboard.KEY_J,
        Keyboard.KEY_K, Keyboard.KEY_L, Keyboard.KEY_SEMICOLON, Keyboard.KEY_APOSTROPHE, Keyboard.KEY_GRAVE,
        Keyboard.KEY_LSHIFT, Keyboard.KEY_BACKSLASH, Keyboard.KEY_Z, Keyboard.KEY_X, Keyboard.KEY_C, Keyboard.KEY_V,
        Keyboard.KEY_B, Keyboard.KEY_N, Keyboard.KEY_M, Keyboard.KEY_COMMA, Keyboard.KEY_PERIOD, Keyboard.KEY_SLASH,
        Keyboard.KEY_RSHIFT, Keyboard.KEY_MULTIPLY, Keyboard.KEY_LMENU, Keyboard.KEY_SPACE, Keyboard.KEY_CAPITAL,
        Keyboard.KEY_F1, Keyboard.KEY_F2, Keyboard.KEY_F3, Keyboard.KEY_F4, Keyboard.KEY_F5, Keyboard.KEY_F6,
        Keyboard.KEY_F7, Keyboard.KEY_F8, Keyboard.KEY_F9, Keyboard.KEY_F10, Keyboard.KEY_NUMLOCK, Keyboard.KEY_SCROLL,
        Keyboard.KEY_NUMPAD7, Keyboard.KEY_NUMPAD8, Keyboard.KEY_NUMPAD9, Keyboard.KEY_SUBTRACT, Keyboard.KEY_NUMPAD4,
        Keyboard.KEY_NUMPAD5, Keyboard.KEY_NUMPAD6, Keyboard.KEY_ADD, Keyboard.KEY_NUMPAD1, Keyboard.KEY_NUMPAD2,
        Keyboard.KEY_NUMPAD3, Keyboard.KEY_NUMPAD0, Keyboard.KEY_DECIMAL, Keyboard.KEY_F11, Keyboard.KEY_F12,
        Keyboard.KEY_F13, Keyboard.KEY_F14, Keyboard.KEY_F15, Keyboard.KEY_KANA, Keyboard.KEY_CONVERT,
        Keyboard.KEY_NOCONVERT, Keyboard.KEY_YEN, Keyboard.KEY_NUMPADEQUALS, Keyboard.KEY_CIRCUMFLEX,
        Keyboard.KEY_AT, Keyboard.KEY_COLON, Keyboard.KEY_UNDERLINE, Keyboard.KEY_KANJI, Keyboard.KEY_STOP,
        Keyboard.KEY_AX, Keyboard.KEY_UNLABELED, Keyboard.KEY_NUMPADENTER, Keyboard.KEY_RCONTROL,
        Keyboard.KEY_NUMPADCOMMA, Keyboard.KEY_DIVIDE, Keyboard.KEY_SYSRQ, Keyboard.KEY_RMENU, Keyboard.KEY_PAUSE,
        Keyboard.KEY_HOME, Keyboard.KEY_UP, Keyboard.KEY_PRIOR, Keyboard.KEY_LEFT, Keyboard.KEY_RIGHT,
        Keyboard.KEY_END, Keyboard.KEY_DOWN, Keyboard.KEY_NEXT, Keyboard.KEY_INSERT, Keyboard.KEY_DELETE,
        Keyboard.KEY_LMETA, Keyboard.KEY_RMETA, Keyboard.KEY_APPS,
        Keyboard.KEY_POWER, Keyboard.KEY_SLEEP) map {
        Keyboard.getKeyName _
    }

    var keyboardState: KeyboardState = Map[Key, KeyState.KeyState](allKeys.zip(Stream.continually(KeyState.KEY_UP)): _*)

    // Assumes only ever accessed in a single thread.
    def updateKeyboard {
        var keyEvents: List[(Key, KeyState.KeyState)] = List[(Key, KeyState.KeyState)]()

        allKeys foreach {
            key =>
                val keyState = if (Keyboard.isKeyDown(Keyboard.getKeyIndex(key))) KeyState.KEY_DOWN else KeyState.KEY_UP
                (keyboardState(key), keyState) match {
                    case (KeyState.KEY_DOWN, KeyState.KEY_UP) =>
                        keyEvents = (key, KeyState.KEY_UP) :: keyEvents
                    case (KeyState.KEY_UP, KeyState.KEY_DOWN) =>
                        keyEvents = (key, KeyState.KEY_DOWN) :: keyEvents
                    case _ =>
                }
                keyboardState + (key -> keyState)
        }

        keyEvents foreach {
            event =>
                val (key, state) = event
                onKeyChange.dispatch((key, state, keyboardState))
        }
    }

}