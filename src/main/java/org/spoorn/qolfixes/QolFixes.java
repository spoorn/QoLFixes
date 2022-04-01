package org.spoorn.qolfixes;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;
import org.spoorn.qolfixes.config.ModConfig;

@Log4j2
public class QolFixes implements ModInitializer {
    
    public static final String MODID = "qolfixes";
    
    @Override
    public void onInitialize() {
        log.info("Hello from QoLFixes!");

        // Config
        ModConfig.init();
    }
}
