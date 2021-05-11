package com.javatar.osrs.definitions.loaders

import com.javatar.osrs.definitions.DeserializeDefinition
import com.javatar.osrs.definitions.impl.ClientVarDefinition
import com.javatar.osrs.definitions.io.InputStream

class ClientVariableLoader : DeserializeDefinition<ClientVarDefinition> {
    override fun deserialize(id: Int, b: ByteArray): ClientVarDefinition {
        val def = ClientVarDefinition(id)
        val input = InputStream(b)
        while(true) {
            val opcode = input.readUnsignedByte()
            if(opcode == 0)
                break
            if(opcode == 2) {
                def.persisent = true
            }
        }
        return def
    }
}