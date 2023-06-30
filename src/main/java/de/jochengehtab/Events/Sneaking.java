package de.jochengehtab.Events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Sneaking implements AttackEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof VillagerEntity && !world.isClient()){
            if (player.isSneaking()) {
                PlayerInventory playerInventory = player.getInventory();
                if (playerInventory.getEmptySlot() == -1){
                    Block.dropStack(world, player.getBlockPos(), new ItemStack(Items.VILLAGER_SPAWN_EGG));
                    entity.remove(Entity.RemovalReason.KILLED);
                }
                else{
                    if (playerInventory.contains(new ItemStack(Items.VILLAGER_SPAWN_EGG))){
                        int count = playerInventory.getStack(player.getInventory().getSlotWithStack(new ItemStack(Items.VILLAGER_SPAWN_EGG))).getCount() + 1;
                        playerInventory.removeStack(player.getInventory().getSlotWithStack(new ItemStack(Items.VILLAGER_SPAWN_EGG)));
                        playerInventory.insertStack(new ItemStack(Items.VILLAGER_SPAWN_EGG, count));
                    }else{
                        playerInventory.insertStack(new ItemStack(Items.VILLAGER_SPAWN_EGG));
                    }
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            }
        }
        else if (entity instanceof ZombieEntity && !world.isClient()){
            if (player.isSneaking()) {
                PlayerInventory playerInventory = player.getInventory();
                if (playerInventory.getEmptySlot() == -1){
                    Block.dropStack(world, player.getBlockPos(), new ItemStack(Items.ZOMBIE_SPAWN_EGG));
                    entity.remove(Entity.RemovalReason.KILLED);
                }
                else{
                    if (playerInventory.contains(new ItemStack(Items.ZOMBIE_SPAWN_EGG))){
                        int count = playerInventory.getStack(player.getInventory().getSlotWithStack(new ItemStack(Items.ZOMBIE_SPAWN_EGG))).getCount() + 1;
                        playerInventory.removeStack(player.getInventory().getSlotWithStack(new ItemStack(Items.ZOMBIE_SPAWN_EGG)));
                        playerInventory.insertStack(new ItemStack(Items.ZOMBIE_SPAWN_EGG, count));
                    }else{
                        playerInventory.insertStack(new ItemStack(Items.ZOMBIE_SPAWN_EGG));
                    }
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            }
        }
        return ActionResult.PASS;
    }
}
