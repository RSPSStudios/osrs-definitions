package com.javatar.osrs.definitions;

@FunctionalInterface
public interface DeserializeDefinition<T extends com.javatar.osrs.definitions.Definition> {

    T deserialize(final int id, byte[] b);

}
