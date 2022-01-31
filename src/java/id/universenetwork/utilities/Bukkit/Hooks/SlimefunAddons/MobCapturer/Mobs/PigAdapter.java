package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Pig;

import java.util.List;

public class PigAdapter extends AnimalsAdapter<Pig> {
    public PigAdapter() {
        super(Pig.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        if (json.get("saddle").getAsBoolean()) {
            lore.add(ChatColor.GRAY + "+ Saddle");
        }
        return lore;
    }

    @Override
    public void apply(Pig entity, JsonObject json) {
        super.apply(entity, json);
        entity.setSaddle(json.get("saddle").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Pig entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("saddle", entity.hasSaddle());
        return json;
    }
}