package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.ChatColor;

import java.util.List;

public class AnimalsAdapter<T extends org.bukkit.entity.Animals> implements MobAdapter<T> {
    final Class<T> entityClass;

    public AnimalsAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);
        lore.add(ChatColor.GRAY + "Baby: " + ChatColor.WHITE + json.get("baby").getAsBoolean());
        return lore;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);
        json.addProperty("baby", !entity.isAdult());
        json.addProperty("_age", entity.getAge());
        json.addProperty("_ageLock", entity.getAgeLock());
        json.addProperty("_breedable", entity.canBreed());
        json.addProperty("_loveModeTicks", entity.getLoveModeTicks());
        return json;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);
        entity.setAge(json.get("_age").getAsInt());
        entity.setLoveModeTicks(json.get("_loveModeTicks").getAsInt());
        entity.setAgeLock(json.get("_ageLock").getAsBoolean());
        entity.setBreed(json.get("_breedable").getAsBoolean());
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}