package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.MobAdapter;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;

public class TropicalFishAdapter implements MobAdapter<TropicalFish> {
    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);
        lore.add(ChatColor.GRAY + "Base Color: " + ChatColor.WHITE + humanize(json.get("baseColor").getAsString()));
        lore.add(ChatColor.GRAY + "Pattern: " + ChatColor.WHITE + humanize(json.get("pattern").getAsString()));
        lore.add(ChatColor.GRAY + "Pattern Color: " + ChatColor.WHITE + humanize(json.get("patternColor").getAsString()));
        return lore;
    }

    @Override
    public void apply(TropicalFish entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);
        entity.setBodyColor(DyeColor.valueOf(json.get("baseColor").getAsString()));
        entity.setPattern(TropicalFish.Pattern.valueOf(json.get("pattern").getAsString()));
        entity.setPatternColor(DyeColor.valueOf(json.get("patternColor").getAsString()));
    }

    @Override
    public JsonObject saveData(TropicalFish entity) {
        JsonObject json = MobAdapter.super.saveData(entity);
        json.addProperty("baseColor", entity.getBodyColor().name());
        json.addProperty("pattern", entity.getPattern().name());
        json.addProperty("patternColor", entity.getPatternColor().name());
        return json;
    }

    @Override
    public Class<TropicalFish> getEntityClass() {
        return TropicalFish.class;
    }
}