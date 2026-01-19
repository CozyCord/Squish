package net.cozystudios.squish.mixin.compat;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.patchouli.client.book.BookCategory;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.common.book.Book;

/**
 * Mixin to hide the Entry Index button from the Squish Patchouli book only.
 * The Entry Index is added in GuiBookLanding.addCategoryButton(i, null).
 * We cancel the method when category is null to prevent the Entry Index from appearing.
 */
@Mixin(targets = "vazkii.patchouli.client.book.gui.GuiBookLanding", remap = false)
public abstract class PatchouliHideEntryIndexMixin extends GuiBook {

    private static final Identifier SQUISH_BOOK_ID = new Identifier("squish", "squish_guidebook");

    // Dummy constructor required for extending GuiBook
    protected PatchouliHideEntryIndexMixin(Book book, Text title) {
        super(book, title);
    }

    @Inject(method = "addCategoryButton", at = @At("HEAD"), cancellable = true)
    private void squish$hideEntryIndex(int i, BookCategory category, CallbackInfo ci) {
        // When category is null, Patchouli adds the Entry Index button
        // Only hide it for the Squish guidebook, not other mods' books
        if (category == null && this.book != null && SQUISH_BOOK_ID.equals(this.book.id)) {
            ci.cancel();
        }
    }
}
