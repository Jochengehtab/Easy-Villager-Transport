package de.jochengehtab.Events.PlayerBlockBreakEvents;

import de.jochengehtab.Controls.AddNewControl;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Before implements PlayerBlockBreakEvents.Before {

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (AddNewControl.allowed){
            if (state.getBlock() == Blocks.CARROTS || state.getBlock() == Blocks.POTATOES || state.getBlock() == Blocks.BEETROOTS || state.getBlock() == Blocks.WHEAT) {
                CropBlock cropBlock = (CropBlock) state.getBlock();

                if (state.getBlock() == Blocks.BEETROOTS){
                    return state.get(cropBlock.getAgeProperty()) > 2;
                }
                else{
                    return state.get(cropBlock.getAgeProperty()) > 6;
                }
            }
        }
        return true;
    }
}
