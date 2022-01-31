package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Tameable;

import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getOfflinePlayer;

class AbstractTameableAdapter<T extends Animals & Tameable> extends AnimalsAdapter<T> {
    public AbstractTameableAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> l = super.getLore(json);
        JsonElement e = json.get("ownerName");
        if (!e.isJsonNull()) l.add(ChatColor.GRAY + "Owner: " + ChatColor.WHITE + e.getAsString());
        return l;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject j = super.saveData(entity);
        j.addProperty("ownerUUID", entity.getOwner() == null ? null : entity.getOwner().getUniqueId().toString());
        j.addProperty("ownerName", entity.getOwner() == null ? null : entity.getOwner().getName());
        return j;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);
        JsonElement e = json.get("ownerUUID");
        if (!e.isJsonNull())
            entity.setOwner(getOfflinePlayer(UUID.fromString(e.getAsString())));
    }
}