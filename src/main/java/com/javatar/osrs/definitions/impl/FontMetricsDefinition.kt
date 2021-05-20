package com.javatar.osrs.definitions.impl

import com.javatar.osrs.definitions.Definition
import kotlin.experimental.and

class FontMetricsDefinition(val id: Int) : Definition {
    var advances = intArrayOf()
    var ascent = 0
    var kerning = intArrayOf()

    override fun getDefinitionId(): Int {
        return id
    }

    fun stringWidth(var1: String): Int {
        return if (var1.isEmpty()) { // L: 127
            0
        } else {
            println(advances.toTypedArray().contentDeepToString())
            var var3 = -1 // L: 129
            var var4 = 0 // L: 130
            for (var5 in var1.indices) { // L: 131
                var var6 = var1[var5] // L: 132
                if (var6.code == 160) { // L: 153
                    var6 = ' '
                }
                var4 += advances[((charToByteCp1252(var6) and 255.toByte()).toInt())]
                if (var3 != -1) { // L: 156
                    var4 += kerning[var6.code + (var3 shl 8)]
                }
                var3 = var6.code // L: 157
            }
            var4 // L: 160
        }
    }

    fun charToByteCp1252(var0: Char): Byte {
        val var1 = if (var0.code in 1..127 || var0.code in 160..255) { // L: 12
            var0.code.toByte()
        } else if (var0.code == 8364) { // L: 13
            -128
        } else if (var0.code == 8218) { // L: 14
            -126
        } else if (var0.code == 402) { // L: 15
            -125
        } else if (var0.code == 8222) { // L: 16
            -124
        } else if (var0.code == 8230) { // L: 17
            -123
        } else if (var0.code == 8224) { // L: 18
            -122
        } else if (var0.code == 8225) { // L: 19
            -121
        } else if (var0.code == 710) { // L: 20
            -120
        } else if (var0.code == 8240) { // L: 21
            -119
        } else if (var0.code == 352) { // L: 22
            -118
        } else if (var0.code == 8249) { // L: 23
            -117
        } else if (var0.code == 338) { // L: 24
            -116
        } else if (var0.code == 381) { // L: 25
            -114
        } else if (var0.code == 8216) {
            -111 // L: 26
        } else if (var0.code == 8217) { // L: 27
            -110
        } else if (var0.code == 8220) { // L: 28
            -109
        } else if (var0.code == 8221) { // L: 29
            -108
        } else if (var0.code == 8226) { // L: 30
            -107
        } else if (var0.code == 8211) { // L: 31
            -106
        } else if (var0.code == 8212) { // L: 32
            -105
        } else if (var0.code == 732) { // L: 33
            -104
        } else if (var0.code == 8482) { // L: 34
            -103
        } else if (var0.code == 353) { // L: 35
            -102
        } else if (var0.code == 8250) { // L: 36
            -101
        } else if (var0.code == 339) { // L: 37
            -100
        } else if (var0.code == 382) { // L: 38
            -98
        } else if (var0.code == 376) { // L: 39
            -97
        } else {
            63 // L: 40
        }
        return var1 // L: 41
    }

}