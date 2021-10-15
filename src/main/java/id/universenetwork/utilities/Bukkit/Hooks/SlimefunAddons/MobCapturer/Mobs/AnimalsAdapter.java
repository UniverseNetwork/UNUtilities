package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.entity.Animals;

import java.util.List;

import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.WHITE;

public class AnimalsAdapter<T extends Animals> implements MobAdapter<T> {
    final Class<T> entityClass;

    public AnimalsAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> l = MobAdapter.super.getLore(json);
        l.add(GRAY + "Baby: " + WHITE + json.get("baby").getAsBoolean());
        return l;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject j = MobAdapter.super.saveData(entity);
        j.addProperty("baby", !entity.isAdult());
        j.addProperty("_age", entity.getAge());
        j.addProperty("_ageLock", entity.getAgeLock());
        j.addProperty("_breedable", entity.canBreed());
        j.addProperty("_loveModeTicks", entity.getLoveModeTicks());
        return j;
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