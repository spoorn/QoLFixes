package org.spoorn.qolfixes.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spoorn.qolfixes.config.ModConfig;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    
    @Redirect(method = "onPlayerMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isInTeleportationState()Z", ordinal = 0))
    private boolean disableMovedTooQuicklyAndTeleport(ServerPlayerEntity instance) {
        if (ModConfig.get().disableMovedTooQuickly) {
            return true;
        }
        return instance.isInTeleportationState();
    }
}
