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
    
    public static void init() {
        CONFIG = OmegaConfig.register(ModConfig.class);
    }

    public static ModConfig get() {
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
