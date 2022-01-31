package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects;

import javax.annotation.ParametersAreNonnullByDefault;

public class PotionSightEffect {
    final PotionSightType type;
    long time;

    @ParametersAreNonnullByDefault
    public PotionSightEffect(PotionSightType type, long time) {
        this.type = type;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public PotionSightType getType() {
        return type;
    }
}
