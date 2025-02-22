package net.gordyjack.jaavaa.block.custom;

import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.block.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import net.minecraft.world.tick.*;
import org.jetbrains.annotations.*;

@SuppressWarnings("deprecation")
public class Blocktant
extends Block
implements Waterloggable, VoxelShapeUtils {
    public static final IntProperty POSITION = JAAVAABlockProperties.BLOCKTANT_POSITION;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(0, 0, 0, 8, 8, 8);

    public Blocktant(Settings settings) {
        super(settings);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int existingPosition = state.get(POSITION);
        VoxelShape returnShape = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
        if ((existingPosition & (1 << 0)) != 0) { //Down North West
            returnShape = mergeShapes(returnShape, BASE_SHAPE);
        }
        if ((existingPosition & (1 << 1)) != 0) { //Down North East
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.EAST, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        if ((existingPosition & (1 << 2)) != 0) { //Down South West
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.SOUTH, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        if ((existingPosition & (1 << 3)) != 0) { //Down South East
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.EAST, 8);
            translatedShape = translateShape(translatedShape, Direction.SOUTH, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        if ((existingPosition & (1 << 4)) != 0) { //Up North West
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.UP, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        if ((existingPosition & (1 << 5)) != 0) { //Up North East
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.UP, 8);
            translatedShape = translateShape(translatedShape, Direction.EAST, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        if ((existingPosition & (1 << 6)) != 0) { //Up South West
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.UP, 8);
            translatedShape = translateShape(translatedShape, Direction.SOUTH, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        if ((existingPosition & (1 << 7)) != 0) { //Up South East
            VoxelShape translatedShape = translateShape(BASE_SHAPE, Direction.UP, 8);
            translatedShape = translateShape(translatedShape, Direction.EAST, 8);
            translatedShape = translateShape(translatedShape, Direction.SOUTH, 8);
            returnShape = mergeShapes(returnShape, translatedShape);
        }
        return returnShape;
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        
        BlockState existingState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        Block existingBlock = existingState.getBlock();
        int existingPosition = existingBlock == blockState.getBlock() ? existingState.get(POSITION) : 0;
        int placePosition = getPlacePosition(ctx);
        int newPosition = existingPosition | placePosition;
        
        return blockState.with(POSITION, newPosition)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }
    private static int getPlacePosition(ItemPlacementContext ctx) {
        Vec3d hitPos = ctx.getHitPos();
        double xHitPos = hitPos.x;
        double yHitPos = hitPos.y;
        double zHitPos = hitPos.z;
        
        BlockPos blockPos = ctx.getBlockPos();
        double xBlockPos = blockPos.getX();
        double yBlockPos = blockPos.getY();
        double zBlockPos = blockPos.getZ();
        
        double relativeXPos = xHitPos - xBlockPos;
        double relativeYPos = yHitPos - yBlockPos;
        double relativeZPos = zHitPos - zBlockPos;
        
        Direction side = ctx.getSide();
        if (relativeZPos == 0.5 && side == Direction.SOUTH) {
            relativeZPos = Math.nextUp(relativeZPos);
        }
        if (relativeYPos == 0.5 && side == Direction.UP) {
            relativeYPos = Math.nextUp(relativeYPos);
        }
        if (relativeXPos == 0.5 && side == Direction.EAST) {
            relativeXPos = Math.nextUp(relativeXPos);
        }
        return getPlacePosition(relativeZPos, relativeYPos, relativeXPos);
    }
    private static int getPlacePosition(double relativeZPos, double relativeYPos, double relativeXPos) {
        boolean north = relativeZPos <= .5;
        boolean south = relativeZPos > .5;
        boolean down = relativeYPos <=.5;
        boolean up = relativeYPos > .5;
        boolean west = relativeXPos <= .5;
        boolean east = relativeXPos > .5;

        int placePosition = 0;

        if (down && north && west) {
            placePosition = 0b00000001;
        } else if (down && north && east) {
            placePosition = 0b00000010;
        } else if (down && south && west) {
            placePosition = 0b00000100;
        } else if (down && south && east) {
            placePosition = 0b00001000;
        } else if (up && north && west) {
            placePosition = 0b00010000;
        } else if (up && north && east) {
            placePosition = 0b00100000;
        } else if (up && south && west) {
            placePosition = 0b01000000;
        } else if (up && south && east) {
            placePosition = 0b10000000;
        }
        return placePosition;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POSITION, WATERLOGGED);
    }
    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return state.get(POSITION) != 0b11111111;
    }
    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        int existingPosition = state.get(POSITION);

        if (existingPosition == 0b11111111 || !context.getStack().isOf(this.asItem())) {
            return false;
        } else if (context.canReplaceExisting()) {
            return (existingPosition & getPlacePosition(context)) == 0;
        } else {
            return true;
        }
    }
    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (state.get(POSITION) != 0b11111111) {
            return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
        }
        return false;
    }
    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        if (state.get(POSITION) != 0b11111111) {
            return Waterloggable.super.canFillWithFluid(player, world, pos, state, fluid);
        }
        return false;
    }
    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return switch (type) {
            case LAND, AIR -> false;
            case WATER -> state.get(WATERLOGGED);
        };
    }
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
                                                   BlockPos pos, Direction direction, BlockPos neighborPos,
                                                   BlockState neighborState, Random random) {
        if (state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }
}
