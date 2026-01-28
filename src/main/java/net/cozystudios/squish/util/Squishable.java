package net.cozystudios.squish.util;

public interface Squishable {
    boolean squish$isSquished();
    void squish$setSquished(boolean squished);

    default boolean squish$isUnsquishing() { return false; }
    default void squish$setUnsquishing(boolean unsquishing) { }
}