package org.spoorn.qolfixes.mixin;

import net.minecraft.block.Block;
import net.minecraft.structure.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;

@Mixin(StructureTemplate.PalettedBlockInfoList.class)
public interface StructureTemplate_PalettedBlockInfoListAccessor {
    
    @Accessor("blockToInfos")
    Map<Block, List<StructureTemplate.StructureBlockInfo>> getBlockToInfos();
    
    @Accessor("blockToInfos")
    void setBlockToInfos(Map<Block, List<StructureTemplate.StructureBlockInfo>> blockToInfos);
}
