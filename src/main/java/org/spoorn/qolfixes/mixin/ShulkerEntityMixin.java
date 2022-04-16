package org.spoorn.qolfixes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.qolfixes.config.ModConfig;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends Entity {
    
    private static final Logger log = LogManager.getLogger("ShulkerEntityMixin");

    private ShulkerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getHeightOffset", at = @At(value = "HEAD"), cancellable = true)
    private void npeForShulkerEntityGetHeightOffset(CallbackInfoReturnable<Double> cir) {
        if (this.getVehicle() == null && ModConfig.get().useNPESafeShulkerEntity) {
            log.info("[QoLFixes] ShulkerEntity had no vehicle in getHeightOffset.  Defaulting to super.getHeightOffset");
            cir.setReturnValue(super.getHeightOffset());
            cir.cancel();
        }
    }
}
