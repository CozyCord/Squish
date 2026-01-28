package net.cozystudios.squish.registry.item;

//? if >1.20.4 {
import net.minecraft.component.type.FoodComponent;
//?} else {
/*import net.minecraft.item.FoodComponent;
*///?}

public class SquishItems {

    //? if >1.20.4 {
    public static final FoodComponent LOLLIPOP_FOOD = new FoodComponent.Builder()
            .nutrition(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent SQUISH_CANDY_FOOD = new FoodComponent.Builder()
            .nutrition(4).saturationModifier(2.0f).snack().alwaysEdible().build();

    public static final FoodComponent BITTER_CANDY_FOOD = new FoodComponent.Builder()
            .nutrition(1).saturationModifier(0.5f).snack().alwaysEdible().build();

    public static final FoodComponent EXPLOSIVE_CANDY_FOOD = new FoodComponent.Builder()
            .nutrition(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent POPPY_CANDY_FOOD = new FoodComponent.Builder()
            .nutrition(3).saturationModifier(1.5f).snack().alwaysEdible().build();

    public static final FoodComponent ENDER_CANDY_FOOD = new FoodComponent.Builder()
            .nutrition(3).saturationModifier(1.5f).snack().alwaysEdible().build();

    public static final FoodComponent SKELLY_CANDY_FOOD = new FoodComponent.Builder()
            .nutrition(3).saturationModifier(1.5f).snack().alwaysEdible().build();
    //?} else {
    /*public static final FoodComponent LOLLIPOP_FOOD = new FoodComponent.Builder()
            .hunger(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent SQUISH_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(4).saturationModifier(2.0f).snack().alwaysEdible().build();

    public static final FoodComponent BITTER_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.5f).snack().alwaysEdible().build();

    public static final FoodComponent EXPLOSIVE_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent POPPY_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(3).saturationModifier(1.5f).snack().alwaysEdible().build();

    public static final FoodComponent ENDER_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(3).saturationModifier(1.5f).snack().alwaysEdible().build();

    public static final FoodComponent SKELLY_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(3).saturationModifier(1.5f).snack().alwaysEdible().build();
    *///?}

}
