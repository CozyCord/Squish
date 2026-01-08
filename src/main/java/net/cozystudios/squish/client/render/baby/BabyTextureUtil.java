package net.cozystudios.squish.client.render.baby;

import net.cozystudios.squish.Squish;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.Locale;

public final class BabyTextureUtil {
    private BabyTextureUtil() {}

    public enum TempBand { COLD, TEMPERATE, WARM }

    public static Identifier squishBaby(String fileName) {
        return new Identifier(Squish.MOD_ID, "textures/entity/baby/" + fileName);
    }

    public static TempBand biomeBand(Entity e) {
        float t = e.getWorld().getBiome(e.getBlockPos()).value().getTemperature();

        if (t <= 0.15f) return TempBand.COLD;
        if (t >= 1.0f) return TempBand.WARM;
        return TempBand.TEMPERATE;
    }

    public static String baseName(Identifier id) {
        String p = id.getPath(); // ex: textures/entity/cat/tabby.png
        int slash = p.lastIndexOf('/');
        String last = (slash >= 0) ? p.substring(slash + 1) : p;
        if (last.endsWith(".png")) last = last.substring(0, last.length() - 4);
        return last.toLowerCase(Locale.ROOT);
    }

    public static int stableIndex(Entity e, int mod) {
        if (mod <= 1) return 0;
        long msb = e.getUuid().getMostSignificantBits();
        long lsb = e.getUuid().getLeastSignificantBits();
        long x = msb ^ lsb;
        // spread bits
        x ^= (x >>> 33);
        x *= 0xff51afd7ed558ccdL;
        x ^= (x >>> 33);
        x *= 0xc4ceb9fe1a85ec53L;
        x ^= (x >>> 33);
        int v = (int) (x & 0x7fffffff);
        return v % mod;
    }
}