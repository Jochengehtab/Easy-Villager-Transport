package de.jochengehtab.mixin;

import de.jochengehtab.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Block.class)
public abstract class BlockDrops {

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            ordinal = 0),
            cancellable = true)
    private static void dropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            if (state.getBlock() == Blocks.CARROTS || state.getBlock() == Blocks.POTATOES){
                CropBlock cropBlock = (CropBlock) state.getBlock();
                if (state.get(cropBlock.getAgeProperty()) <= 6){
                    Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, tool).forEach(stack -> Block.dropStack(world, pos, stack));
                    ci.cancel();
                }
                List<ItemStack> itemStackList = Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, tool);
                Main.LOGGER.info("The List of ItemStacks is: " + itemStackList.toString() + ".");
                for (ItemStack itemStack : itemStackList){
                    if (!(itemStack.getCount() == 1)){
                        Block.dropStack(world, pos, itemStack);
                    }
                }
            }
            else if (state.getBlock() == Blocks.BEETROOTS || state.getBlock() == Blocks.WHEAT){
                CropBlock cropBlock = (CropBlock) state.getBlock();
                if (state.getBlock() == Blocks.BEETROOTS){
                    if (state.get(cropBlock.getAgeProperty()) <= 2){
                        Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, tool).forEach(stack -> Block.dropStack(world, pos, stack));
                        ci.cancel();
                    }
                }
                else {
                    if (state.get(cropBlock.getAgeProperty()) <= 6){
                        Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, tool).forEach(stack -> Block.dropStack(world, pos, stack));
                        ci.cancel();
                    }
                }
                List<ItemStack> itemStackList = Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, tool);
                Main.LOGGER.info("The List of ItemStacks is: " + itemStackList.toString() + ".");
                for (ItemStack itemStack : itemStackList){
                    if (itemStack.getItem() == Items.BEETROOT_SEEDS ||
                            itemStack.getItem() == Items.WHEAT_SEEDS){
                        itemStack.setCount(itemStack.getCount() - 1);
                        Block.dropStack(world, pos, itemStack);
                    }
                    else{
                        Block.dropStack(world, pos, itemStack);
                    }
                }
            }
            else{
                Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, tool).forEach(stack -> Block.dropStack(world, pos, stack));
                state.onStacksDropped((ServerWorld)world, pos, tool, true);
            }
            ci.cancel();
        }
    }
}
