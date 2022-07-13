package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Shulker;

import java.util.List;

public class ShulkerAdapter implements MobAdapter<Shulker> {
    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);
        JsonElement color = json.get("color");
        if (!color.isJsonNull())
            lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(color.getAsString()));
        return lore;
    }

    @Override
    public void apply(Shulker entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);
        JsonElement color = json.get("color");
        if (!color.isJsonNull()) entity.setColor(DyeColor.valueOf(color.getAsString()));
    }

    @Override
    public JsonObject saveData(Shulker entity) {
        JsonObject json = MobAdapter.super.saveData(entity);
        DyeColor color = entity.getColor();
        json.addProperty("color", color == null ? null : color.name());
        return json;
    }

    @Override
    public Class<Shulker> getEntityClass() {
        return Shulker.class;
    }
}