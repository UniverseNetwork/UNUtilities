package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;

import java.util.List;

class AbstractTameableAdapter<T extends org.bukkit.entity.Animals & org.bukkit.entity.Tameable> extends AnimalsAdapter<T> {
    public AbstractTameableAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        JsonElement element = json.get("ownerName");
        if (!element.isJsonNull()) lore.add(ChatColor.GRAY + "Owner: " + ChatColor.WHITE + element.getAsString());
        return lore;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("ownerUUID", entity.getOwner() == null ? null : entity.getOwner().getUniqueId().toString());
        json.addProperty("ownerName", entity.getOwner() == null ? null : entity.getOwner().getName());
        return json;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);
        JsonElement element = json.get("ownerUUID");
        if (!element.isJsonNull()) {
            org.bukkit.OfflinePlayer player = org.bukkit.Bukkit.getOfflinePlayer(java.util.UUID.fromString(element.getAsString()));
            entity.setOwner(player);
        }
    }
}