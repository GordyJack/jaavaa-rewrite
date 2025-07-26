package net.gordyjack.jaavaa.block.custom;

import com.mojang.serialization.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.custom.entity.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.block.enums.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.stream.*;

public class RecyclingTableBlock extends BlockWithEntity {
    private static final MapCodec<RecyclingTableBlock> CODEC = createCodec(RecyclingTableBlock::new);
    public static final VoxelShape Z_FLOOR_SHAPE = Stream.of(
            Block.createCuboidShape(5, 3, 2, 11, 6, 14),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(6, 7, 4, 10, 15, 12),
            Block.createCuboidShape(10, 4, 7, 12, 9, 9),
            Block.createCuboidShape(10, 9, 6, 12, 13, 10),
            Block.createCuboidShape(4, 4, 7, 6, 9, 9),
            Block.createCuboidShape(4, 9, 6, 6, 13, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape X_FLOOR_SHAPE = Stream.of(
            Block.createCuboidShape(2, 3, 5, 14, 6, 11),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(4, 7, 6, 12, 15, 10),
            Block.createCuboidShape(7, 4, 10, 9, 9, 12),
            Block.createCuboidShape(6, 9, 10, 10, 13, 12),
            Block.createCuboidShape(7, 4, 4, 9, 9, 6),
            Block.createCuboidShape(6, 9, 4, 10, 13, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SOUTH_WALL_SHAPE = Stream.of(
            Block.createCuboidShape(5, 2, 3, 11, 14, 6),
            Block.createCuboidShape(0, 0, 0, 16, 16, 4),
            Block.createCuboidShape(6, 4, 7, 10, 12, 15),
            Block.createCuboidShape(10, 7, 4, 12, 9, 9),
            Block.createCuboidShape(10, 6, 9, 12, 10, 13),
            Block.createCuboidShape(4, 7, 4, 6, 9, 9),
            Block.createCuboidShape(4, 6, 9, 6, 10, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape WEST_WALL_SHAPE = Stream.of(
            Block.createCuboidShape(10, 2, 5, 13, 14, 11),
            Block.createCuboidShape(12, 0, 0, 16, 16, 16),
            Block.createCuboidShape(1, 4, 6, 9, 12, 10),
            Block.createCuboidShape(7, 7, 10, 12, 9, 12),
            Block.createCuboidShape(3, 6, 10, 7, 10, 12),
            Block.createCuboidShape(7, 7, 4, 12, 9, 6),
            Block.createCuboidShape(3, 6, 4, 7, 10, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape NORTH_WALL_SHAPE = Stream.of(
            Block.createCuboidShape(5, 2, 10, 11, 14, 13),
            Block.createCuboidShape(0, 0, 12, 16, 16, 16),
            Block.createCuboidShape(6, 4, 1, 10, 12, 9),
            Block.createCuboidShape(4, 7, 7, 6, 9, 12),
            Block.createCuboidShape(4, 6, 3, 6, 10, 7),
            Block.createCuboidShape(10, 7, 7, 12, 9, 12),
            Block.createCuboidShape(10, 6, 3, 12, 10, 7)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape EAST_WALL_SHAPE = Stream.of(
            Block.createCuboidShape(3, 2, 5, 6, 14, 11),
            Block.createCuboidShape(0, 0, 0, 4, 16, 16),
            Block.createCuboidShape(7, 4, 6, 15, 12, 10),
            Block.createCuboidShape(4, 7, 4, 9, 9, 6),
            Block.createCuboidShape(9, 6, 4, 13, 10, 6),
            Block.createCuboidShape(4, 7, 10, 9, 9, 12),
            Block.createCuboidShape(9, 6, 10, 13, 10, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape X_CEILING_SHAPE = Stream.of(
            Block.createCuboidShape(2, 10, 5, 14, 13, 11),
            Block.createCuboidShape(0, 12, 0, 16, 16, 16),
            Block.createCuboidShape(4, 1, 6, 12, 9, 10),
            Block.createCuboidShape(7, 7, 10, 9, 12, 12),
            Block.createCuboidShape(6, 3, 10, 10, 7, 12),
            Block.createCuboidShape(7, 7, 4, 9, 12, 6),
            Block.createCuboidShape(6, 3, 4, 10, 7, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape Z_CEILING_SHAPE = Stream.of(
            Block.createCuboidShape(5, 10, 2, 11, 13, 14),
            Block.createCuboidShape(0, 12, 0, 16, 16, 16),
            Block.createCuboidShape(6, 1, 4, 10, 9, 12),
            Block.createCuboidShape(4, 7, 7, 6, 12, 9),
            Block.createCuboidShape(4, 3, 6, 6, 7, 10),
            Block.createCuboidShape(10, 7, 7, 12, 12, 9),
            Block.createCuboidShape(10, 3, 6, 12, 7, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    private static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    private static final EnumProperty<BlockFace> FACE = Properties.BLOCK_FACE;

    //Constructor
    public RecyclingTableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(FACE, BlockFace.FLOOR));
    }
    //Overrides
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (state.get(FACE)) {
            case FLOOR -> {
                if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                    return Z_FLOOR_SHAPE;
                }
                return X_FLOOR_SHAPE;
            }
            case WALL -> {
                if (direction == Direction.NORTH) {
                    return NORTH_WALL_SHAPE;
                }
                if (direction == Direction.SOUTH) {
                    return SOUTH_WALL_SHAPE;
                }
                if (direction == Direction.EAST) {
                    return EAST_WALL_SHAPE;
                }
                return WEST_WALL_SHAPE;
            }
            case CEILING -> {
                if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                    return Z_CEILING_SHAPE;
                }
                return X_CEILING_SHAPE;
            }
        }
        return X_FLOOR_SHAPE;
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = this.getDefaultState()
                        .with(FACE, direction == Direction.UP ? BlockFace.CEILING : BlockFace.FLOOR)
                        .with(FACING, ctx.getHorizontalPlayerFacing());
            } else {
                blockState = this.getDefaultState().with(FACE, BlockFace.WALL).with(FACING, direction.getOpposite());
            }

            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }
        return null;
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    /**
     * {@return a new block entity instance}
     *
     * <p>For example:
     * <pre>{@code
     * @Override
     * public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
     *   return new MyBlockEntity(pos, state);
     * }
     * }</pre>
     *
     * @param pos
     * @param state
     * @implNote While this is marked as nullable, in practice this should never return {@code null}.
     * {@link PistonExtensionBlock} is the only block in vanilla that returns {@code null} inside the implementation.
     */
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RecyclingTableBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, JAAVAABlockEntityTypes.RECYCLING_TABLE_BLOCK_ENTITY_TYPE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof RecyclingTableBlockEntity recyclingBlockEntity) {
            player.openHandledScreen(recyclingBlockEntity);
        }
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            this.openScreen(world, pos, player);
        }
        return ActionResult.SUCCESS;
    }
}
