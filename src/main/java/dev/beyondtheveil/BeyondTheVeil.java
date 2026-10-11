package dev.beyondtheveil;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeyondTheVeil implements ModInitializer {
    public static final String MOD_ID = "beyond_the_veil";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Beyond the Veil initialized. Gameplay systems are being brought online incrementally.");
    }
}
