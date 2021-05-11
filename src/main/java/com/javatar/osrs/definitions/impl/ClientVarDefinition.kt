package com.javatar.osrs.definitions.impl

import com.javatar.osrs.definitions.Definition

class ClientVarDefinition(var id: Int = 0) : Definition {

    var persisent: Boolean = false

    override fun getDefinitionId(): Int {
        return id
    }
}