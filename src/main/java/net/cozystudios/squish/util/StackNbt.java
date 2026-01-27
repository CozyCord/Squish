package net.cozystudios.squish.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
//? if >1.20.4 {
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
//?}

public final class StackNbt {

    private StackNbt() {}

    public static NbtCompound get(ItemStack stack) {
        //? if >1.20.4 {
        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);
        return component != null ? component.copyNbt() : null;
        //?} else {
        /*return stack.getNbt();
        *///?}
    }

    public static NbtCompound getOrCreate(ItemStack stack) {
        //? if >1.20.4 {
        NbtComponent component = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        return component.copyNbt();
        //?} else {
        /*return stack.getOrCreateNbt();
        *///?}
    }

    public static void putInt(ItemStack stack, String key, int value) {
        //? if >1.20.4 {
        NbtCompound nbt = getOrCreate(stack);
        nbt.putInt(key, value);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        //?} else {
        /*stack.getOrCreateNbt().putInt(key, value);
        *///?}
    }

    public static void putString(ItemStack stack, String key, String value) {
        //? if >1.20.4 {
        NbtCompound nbt = getOrCreate(stack);
        nbt.putString(key, value);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        //?} else {
        /*stack.getOrCreateNbt().putString(key, value);
        *///?}
    }

    public static void putBoolean(ItemStack stack, String key, boolean value) {
        //? if >1.20.4 {
        NbtCompound nbt = getOrCreate(stack);
        nbt.putBoolean(key, value);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        //?} else {
        /*stack.getOrCreateNbt().putBoolean(key, value);
        *///?}
    }

    public static int getInt(ItemStack stack, String key) {
        NbtCompound nbt = get(stack);
        return nbt != null ? nbt.getInt(key) : 0;
    }

    public static boolean getBoolean(ItemStack stack, String key) {
        NbtCompound nbt = get(stack);
        return nbt != null && nbt.getBoolean(key);
    }

    public static boolean hasKey(ItemStack stack, String key) {
        NbtCompound nbt = get(stack);
        return nbt != null && nbt.contains(key);
    }

    public static boolean hasNbt(ItemStack stack) {
        //? if >1.20.4 {
        return stack.contains(DataComponentTypes.CUSTOM_DATA);
        //?} else {
        /*return stack.hasNbt();
        *///?}
    }
}
