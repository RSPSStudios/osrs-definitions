package com.javatar.osrs.definitions;

public interface DefinitionManager<T extends Definition> {

    void add(T def);
    void remove(T def);

    T get(int id);
    T load(int id, byte[] data);
}
