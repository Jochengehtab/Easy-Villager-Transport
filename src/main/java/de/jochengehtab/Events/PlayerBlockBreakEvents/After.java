package de.jochengehtab.Events.PlayerBlockBreakEvents;

import de.jochengehtab.Main;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class After implements PlayerBlockBreakEvents.After {
    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (state.getBlock() == Blocks.CARROTS){
            Main.LOGGER.info(state.getBlock().getName().toString());
            CarrotsBlock carrotsBlock = (CarrotsBlock) state.getBlock();
            BlockState carrots = Blocks.CARROTS.getDefaultState();

            if (state.get(carrotsBlock.getAgeProperty()) == 7){
                world.setBlockState(pos, carrots);
            }
        }

        if (state.getBlock() == Blocks.POTATOES){
            Main.LOGGER.info(state.getBlock().getName().toString());
            PotatoesBlock carrotsBlock = (PotatoesBlock) state.getBlock();

            if (state.get(carrotsBlock.getAgeProperty()) == 7){
                world.setBlockState(pos, Blocks.POTATOES.getDefaultState());
            }
        }

        if (state.getBlock() == Blocks.BEETROOTS){
            Main.LOGGER.info(state.getBlock().getName().toString());
            BeetrootsBlock carrotsBlock = (BeetrootsBlock) state.getBlock();

            if (state.get(carrotsBlock.getAgeProperty()) == 3){
                world.setBlockState(pos, Blocks.BEETROOTS.getDefaultState());
            }
        }

        if (state.getBlock() == Blocks.WHEAT){
            CropBlock cropBlock = (CropBlock) state.getBlock();
            if (state.get(cropBlock.getAgeProperty()) == 7){
                world.setBlockState(pos, Blocks.WHEAT.getDefaultState());
            }
        }
    }
}


