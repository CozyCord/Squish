package net.cozystudios.squish.registry.block;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import net.cozystudios.squish.registry.block.entity.MeltedSugarBlockEntity;
import net.cozystudios.squish.registry.block.entity.SquishBlockEntities;
import net.cozystudios.squish.util.StackNbt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
//? if >1.20.4 {
import com.mojang.serialization.MapCodec;
//?}

@SuppressWarnings({"deprecation"})
public class MeltedSugarBlock extends BlockWithEntity {

    //? if >1.20.4 {
    public static final MapCodec<MeltedSugarBlock> CODEC = createCodec(MeltedSugarBlock::new);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
    //?}

    private static final VoxelShape COLLISION = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9375, 1.0);
    public static final BooleanProperty PRESERVED = BooleanProperty.of("preserved");

    public MeltedSugarBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PRESERVED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PRESERVED);
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
        //? if <=1.20.4 {
        /*return (!state.get(PRESERVED) && !world.isClient)
                ? checkType(type, SquishBlockEntities.MELTED_SUGAR_BE, MeltedSugarBlockEntity::tickServer)
                : null;
        *///?} else {
        return (!state.get(PRESERVED) && !world.isClient)
                ? validateTicker(type, SquishBlockEntities.MELTED_SUGAR_BE, MeltedSugarBlockEntity::tickServer)
                : null;
        //?}
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

    //? if <=1.20.4 {
    /*@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              net.minecraft.entity.player.PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (!state.get(PRESERVED) && stack.isOf(Items.HONEYCOMB)) {
            world.setBlockState(pos, state.with(PRESERVED, true), 3);
            world.playSound(null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isClient) {
                spawnWaxParticles((ServerWorld) world, pos);
            }
            if (!player.getAbilities().creativeMode) stack.decrement(1);
            return ActionResult.SUCCESS;
        }

        if (state.get(PRESERVED) && stack.isIn(ItemTags.AXES)) {
            world.setBlockState(pos, state.with(PRESERVED, false), 3);
            world.playSound(null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isClient) {
                spawnWaxParticles((ServerWorld) world, pos);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
    *///?} else {
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos,
                              net.minecraft.entity.player.PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getMainHandStack();

        if (!state.get(PRESERVED) && stack.isOf(Items.HONEYCOMB)) {
            world.setBlockState(pos, state.with(PRESERVED, true), 3);
            world.playSound(null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isClient) {
                spawnWaxParticles((ServerWorld) world, pos);
            }
            if (!player.getAbilities().creativeMode) stack.decrement(1);
            return ActionResult.SUCCESS;
        }

        if (state.get(PRESERVED) && stack.isIn(ItemTags.AXES)) {
            world.setBlockState(pos, state.with(PRESERVED, false), 3);
            world.playSound(null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isClient) {
                spawnWaxParticles((ServerWorld) world, pos);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
    //?}

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> drops = super.getDroppedStacks(state, builder);
        if (state.get(PRESERVED)) {
            for (ItemStack s : drops) {
                if (s.isOf(this.asItem())) {
                    StackNbt.putBoolean(s, "Preserved", true);
                }
            }
        }
        return drops;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState base = this.getDefaultState();
        ItemStack stack = ctx.getStack();
        boolean preserved = StackNbt.getBoolean(stack, "Preserved");
        return base.with(PRESERVED, preserved);
    }

    private static void spawnWaxParticles(ServerWorld world, BlockPos pos) {
        float r = 207 / 255f;
        float g = 189 / 255f;
        float b = 223 / 255f;
        DustParticleEffect lilacDust = new DustParticleEffect(new Vector3f(r, g, b), 1.0f);

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        world.spawnParticles(lilacDust, x, y, z, 20, 0.5, 0.5, 0.5, 0.05);
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
