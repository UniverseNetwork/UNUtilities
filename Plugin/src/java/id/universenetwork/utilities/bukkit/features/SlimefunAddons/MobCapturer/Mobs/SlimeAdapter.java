package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.ChatColor;

import java.util.List;

public class SlimeAdapter<T extends org.bukkit.entity.Slime> implements MobAdapter<T> {
    final Class<T> entityClass;

    public SlimeAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);
        lore.add(ChatColor.GRAY + "Size: " + ChatColor.WHITE + json.get("size").getAsInt());
        return lore;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);
        entity.setSize(json.get("size").getAsInt());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);
        json.addProperty("size", entity.getSize());
        return json;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}