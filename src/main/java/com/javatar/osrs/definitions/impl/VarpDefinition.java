package com.javatar.osrs.definitions.impl;

import com.javatar.osrs.definitions.Definition;

public class VarpDefinition implements Definition {

    int id;
    int type;

    public VarpDefinition(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getDefinitionId() {
        return id;
    }

    @Override
    public String getName() {
        return VarpDefinition.class.getSimpleName();
    }
}
