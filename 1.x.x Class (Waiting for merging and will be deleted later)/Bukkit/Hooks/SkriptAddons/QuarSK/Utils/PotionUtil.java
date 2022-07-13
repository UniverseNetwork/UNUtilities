package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.QuarSK.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.bukkit.entity.EntityType.SPLASH_POTION;

/**
 * Created by PRODSEB on 27/01/2017.
 */
public final class PotionUtil {
    public static PotionEffect getEffectByEffectType(@NotNull PotionMeta meta, PotionEffectType effectType) {
        List<PotionEffect> effectList = meta.getCustomEffects();
        return effectList.stream().filter(effect -> effect.getType() == effectType).findFirst().orElse(null);
    }

    public static boolean isPotionItem(@NotNull org.bukkit.inventory.ItemStack item) {
        return (item.getType() == Material.POTION || item.getType() == Material.SPLASH_POTION || item.getType() == Material.LINGERING_POTION || item.getType() == Material.TIPPED_ARROW);
    }

    @NotNull
    public static PotionEffect fromPotionData(@NotNull PotionData data) {
        PotionEffectType type = data.getType().getEffectType();
        if (type == PotionEffectType.HEAL || type == PotionEffectType.HARM) {
            return new PotionEffect(type, 1, data.isUpgraded() ? 2 : 1);
        } else if (type == PotionEffectType.REGENERATION || type == PotionEffectType.POISON) {
            if (data.isExtended()) return new PotionEffect(type, 1800, 1);
            else if (data.isUpgraded()) return new PotionEffect(type, 440, 2);
            else return new PotionEffect(type, 900, 1);
        } else if (type == PotionEffectType.NIGHT_VISION || type == PotionEffectType.INVISIBILITY || type == PotionEffectType.FIRE_RESISTANCE || type == PotionEffectType.WATER_BREATHING)
            return new PotionEffect(type, data.isExtended() ? 9600 : 3600, 1);
        else if (type == PotionEffectType.WEAKNESS || type == PotionEffectType.SLOW)
            return new PotionEffect(type, data.isExtended() ? 4800 : 1800, 1);
        else if (data.isExtended()) return new PotionEffect(type, 9600, 1);
        else if (data.isUpgraded()) return new PotionEffect(type, 1800, 2);
        else return new PotionEffect(type, 3600, 1);
    }

    @NotNull
    public static PotionData emptyPotionData() {
        return new PotionData(org.bukkit.potion.PotionType.WATER);
    }

    public static boolean isEntityThrownPotion(@NotNull org.bukkit.entity.Entity entity) {
        return entity.getType() == SPLASH_POTION;
    }

    public static PotionEffect[] actualPotionEffects(@NotNull PotionMeta meta) {
        List<PotionEffect> effects = new java.util.ArrayList<>(meta.getCustomEffects());
        if (meta.getBasePotionData().getType() != PotionType.UNCRAFTABLE)
            effects.add(fromPotionData(meta.getBasePotionData()));
        return effects.toArray(new PotionEffect[0]);
    }
}