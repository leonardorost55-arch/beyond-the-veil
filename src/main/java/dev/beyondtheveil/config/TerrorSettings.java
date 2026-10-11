package dev.beyondtheveil.config;

/**
 * Immutable, validated runtime settings for the horror event systems.
 * Event intervals are measured in Minecraft ticks (20 ticks per second).
 */
public record TerrorSettings(
        int intensity,
        int minimumEventIntervalTicks,
        int maximumEventIntervalTicks,
        boolean visualEffectsEnabled,
        float effectsVolume
) {
    public static final int MIN_INTERVAL_TICKS = 1_200;
    public static final int MAX_INTERVAL_TICKS = 72_000;

    public TerrorSettings {
        intensity = clamp(intensity, 0, 3);
        minimumEventIntervalTicks = clamp(
                minimumEventIntervalTicks, MIN_INTERVAL_TICKS, MAX_INTERVAL_TICKS);
        maximumEventIntervalTicks = clamp(
                maximumEventIntervalTicks, minimumEventIntervalTicks, MAX_INTERVAL_TICKS);

        if (!Float.isFinite(effectsVolume)) {
            effectsVolume = 0.5F;
        } else {
            effectsVolume = Math.max(0.0F, Math.min(1.0F, effectsVolume));
        }
    }

    public static TerrorSettings defaults() {
        return new TerrorSettings(2, 6_000, 24_000, true, 0.7F);
    }

    private static int clamp(int value, int minimum, int maximum) {
        return Math.max(minimum, Math.min(maximum, value));
    }
}
