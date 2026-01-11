package net.cozystudios.squish.mixin.compat;

import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "vazkii.patchouli.client.book.gui.GuiBook")
public abstract class PatchouliJeiKeybindCrashFixMixin extends Screen {

    protected PatchouliJeiKeybindCrashFixMixin() {
        super(null);
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void squish$avoidPatchouliJeiRuntimeNpe(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (keyCode != GLFW.GLFW_KEY_R && keyCode != GLFW.GLFW_KEY_U) return;

        try {
            Class<?> plugin = Class.forName("vazkii.patchouli.client.jei.PatchouliJeiPlugin");
            var field = plugin.getDeclaredField("jeiRuntime");
            field.setAccessible(true);

            Object runtime = field.get(null);
            if (runtime == null) {
                cir.setReturnValue(false);
            }
        } catch (Throwable ignored) {
        }
    }
}
