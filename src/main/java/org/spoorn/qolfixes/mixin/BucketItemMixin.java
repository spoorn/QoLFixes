package org.spoorn.qolfixes.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.qolfixes.config.ModConfig;
import org.spoorn.qolfixes.util.QolUtil;

@Mixin(BucketItem.class)
public class BucketItemMixin {
    
    @Inject(method = "getEmptiedStack", at = @At(value = "RETURN"), cancellable = true)
    private static void supportMoreThanOneStackCount(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (ModConfig.get().increaseMaxItemStackSizeForLowerOnes && !player.getAbilities().creativeMode && stack.getCount() > 1) {
            stack.decrement(1);
            QolUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
            cir.setReturnValue(stack);
            cir.cancel();
        }
    }
}
