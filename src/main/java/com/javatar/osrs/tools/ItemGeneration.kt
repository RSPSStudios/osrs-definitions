package com.javatar.osrs.tools

import com.javatar.osrs.definitions.impl.ItemDefinition

object ItemGeneration {

    fun generateBankNoteItem(item: ItemDefinition, offsetId: Int = 1, templateId : Int = 799) : ItemDefinition {
        val newNoteDef = ItemDefinition((item.id + offsetId))
        newNoteDef.notedID = item.id
        newNoteDef.notedTemplate = templateId
        item.notedID = newNoteDef.id
        item.notedTemplate = -1
        return newNoteDef
    }

    fun generatePlaceholderItem(item: ItemDefinition, offsetId: Int = 1) : ItemDefinition {
        return ItemDefinition(offsetId)
    }

}