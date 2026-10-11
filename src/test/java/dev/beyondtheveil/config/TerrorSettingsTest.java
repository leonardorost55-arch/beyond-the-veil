package dev.beyondtheveil.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerrorSettingsTest {
    @Test
    void clampsIntensityAndEventIntervals() {
        TerrorSettings settings = new TerrorSettings(-10, 1, 0, true, 0.5F);

        assertEquals(0, settings.intensity());
        assertEquals(TerrorSettings.MIN_INTERVAL_TICKS, settings.minimumEventIntervalTicks());
        assertEquals(settings.minimumEventIntervalTicks(), settings.maximumEventIntervalTicks());
    }

    @Test
    void clampsVolumeAndHandlesNonFiniteValues() {
        assertEquals(0.0F, new TerrorSettings(1, 1200, 2400, true, -3.0F).effectsVolume());
        assertEquals(1.0F, new TerrorSettings(1, 1200, 2400, true, 3.0F).effectsVolume());
        assertEquals(0.5F, new TerrorSettings(1, 1200, 2400, true, Float.NaN).effectsVolume());
    }

    @Test
    void defaultsStayWithinTheDeclaredBounds() {
        TerrorSettings settings = TerrorSettings.defaults();

        assertEquals(2, settings.intensity());
        assertTrue(settings.minimumEventIntervalTicks() <= settings.maximumEventIntervalTicks());
        assertTrue(settings.effectsVolume() >= 0.0F && settings.effectsVolume() <= 1.0F);
    }
}
