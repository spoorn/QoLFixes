package org.spoorn.qolfixes.config;

import draylar.omegaconfig.OmegaConfig;
import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import org.spoorn.qolfixes.QolFixes;

public class ModConfig implements Config {

    private static ModConfig CONFIG;
    
    @Comment("True to disable the \"...moved too quickly!...\" error message on servers when players move too fast, along with the teleport.  [default = true]")
    public boolean disableMovedTooQuickly = true;
    
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
