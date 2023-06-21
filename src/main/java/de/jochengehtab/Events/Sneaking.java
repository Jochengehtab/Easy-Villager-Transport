package de.jochengehtab.Events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
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
                player.getInventory().addPickBlock(new ItemStack(Items.VILLAGER_SPAWN_EGG));
            }
            entity.remove(Entity.RemovalReason.KILLED);
        }
        return ActionResult.PASS;
    }
}
