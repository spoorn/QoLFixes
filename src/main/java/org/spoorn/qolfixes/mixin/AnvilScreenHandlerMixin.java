package org.spoorn.qolfixes.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spoorn.qolfixes.config.ModConfig;
import org.spoorn.qolfixes.util.QolUtil;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Shadow private int repairItemUsage;

    @Inject(method = "updateResult", at = @At(value = "RETURN"))
    private void changeUsageValueIfModifiedStackMaxCount(CallbackInfo ci) {
        ForgingScreenHandlerAccessor accessor = (ForgingScreenHandlerAccessor) this;
        if (ModConfig.get().increaseMaxItemStackSizeForLowerOnes
            && QolUtil.INCREASE_MAX_STACK_ITEMS.contains(accessor.getInput().getStack(1).getItem().getClass())) {
            this.repairItemUsage = 1;
        }
    }
}
