package org.spoorn.qolfixes.mixin;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.structure.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spoorn.qolfixes.config.ModConfig;

import java.util.List;
import java.util.Map;

@Mixin(StructureTemplate.PalettedBlockInfoList.class)
public class StructureTemplate_PalettedBlockInfoListMixin {
    
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void fixBlockToInfosCME(List infos, CallbackInfo ci) {
        if (ModConfig.get().fixCMEPalettedBlockInfoList) {
            StructureTemplate_PalettedBlockInfoListAccessor accessor = ((StructureTemplate_PalettedBlockInfoListAccessor) this);
            Map<Block, List<StructureTemplate.StructureBlockInfo>> newMap = Maps.newConcurrentMap();
            // For sanity, put if anything was already in the existing map
            newMap.putAll(accessor.getBlockToInfos());
            accessor.setBlockToInfos(newMap);
        }
    }
}
