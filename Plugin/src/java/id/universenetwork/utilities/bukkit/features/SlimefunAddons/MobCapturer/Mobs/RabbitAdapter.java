package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Rabbit;

import java.util.List;

public class RabbitAdapter extends AnimalsAdapter<Rabbit> {
    public RabbitAdapter() {
        super(Rabbit.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Variant: " + ChatColor.WHITE + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(json.get("rabbitType").getAsString()));
        return lore;
    }

    @Override
    public void apply(Rabbit entity, JsonObject json) {
        super.apply(entity, json);
        entity.setRabbitType(Rabbit.Type.valueOf(json.get("rabbitType").getAsString()));
    }

    @Override
    public JsonObject saveData(Rabbit entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("rabbitType", entity.getRabbitType().name());
        return json;
    }
}