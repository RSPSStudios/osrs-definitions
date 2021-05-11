package com.javatar.osrs.tools.variables

import kotlin.reflect.KProperty

class VariableBit(val variablePlayer: VariablePlayer, val lsb: Int, val msb: Int) {

    private val listeners: MutableList<(VariableBit, Int, Int) -> Unit> = mutableListOf()

    fun addListener(listener: (VariableBit, Int, Int) -> Unit) {
        listeners.add(listener)
    }

    fun removeListener(listener: (VariableBit, Int, Int) -> Unit) {
        listeners.remove(listener)
    }

    operator fun getValue(ref: Any?, property: KProperty<*>) : Int {
        return variablePlayer.getValue(ref, property, lsb, msb)
    }

    operator fun setValue(ref: Any?, property: KProperty<*>, value: Int) {

        val oldValue = getValue(ref, property)
        if (oldValue != value) {
            listeners.forEach {
                it(this, oldValue, value)
            }
        }

        variablePlayer.setValue(ref, property, lsb, msb, value)
    }
}