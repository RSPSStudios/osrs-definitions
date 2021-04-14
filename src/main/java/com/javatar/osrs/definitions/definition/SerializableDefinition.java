package com.javatar.osrs.definitions.definition;

import com.javatar.osrs.definitions.Definition;

@FunctionalInterface
public interface SerializableDefinition<T extends Definition> {

    byte[] serialize(T def);

}
