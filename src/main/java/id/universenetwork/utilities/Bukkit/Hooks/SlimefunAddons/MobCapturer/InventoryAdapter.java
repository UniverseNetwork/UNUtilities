package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface InventoryAdapter<T extends LivingEntity> {
    void applyInventory(T entity, Map<String, ItemStack> inventory);

    Map<String, ItemStack> saveInventory(T entity);
}