package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;

public class RaiderAdapter<T extends org.bukkit.entity.Raider> extends AbstractHumanoidAdapter<T> {
    public RaiderAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);
        entity.setCanJoinRaid(json.get("canJoinRaid").getAsBoolean());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("canJoinRaid", entity.isCanJoinRaid());
        return json;
    }
}