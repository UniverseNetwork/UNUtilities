package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Items;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.InventoryAdapter;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.MobAdapter;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public class MobEgg<T extends org.bukkit.entity.LivingEntity> extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    final NamespacedKey dataKey;
    final NamespacedKey inventoryKey;
    final MobAdapter<T> adapter;

    public MobEgg(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, NamespacedKey dataKey, NamespacedKey inventoryKey, MobAdapter<T> adapter, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.dataKey = dataKey;
        this.inventoryKey = inventoryKey;
        this.adapter = adapter;
    }

    public ItemStack getEggItem(T entity) {
        JsonObject json = adapter.saveData(entity);
        ItemStack item = getItem().clone();
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setLore(adapter.getLore(json));
        meta.getPersistentDataContainer().set(dataKey, adapter, json);
        if (adapter instanceof InventoryAdapter) {
            FileConfiguration yaml = new YamlConfiguration();
            for (Map.Entry<String, ItemStack> entry : ((InventoryAdapter<T>) adapter).saveInventory(entity).entrySet())
                yaml.set(entry.getKey(), entry.getValue());
            meta.getPersistentDataContainer().set(inventoryKey, PersistentDataType.STRING, yaml.saveToString());
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            java.util.Optional<Block> block = e.getClickedBlock();
            if (block.isPresent()) {
                Block b = block.get();
                T entity = b.getWorld().spawn(b.getRelative(e.getClickedFace()).getLocation(), adapter.getEntityClass());
                org.bukkit.persistence.PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
                JsonObject json = container.get(dataKey, adapter);
                io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.consumeItem(e.getItem(), false);
                if (json != null) {
                    adapter.apply(entity, json);
                    if (adapter instanceof InventoryAdapter) {
                        Map<String, ItemStack> inventory = new java.util.HashMap<>();
                        try (java.io.Reader reader = new java.io.StringReader(container.get(inventoryKey, PersistentDataType.STRING))) {
                            FileConfiguration yaml = YamlConfiguration.loadConfiguration(reader);
                            for (String key : yaml.getKeys(true)) {
                                Object obj = yaml.get(key);
                                if (obj instanceof ItemStack) inventory.put(key, (ItemStack) obj);
                            }
                        } catch (java.io.IOException x) {
                            x.printStackTrace();
                        }
                        ((InventoryAdapter<T>) adapter).applyInventory(entity, inventory);
                    }
                }
            }
        };
    }
}