package org.spoorn.qolfixes.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spoorn.qolfixes.config.ModConfig;
import org.spoorn.qolfixes.util.QolUtil;

@Mixin(PowderSnowBucketItem.class)
public class PowderSnowBucketItemMixin {
    
    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"))
    private void supportMoreThanOneStackCount(PlayerEntity instance, Hand hand, ItemStack itemStack, ItemUsageContext context) {
        if (ModConfig.get().increaseMaxItemStackSizeForLowerOnes) {
            ItemStack handStack = context.getStack();
            if (handStack.getCount() > 1) {
                QolUtil.givePlayerItemOrDrop(instance, new ItemStack(Items.BUCKET));
            }
            instance.setStackInHand(hand, handStack);
        } else {
            instance.setStackInHand(hand, itemStack);
        }
    }
}
