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

package com.javatar.osrs.definitions.impl;


import com.javatar.osrs.definitions.Definition;

import java.util.Map;

public class NpcDefinition implements Definition {
    public int id;
    public String name = "null";
    public int size = 1;
    public int[] models;
    public int[] chatheadModels;
    public int standingAnimation = -1;
    public int rotateLeftAnimation = -1;
    public int rotateRightAnimation = -1;
    public int walkingAnimation = -1;
    public int rotate180Animation = -1;
    public int rotate90RightAnimation = -1;
    public int rotate90LeftAnimation = -1;
    public short[] recolorToFind;
    public short[] recolorToReplace;
    public short[] retextureToFind;
    public short[] retextureToReplace;
    public String[] actions = new String[5];
    public boolean isMinimapVisible = true;
    public int combatLevel = -1;
    public int widthScale = 128;
    public int heightScale = 128;
    public boolean hasRenderPriority;
    public int ambient;
    public int contrast;
    public int headIcon = -1;
    public int rotationSpeed = 32;
    public int[] configs;
    public int varbitId = -1;
    public int varpIndex = -1;
    public int category = 0;
    public boolean isInteractable = true;
    public boolean rotationFlag = true;
    public boolean isPet;
    public Map<Integer, Object> params;

    public NpcDefinition(int id) {
        this.id = id;
    }

    @Override
    public int getDefinitionId() {
        return this.id;
    }

    @Override
    public String getName() {
        return name;
    }
}
