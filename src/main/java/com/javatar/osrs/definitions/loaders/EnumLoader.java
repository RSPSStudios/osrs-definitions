/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.javatar.osrs.definitions.loaders;

import com.javatar.osrs.definitions.DeserializeDefinition;
import com.javatar.osrs.definitions.impl.EnumDefinition;
import com.javatar.osrs.definitions.impl.cs2.ScriptVarType;
import com.javatar.osrs.definitions.io.InputStream;

public class EnumLoader implements DeserializeDefinition<EnumDefinition> {
    public EnumDefinition load(int id, byte[] b) {
        EnumDefinition def = new EnumDefinition();
        def.setId(id);
        if (b.length == 1 && b[0] == 0) {
            return def;
        }
        InputStream is = new InputStream(b);
        for (; ; ) {
            int opcode = is.readUnsignedByte();
            if (opcode == 0) {
                break;
            }

            processOp(opcode, def, is);
        }

        return def;
    }

    private void processOp(int opcode, EnumDefinition def, InputStream is) {
        switch (opcode) {
            case 1 -> def.setKeyType(ScriptVarType.forCharKey((char) is.readUnsignedByte()));
            case 2 -> def.setValType(ScriptVarType.forCharKey((char) is.readUnsignedByte()));
            case 3 -> def.setDefaultString(is.readString());
            case 4 -> def.setDefaultInt(is.readInt());
            case 5 -> {
                int size = is.readUnsignedShort();
                int[] keys = new int[size];
                String[] stringVals = new String[size];
                for (int index = 0; index < size; ++index) {
                    keys[index] = is.readInt();
                    stringVals[index] = is.readString();
                }
                def.setSize(size);
                def.setKeys(keys);
                def.setStringVals(stringVals);
            }
            case 6 -> {
                int size = is.readUnsignedShort();
                int[] keys = new int[size];
                int[] intVals = new int[size];
                for (int index = 0; index < size; ++index) {
                    keys[index] = is.readInt();
                    intVals[index] = is.readInt();
                }
                def.setSize(size);
                def.setKeys(keys);
                def.setIntVals(intVals);
            }
        }
    }

    @Override
    public EnumDefinition deserialize(int id, byte[] b) {
        return load(id, b);
    }
}
