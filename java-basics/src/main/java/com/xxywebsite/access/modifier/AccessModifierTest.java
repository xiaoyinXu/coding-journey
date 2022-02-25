package com.xxywebsite.access.modifier;

import lombok.Data;

/**
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
public class AccessModifierTest {
    private int x;
    int y;
    protected int z;
    public int k;

    // "private" on purpose
    protected int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

