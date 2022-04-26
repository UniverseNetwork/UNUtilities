package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.entity.Endermite;

public class EndermiteAdapter implements MobAdapter<Endermite> {
    @Override
    public void apply(Endermite entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);
        entity.setPlayerSpawned(json.get("playerSpawned").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Endermite entity) {
        JsonObject json = MobAdapter.super.saveData(entity);
        json.addProperty("playerSpawned", entity.isPlayerSpawned());
        return json;
    }

    @Override
    public Class<Endermite> getEntityClass() {
        return Endermite.class;
    }
}