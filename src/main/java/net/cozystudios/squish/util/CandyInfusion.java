package net.cozystudios.squish.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public final class CandyInfusion {
    private CandyInfusion() {}

    public static final String KEY = "SquishInfusion";
    public static final int MIN = 1;
    public static final int MAX = 5;

    public static int getLevel(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(KEY)) return MIN;

        int lvl = nbt.getInt(KEY);
        return clamp(lvl);
    }

    public static void setLevel(ItemStack stack, int level) {
        stack.getOrCreateNbt().putInt(KEY, clamp(level));
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