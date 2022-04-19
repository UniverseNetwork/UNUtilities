package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Sheep;

import java.util.List;

public class SheepAdapter extends AnimalsAdapter<Sheep> {
    public SheepAdapter() {
        super(Sheep.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(json.get("woolColor").getAsString()));
        return lore;
    }

    @Override
    public void apply(Sheep entity, JsonObject json) {
        super.apply(entity, json);
        entity.setSheared(json.get("sheared").getAsBoolean());
        entity.setColor(org.bukkit.DyeColor.valueOf(json.get("woolColor").getAsString()));
    }

    @Override
    public JsonObject saveData(Sheep entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("sheared", entity.isSheared());
        json.addProperty("woolColor", entity.getColor().name());
        return json;
    }
}