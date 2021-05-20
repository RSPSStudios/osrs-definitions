package com.javatar.osrs.definitions.loaders

import com.javatar.osrs.definitions.DeserializeDefinition
import com.javatar.osrs.definitions.impl.FontMetricsDefinition
import kotlin.experimental.and

class FontMetricsLoader : DeserializeDefinition<FontMetricsDefinition> {
    override fun deserialize(id: Int, b: ByteArray): FontMetricsDefinition {
        val metrics = FontMetricsDefinition(id)

        metrics.advances = IntArray(256)

        if (b.size == 257) {
            for (index in metrics.advances.indices) {
                metrics.advances[index] = (b[index] and 255.toByte()).toInt()
            }
            metrics.ascent = (b[256] and 255.toByte()).toInt()
        } else {

            var var2 = 0
            val var1 = b


            for (var3 in 0..255) { // L: 64
                metrics.advances[var3] = (var1[var2++] and 255.toByte()).toInt()
            }

            val var10 = IntArray(256) // L: 65

            val var4 = IntArray(256) // L: 66


            var var5 = 0
            while (var5 < 256) {
                var10[var5] = (var1[var2++] and 255.toByte()).toInt()
                ++var5
            }
            var5 = 0
            while (var5 < 256) {
                var4[var5] = (var1[var2++] and 255.toByte()).toInt()
                ++var5
            }

            val var11 = Array(256) { byteArrayOf() }


            var var8: Int
            for (var6 in 0..255) {
                var11[var6] = ByteArray(var10[var6]) // L: 71
                var var7 = 0
                var8 = 0
                while (var8 < var11[var6].size) {
                    var7 += var1[var2++]
                    var11[var6][var8] = var7.toByte()
                    ++var8
                }
            }

            val var12 = Array(256) { byteArrayOf() }


            var var13: Int
            var13 = 0
            while (var13 < 256) {
                var12[var13] = ByteArray(var10[var13]) // L: 80
                var var14 = 0
                for (var9 in 0 until var12[var13].size) { // L: 82
                    var14 += var1[var2++]
                    var12[var13][var9] = var14.toByte()
                }
                ++var13
            }

            metrics.kerning = IntArray(65536)


            var13 = 0
            while (var13 < 256) {
                if (var13 != 32 && var13 != 160) { // L: 89
                    var8 = 0
                    while (var8 < 256) {
                        if (var8 != 32 && var8 != 160) { // L: 91
                            metrics.kerning[var8 + (var13 shl 8)] =
                                method5369(
                                    var11,
                                    var12,
                                    var4,
                                    metrics.advances,
                                    var10,
                                    var13,
                                    var8
                                )
                        }
                        ++var8
                    }
                }
                ++var13
            }

            metrics.ascent = var4[32] + var10[32]

        }

        return metrics
    }

    fun method5369(
        var0: Array<ByteArray>,
        var1: Array<ByteArray>,
        var2: IntArray,
        var3: IntArray,
        var4: IntArray,
        var5: Int,
        var6: Int
    ): Int {
        val var7 = var2[var5] // L: 100
        val var8 = var7 + var4[var5] // L: 101
        val var9 = var2[var6] // L: 102
        val var10 = var9 + var4[var6] // L: 103
        var var11 = var7 // L: 104
        if (var9 > var7) { // L: 105
            var11 = var9
        }
        var var12 = var8 // L: 106
        if (var10 < var8) { // L: 107
            var12 = var10
        }
        var var13 = var3[var5] // L: 108
        if (var3[var6] < var13) { // L: 109
            var13 = var3[var6]
        }
        val var14 = var1[var5] // L: 110
        val var15 = var0[var6] // L: 111
        var var16 = var11 - var7 // L: 112
        var var17 = var11 - var9 // L: 113
        for (var18 in var11 until var12) { // L: 114
            val var19 = var14[var16++] + var15[var17++] // L: 115
            if (var19 < var13) { // L: 116
                var13 = var19
            }
        }
        return -var13 // L: 118
    }

}