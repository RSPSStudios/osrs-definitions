package com.javatar.osrs.definitions;

public interface Definition {

    int getDefinitionId();
    default String getName() {
        return this.getClass().getSimpleName();
    }

}
