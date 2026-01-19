package net.cozystudios.squish.item;

import net.minecraft.item.FoodComponent;

//? if fabric {

//? }

public class SquishItems {

    public static final FoodComponent LOLLIPOP_FOOD = new FoodComponent.Builder()
            .hunger(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent SQUISH_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(4).saturationModifier(2.0f).snack().alwaysEdible().build();

    public static final FoodComponent BITTER_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.5f).snack().alwaysEdible().build();

    public static final FoodComponent EXPLOSIVE_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent POPPY_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(3).saturationModifier(1.5f).snack().alwaysEdible().build();

}
