package com.javatar.osrs.tools

import com.javatar.osrs.definitions.impl.ItemDefinition

object ItemGeneration {

    fun generateBankNoteItem(item: ItemDefinition, bankNoteId: Int, templateId : Int = 799) : ItemDefinition {
        val newNoteDef = ItemDefinition(bankNoteId)
        newNoteDef.notedID = item.id
        newNoteDef.notedTemplate = templateId
        item.notedID = newNoteDef.id
        item.notedTemplate = -1
        return newNoteDef
    }

    fun generatePlaceholderItem(item: ItemDefinition, placeholderId: Int, templateId: Int = 14401) : ItemDefinition {
        val newPlaceholerDef = ItemDefinition(placeholderId)
        newPlaceholerDef.placeholderId = item.id
        newPlaceholerDef.placeholderTemplateId = templateId
        item.placeholderId = newPlaceholerDef.id
        item.placeholderTemplateId = -1
        return newPlaceholerDef
    }

}