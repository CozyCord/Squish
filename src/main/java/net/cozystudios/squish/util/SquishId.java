package net.cozystudios.squish.util;

import net.cozystudios.squish.Squish;
import net.minecraft.util.Identifier;

public final class SquishId {

    private SquishId() {}

    public static Identifier of(String path) {
        return of(Squish.MOD_ID, path);
    }

    public static Identifier of(String namespace, String path) {
        //? if >1.20.4 {
        return Identifier.of(namespace, path);
        //?} else {
        /*return new Identifier(namespace, path);
        *///?}
    }
}
