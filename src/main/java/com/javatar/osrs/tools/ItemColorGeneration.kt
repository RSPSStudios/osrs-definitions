package com.javatar.osrs.tools

import com.javatar.osrs.definitions.impl.ItemDefinition
import com.javatar.osrs.definitions.impl.ModelDefinition

object ItemColorGeneration {

    fun ItemDefinition.setColor(color: Int, newColor: Int) {
        val index = colorFind.indexOf(color.toShort())
        if(index < colorReplace.size) {
            colorReplace[index] = newColor.toShort()
        }
    }

    fun generateOriginalColors(model: ModelDefinition) : ShortArray {
        return model.faceColors.toHashSet().toShortArray()
    }

    fun generateOriginalTextures(model : ModelDefinition) : ShortArray {
        return model.faceTextures.toHashSet().toShortArray()
    }

    fun applyOriginalColorsTo(def: ItemDefinition, colors: ShortArray) {
        def.colorFind = colors.copyOf()
        def.colorReplace = colors.copyOf()
    }

}