package com.javatar.osrs.tools.variables

import java.util.*
import kotlin.reflect.KProperty

class VariablePlayer {

    val bits = BitSet(32)

    operator fun getValue(ref: Any?, property: KProperty<*>, lsb: Int, msb: Int) : Int {

        if(lsb > msb)
            return 0

        var value = 0
        for(i in lsb until msb) {
            value += if(bits[i]) (1 shl i) else 0
        }

        return value
    }

    operator fun setValue(ref: Any?, property: KProperty<*>, lsb: Int, msb: Int, value: Int) {

        if(lsb > msb)
            return

        var index = 0
        var v = value
        while (v != 0) {
            if (v % 2 != 0) {
                bits.set(index)
            }
            ++index
            v = v ushr 1
        }
    }

}