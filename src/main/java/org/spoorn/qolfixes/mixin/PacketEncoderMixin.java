package org.spoorn.qolfixes.mixin;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketEncoder;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spoorn.qolfixes.config.ModConfig;

@Mixin(PacketEncoder.class)
public class PacketEncoderMixin {

    private static final Logger log = LogManager.getLogger("PacketEncoderMixin");

    @Redirect(method = "encode(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;Lio/netty/buffer/ByteBuf;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/Packet;isWritingErrorSkippable()Z"))
    private boolean skipWritingErrorForSoundPackets(Packet instance) {
        if (instance instanceof PlaySoundS2CPacket playSoundS2CPacket) {
            log.error("[QoLFixes] Failed to register SoundEvent Packet with ID " + playSoundS2CPacket.getSound().getId());
            if (ModConfig.get().preventClientKickOnBadSoundPacket) {
                log.warn("[QoLFixes] Skipping write error for the bad Sound packet");
                return true;
            } else {
                log.warn("[QoLFixes] If you want to prevent clients from being kicked on these errors, set QoLFixes config " +
                        "`preventClientKickOnBadSoundPacket` to `true`.");
            }
        }
        return instance.isWritingErrorSkippable();
    }
}
