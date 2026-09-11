package net.cozystudios.squish.compat.jei;

//? if <=1.20.4 {
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.cozystudios.squish.Squish;
import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EssenceInfusionCategory implements IRecipeCategory<EssenceInfusionRecipe> {

    public static final RecipeType<EssenceInfusionRecipe> TYPE =
            new RecipeType<>(new Identifier(Squish.MOD_ID, "essence_infusion"), EssenceInfusionRecipe.class);

    private static final int WIDTH = 210;
    private static final int HEIGHT = 52;
    private static final int TEXT_COLOR = 0x555555;

    private final IDrawable background;
    private final IDrawable icon;

    public EssenceInfusionCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(WIDTH, HEIGHT);
        this.icon = helper.createDrawableItemStack(new ItemStack(RegistryHelper.SQUISH_ESSENCE));
    }

    @Override
    public RecipeType<EssenceInfusionRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("category.squish.essence_infusion");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EssenceInfusionRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 47, 6).addItemStack(recipe.lollipop());
        builder.addSlot(RecipeIngredientRole.INPUT, 95, 6).addItemStack(recipe.essence());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 147, 6).addItemStack(recipe.result());
    }

    @Override
    public void draw(EssenceInfusionRecipe recipe, IRecipeSlotsView view, DrawContext graphics, double mouseX, double mouseY) {
        TextRenderer tr = MinecraftClient.getInstance().textRenderer;

        graphics.drawText(tr, "+", 77, 10, TEXT_COLOR, false);
        graphics.drawText(tr, "→", 125, 10, TEXT_COLOR, false);

        Text hint1 = Text.translatable("category.squish.essence_infusion.hint1");
        Text hint2 = Text.translatable("category.squish.essence_infusion.hint2");
        graphics.drawText(tr, hint1, (WIDTH - tr.getWidth(hint1)) / 2, 28, TEXT_COLOR, false);
        graphics.drawText(tr, hint2, (WIDTH - tr.getWidth(hint2)) / 2, 40, TEXT_COLOR, false);
    }
}
//?} else {
/*public class EssenceInfusionCategory {}
*///?}
