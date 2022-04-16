package org.spoorn.qolfixes.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.qolfixes.config.ModConfig;
import org.spoorn.qolfixes.util.QolUtil;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {
    
    @Inject(method = "finishUsing", at = @At(value = "RETURN"), cancellable = true)
    private void supportMoreThanOneStackCount(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (ModConfig.get().increaseMaxItemStackSizeForLowerOnes && !stack.isEmpty() && user instanceof PlayerEntity player) {
            QolUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
        }
    }
}
