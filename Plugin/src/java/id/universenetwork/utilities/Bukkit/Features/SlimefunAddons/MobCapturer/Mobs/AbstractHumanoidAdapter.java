package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.InventoryAdapter;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

class AbstractHumanoidAdapter<T extends org.bukkit.entity.Monster> implements MobAdapter<T>, InventoryAdapter<T> {
    final Class<T> entityClass;

    public AbstractHumanoidAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);
        com.google.gson.JsonElement element = json.get("canPickupItems");
        if (!element.isJsonNull()) entity.setCanPickupItems(element.getAsBoolean());
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) {
            equipment.setItemInMainHandDropChance(json.get("mainHandDropChance").getAsFloat());
            equipment.setItemInOffHandDropChance(json.get("offHandDropChance").getAsFloat());
            equipment.setHelmetDropChance(json.get("helmetDropChance").getAsFloat());
            equipment.setChestplateDropChance(json.get("chestplateDropChance").getAsFloat());
            equipment.setLeggingsDropChance(json.get("leggingsDropChance").getAsFloat());
            equipment.setBootsDropChance(json.get("bootsDropChance").getAsFloat());
        }
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);
        json.addProperty("canPickupItems", entity.getCanPickupItems());
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) {
            json.addProperty("mainHandDropChance", equipment.getItemInMainHandDropChance());
            json.addProperty("offHandDropChance", equipment.getItemInOffHandDropChance());
            json.addProperty("helmetDropChance", equipment.getHelmetDropChance());
            json.addProperty("chestplateDropChance", equipment.getChestplateDropChance());
            json.addProperty("leggingsDropChance", equipment.getLeggingsDropChance());
            json.addProperty("bootsDropChance", equipment.getBootsDropChance());
        }
        return json;
    }

    @Override
    public void applyInventory(T entity, Map<String, ItemStack> inventory) {
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) {
            equipment.setItemInMainHand(inventory.get("mainHand"));
            equipment.setItemInOffHand(inventory.get("offHand"));
            equipment.setHelmet(inventory.get("helmet"));
            equipment.setChestplate(inventory.get("chestplate"));
            equipment.setLeggings(inventory.get("leggings"));
            equipment.setBoots(inventory.get("boots"));
        }
    }

    @Override
    public Map<String, ItemStack> saveInventory(T entity) {
        Map<String, ItemStack> inv = new java.util.HashMap<>();
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) {
            inv.put("mainHand", equipment.getItemInMainHand());
            inv.put("offHand", equipment.getItemInOffHand());
            inv.put("helmet", equipment.getHelmet());
            inv.put("chestplate", equipment.getChestplate());
            inv.put("leggings", equipment.getLeggings());
            inv.put("boots", equipment.getBoots());
        }
        return inv;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}