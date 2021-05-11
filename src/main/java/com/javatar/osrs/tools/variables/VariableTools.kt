package com.javatar.osrs.tools.variables

import com.javatar.osrs.definitions.impl.VarbitDefinition
import com.javatar.osrs.definitions.impl.VarpDefinition

/**
 * Not entirely 100% sure on how varps and varbits work, this could be completely wrong
 * This is an idea, to ensure that newly created varbits don't override existing varps.
 */

object VariableTools {

    @JvmStatic
    fun main(args: Array<String>) {

        val varp = VarpDefinition(0)
        val varbit = VarbitDefinition(1)
        val varbit1 = VarbitDefinition(2)

        varbit.index = varp.definitionId
        varbit1.index = varp.definitionId

        varbit1.leastSignificantBit = 3
        varbit1.mostSignificantBit = 5

        println(intersects(varp, varbit, varbit1))

    }

    fun intersects(varpDef: VarpDefinition, varbitDef: VarbitDefinition, otherVarbit: VarbitDefinition) : Boolean {
        if(varbitDef.index != varpDef.definitionId || otherVarbit.index != varpDef.definitionId) {
            return false
        }
        val range = varbitDef.leastSignificantBit..varbitDef.mostSignificantBit
        println(range)
        if(otherVarbit.leastSignificantBit in range || otherVarbit.mostSignificantBit in range) {
            return true
        }
        return false
    }

    fun validateVarbit(varbit: VarbitDefinition) : Boolean {
        if(varbit.leastSignificantBit >= varbit.mostSignificantBit) {
            return false
        }
        if(varbit.leastSignificantBit <= 0 || varbit.leastSignificantBit > 32) {
            return false
        }
        if(varbit.mostSignificantBit <= 0 || varbit.mostSignificantBit > 32) {
            return false
        }
        return true
    }

    fun Int.bitCount() : Int {
        var v = this
        var bits = 0
        while(v > 0) {
            if(bits >= 32) {
                break
            }
            bits++
            v = v shr 1
        }
        return bits
    }

    fun UInt.bitCount() : UInt {
        var v = this
        var bits = 0
        while(v > 0u) {
            if(bits >= 32) {
                break
            }
            bits++
            v = v shr 1
        }
        return v
    }
}