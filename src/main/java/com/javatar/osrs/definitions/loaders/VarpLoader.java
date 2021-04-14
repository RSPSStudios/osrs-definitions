package com.javatar.osrs.definitions.loaders;

import com.javatar.osrs.definitions.DeserializeDefinition;
import com.javatar.osrs.definitions.impl.VarpDefinition;
import com.javatar.osrs.definitions.io.InputStream;

public class VarpLoader implements DeserializeDefinition<VarpDefinition> {
    @Override
    public VarpDefinition deserialize(int id, byte[] b) {
        VarpDefinition def = new VarpDefinition(id);
        InputStream in = new InputStream(b);

        while(true) {
            int opcode = in.readUnsignedByte();
            if(opcode == 0)
                break;

            if(opcode == 5) {
                def.setType(in.readUnsignedShort());
            }

        }

        return def;
    }
}
