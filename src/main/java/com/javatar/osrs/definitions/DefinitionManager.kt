package com.javatar.osrs.definitions

/**
 * @author David Schlachter <davidschlachter96@gmail.com>
 * @created March 13 2021
 */

open class DefinitionManager<T : Definition, L : DeserializeDefinition<T>>(
    val loader: L
) {

    private val definitions = mutableMapOf<Int, T>()

    open fun getDefinition(id: Int): T = definitions[id]!!

    operator fun contains(id: Int) = definitions.containsKey(id)

    fun values(): List<T> = definitions.values.toList()

    fun add(def: T) {
        definitions[def.definitionId] = def
    }

    fun remove(id: Int) {
        definitions.remove(id)
    }

    fun getDefinition(id: Int, data: ByteArray) : T {
        if(definitions.containsKey(id)) {
            return definitions[id]!!
        }
        return load(id, data)
    }

    fun load(id: Int, data: ByteArray): T {
        val definition = loader.deserialize(id, data)
        add(definition)
        return definition
    }
}
