package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface InventoryAdapter<T extends org.bukkit.entity.LivingEntity> {
    void applyInventory(T entity, Map<String, ItemStack> inventory);

    Map<String, ItemStack> saveInventory(T entity);
}