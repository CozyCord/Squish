package net.cozystudios.squish.block.entity;

import net.cozystudios.squish.block.MeltedSugarBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//? if fabric {
import net.cozystudios.squish.fabric.RegistryHelper;
//? }

public class MeltedSugarBlockEntity extends BlockEntity {
    private static final int HARDEN_TICKS = 1200;

    private int age = 0;

    public MeltedSugarBlockEntity(BlockPos pos, BlockState state) {
        super(net.cozystudios.squish.block.entity.SquishBlockEntities.MELTED_SUGAR_BE, pos, state);
    }

    public static void tickServer(World world, BlockPos pos, BlockState state, MeltedSugarBlockEntity be) {
        if (world.isClient) return;

        if (state.get(MeltedSugarBlock.PRESERVED)) return;

        be.age++;
        if (be.age >= HARDEN_TICKS) {
            world.setBlockState(pos, RegistryHelper.HARDENED_SUGAR_BLOCK.getDefaultState(), 3);
            world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.BLOCKS, 0.8f, 1.2f);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Age", age);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        age = nbt.getInt("Age");
    }
}
