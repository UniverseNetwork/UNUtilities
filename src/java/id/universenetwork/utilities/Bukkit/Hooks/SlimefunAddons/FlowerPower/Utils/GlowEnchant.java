package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * A false enchantment used to make items glow
 *
 * @author NCBPFluffyBear
 * @author J3fftw1
 */
@SuppressWarnings("NullableProblems")
public class GlowEnchant extends Enchantment {
    final Set<String> ids = new java.util.HashSet<>();

    public GlowEnchant(@NotNull org.bukkit.NamespacedKey key, @NotNull String[] applicableItems) {
        super(key);
        ids.addAll(java.util.Arrays.asList(applicableItems));
    }

    @NotNull
    @Override
    @Deprecated
    public String getName() {
        return "flowerpower_glow";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    @Deprecated
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(org.bukkit.inventory.ItemStack item) {
        if (item.hasItemMeta()) {
            final org.bukkit.inventory.meta.ItemMeta itemMeta = item.getItemMeta();
            final java.util.Optional<String> id = io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getItemDataService().getItemData(itemMeta);
            if (id.isPresent()) return ids.contains(id.get());
        }
        return false;
    }

    @Override
    public @NotNull net.kyori.adventure.text.Component displayName(int level) {
        return null;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull io.papermc.paper.enchantments.EnchantmentRarity getRarity() {
        return null;
    }

    @Override
    public float getDamageIncrease(int level, @NotNull org.bukkit.entity.EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<org.bukkit.inventory.EquipmentSlot> getActiveSlots() {
        return null;
    }

    @Override
    public @NotNull String translationKey() {
        return null;
    }
}