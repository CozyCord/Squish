package net.cozystudios.squish.mixin.compat;

import net.cozystudios.squish.util.SquishId;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import vazkii.patchouli.client.book.BookCategory;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.common.book.Book;

@Mixin(targets = "vazkii.patchouli.client.book.gui.GuiBookLanding", remap = false)
public abstract class PatchouliHideEntryIndexMixin extends GuiBook {

    private static final Identifier SQUISH_BOOK_ID = SquishId.of("squish", "squish_guidebook");

    protected PatchouliHideEntryIndexMixin(Book book, Text title) {
        super(book, title);
    }

    @Inject(method = "addCategoryButton", at = @At("HEAD"), cancellable = true)
    private void squish$hideEntryIndex(int i, BookCategory category, CallbackInfo ci) {
        if (category == null && this.book != null && SQUISH_BOOK_ID.equals(this.book.id)) {
            ci.cancel();
        }
    }
}
