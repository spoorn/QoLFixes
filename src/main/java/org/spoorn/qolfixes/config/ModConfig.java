package org.spoorn.qolfixes.config;

import draylar.omegaconfig.OmegaConfig;
import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import org.spoorn.qolfixes.QolFixes;

public class ModConfig implements Config {

    private static ModConfig CONFIG;
    
    @Comment("True to disable the \"...moved too quickly!...\" error message on servers when players move too fast, along with the teleport.  [default = true]")
    public boolean disableMovedTooQuickly = true;
    
    @Comment("True to disable vehicle \"...moved too quickly!...\" error message on servers when players in vehicles move too fast, along with the teleport.  [default = true]")
    public boolean disableVehicleMovedTooQuickly = true;
    
    @Comment("True to replace Criterion sets in the AbstractCriterion class with a Concurrent Hash Set to make it thread safe.  [default = true]\n" +
            "This prevents issues such as https://github.com/Draylar/inmis/issues/117")
    public boolean useThreadSafeAbstractCriterionProgressions = true;
    
    @Comment("Makes various ShulkerEntity code NPE safe.  False to disable.  [default = true]")
    public boolean useNPESafeShulkerEntity = true;
    
    @Comment("Changes some items to have max ItemStack size of 64 instead of their lower vanilla values [default = true]\n" +
            "Applies for: Saddle, Bucket, Milk Bucket, Entity Buckets, Powdered Snow Buckets, Snowball, Boats, Ender Pearls,\n" +
            "\tEggs, Enchanted Books, Honey Bottles, Potions, Music Discs, Horse Armor, Stews")
    public boolean increaseMaxItemStackSizeForLowerOnes = true;
    
    @Comment("Fixes ConcurrentModificationException with StructureTemplate.PalettedBLockInfoList [default = true]")
    public boolean fixCMEPalettedBlockInfoList = true;
    
    @Comment("Set to true to prevent kicking clients off when Sound packets are invalid on the server.  [default = false]\n" +
            "It will instead print the error along with the sound ID that caused the error, and continue on.\n" +
            "If set to false, this at least adds extra error logging to print the sound ID so you can locate the mod that's causing it.")
    public boolean preventClientKickOnBadSoundPacket = false;
    
    public static void init() {
        CONFIG = OmegaConfig.register(ModConfig.class);
    }

    public static ModConfig get() {
        // In case mixins are called before our mod initializer
        if (CONFIG == null) {
            init();
        }
        return CONFIG;
    }

    @Override
    public String getName() {
        return QolFixes.MODID;
    }

    @Override
    public String getExtension() {
        // For nicer comments parsing in text editors
        return "json5";
    }
}
