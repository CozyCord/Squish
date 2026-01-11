package net.cozystudios.squish.registry.item;

import net.minecraft.item.FoodComponent;

//? if fabric {

//? }

public class SquishItems {

    public static final FoodComponent LOLLIPOP_FOOD = new FoodComponent.Builder()
            .hunger(2).saturationModifier(1.0f).snack().alwaysEdible().build();

    public static final FoodComponent SQUISH_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(4).saturationModifier(2.0f).snack().alwaysEdible().build();

}
