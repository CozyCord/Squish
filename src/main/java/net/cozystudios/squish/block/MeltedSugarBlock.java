package net.cozystudios.squish.block;

import net.cozystudios.squish.block.entity.MeltedSugarBlockEntity;
import net.cozystudios.squish.block.entity.SquishBlockEntities;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MeltedSugarBlock extends BlockWithEntity {

    private static final VoxelShape COLLISION = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9375, 1.0);

    public MeltedSugarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MeltedSugarBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, SquishBlockEntities.MELTED_SUGAR_BE, MeltedSugarBlockEntity::tickServer);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSneaking()) {
            entity.setVelocity(entity.getVelocity().multiply(0.4, 1.0, 0.4));
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isTouchingSide(pos, entity)) {
            if (entity.getVelocity().y < -0.08D) {
                entity.setVelocity(entity.getVelocity().x * 0.9, -0.05D, entity.getVelocity().z * 0.9);
                entity.fallDistance = 0.0F;

                if (world.isClient && world.random.nextFloat() < 0.1F) {
                    world.addParticle(ParticleTypes.LANDING_HONEY, entity.getX(), entity.getY() + 0.1, entity.getZ(), 0.0, 0.0, 0.0);
                }
                if (!world.isClient && world.random.nextFloat() < 0.02F) {
                    world.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, SoundCategory.BLOCKS, 0.2F, 1.0F);
                }
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance * 0.2F, 0.0F, world.getDamageSources().fall());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!world.isClient) return;

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.8;
        double z = pos.getZ() + 0.5;

        if (random.nextFloat() < 0.12f) {
            world.addParticle(ParticleTypes.BUBBLE_POP, x, y + 0.1, z, 0.0, 0.05, 0.0);
        }
        if (random.nextFloat() < 0.03f) {
            world.playSound(x, y, z, SoundEvents.BLOCK_HONEY_BLOCK_HIT, SoundCategory.BLOCKS, 0.15f, 1.05f + random.nextFloat() * 0.2f, false);
        }
    }

    private static boolean isTouchingSide(BlockPos pos, Entity entity) {
        var box = entity.getBoundingBox();
        boolean verticallyOverlaps = box.maxY > pos.getY() && box.minY < pos.getY() + 1.0;
        if (!verticallyOverlaps) return false;

        double cx = entity.getX() - pos.getX();
        double cz = entity.getZ() - pos.getZ();

        return (cx < 0.2 || cx > 0.8 || cz < 0.2 || cz > 0.8);
    }
}