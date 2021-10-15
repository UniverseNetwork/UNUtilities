package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects;

import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EffectsManager {
    static final HashMap<UUID, List<PotionSightEffect>> playersEffects = new HashMap<>();

    @NotNull
    public static List<PotionSightEffect> getPlayerEffects(@NotNull Player player) {
        return playersEffects.getOrDefault(player.getUniqueId(), new ArrayList<>());
    }

    @ParametersAreNonnullByDefault
    public static void addEffect(Player player, PotionSightEffect effect) {
        List<PotionSightEffect> list = getPlayerEffects(player);
        list.removeIf(e -> e.getType() == effect.getType());
        list.add(effect);
        playersEffects.put(player.getUniqueId(), list);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasEffect(Player player, PotionSightType type) {
        for (PotionSightEffect potionSightEffect : getPlayerEffects(player)) {
            if (potionSightEffect.getType() == type) return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static boolean hasAnyEffect(Player player) {
        return getPlayerEffects(player).size() != 0;
    }

    @ParametersAreNonnullByDefault
    public static void removePlayer(UUID uuid) {
        playersEffects.remove(uuid);
    }
}