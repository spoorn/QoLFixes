package org.spoorn.qolfixes.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.qolfixes.config.ModConfig;
import org.spoorn.qolfixes.util.QolUtil;

@Mixin(Item.class)
public class ItemMixin {
    
    private static final int SIXTY_FOUR = 64;
    
    @Inject(method = "getMaxCount", at = @At(value = "HEAD"), cancellable = true)
    private void modifyItemMaxCounts(CallbackInfoReturnable<Integer> cir) {
        // Null check as charm checks item stacks during mod load time which can run into a NULL here
        ModConfig config = ModConfig.get();
        if (config != null && config.increaseMaxItemStackSizeForLowerOnes && QolUtil.INCREASE_MAX_STACK_ITEMS.contains(this.getClass())) {
            cir.setReturnValue(SIXTY_FOUR);
            cir.cancel();
        }
    }
}
