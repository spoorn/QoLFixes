package org.spoorn.qolfixes.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spoorn.qolfixes.config.ModConfig;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

    @Shadow protected abstract boolean isHost();

    @Redirect(method = "onPlayerMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isInTeleportationState()Z", ordinal = 0))
    private boolean disableMovedTooQuicklyAndTeleport(ServerPlayerEntity instance) {
        if (ModConfig.get().disableMovedTooQuickly) {
            return true;
        }
        return instance.isInTeleportationState();
    }
    
    @Redirect(method = "onVehicleMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;isHost()Z"))
    private boolean disableVehicleMovedTooQuicklyAndTeleport(ServerPlayNetworkHandler instance) {
        if (ModConfig.get().disableVehicleMovedTooQuickly) {
            return true;
        }
        return isHost();
    }
}
