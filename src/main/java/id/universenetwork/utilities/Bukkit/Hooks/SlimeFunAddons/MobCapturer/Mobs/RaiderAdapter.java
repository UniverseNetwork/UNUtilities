package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.entity.Raider;

public class RaiderAdapter<T extends Raider> extends AbstractHumanoidAdapter<T> {
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