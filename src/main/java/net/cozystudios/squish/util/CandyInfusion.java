package net.cozystudios.squish.util;

import net.minecraft.item.ItemStack;

public final class CandyInfusion {
    private CandyInfusion() {}

    public static final String KEY = "SquishInfusion";
    public static final int MIN = 1;
    public static final int MAX = 5;

    public static int getLevel(ItemStack stack) {
        if (!StackNbt.hasKey(stack, KEY)) return MIN;
        int lvl = StackNbt.getInt(stack, KEY);
        return clamp(lvl);
    }

    public static void setLevel(ItemStack stack, int level) {
        StackNbt.putInt(stack, KEY, clamp(level));
    }

    public static int increment(ItemStack stack) {
        int next = clamp(getLevel(stack) + 1);
        setLevel(stack, next);
        return next;
    }

    private static int clamp(int lvl) {
        if (lvl < MIN) return MIN;
        if (lvl > MAX) return MAX;
        return lvl;
    }
}