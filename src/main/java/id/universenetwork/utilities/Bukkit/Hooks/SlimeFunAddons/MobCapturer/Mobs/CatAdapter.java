package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.WHITE;

public class CatAdapter extends AbstractTameableAdapter<Cat> {
    public CatAdapter() {
        super(Cat.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> l = super.getLore(json);
        l.add(GRAY + "Variant: " + WHITE + humanize(json.get("catType").getAsString()));
        if (!json.get("ownerUUID").isJsonNull()) {
            l.add(GRAY + "Collar Color: " + WHITE + humanize(json.get("collarColor").getAsString()));
            l.add(GRAY + "Sitting: " + WHITE + json.get("sitting").getAsBoolean());
        }
        return l;
    }

    @Override
    public void apply(Cat entity, JsonObject json) {
        super.apply(entity, json);
        entity.setCatType(Type.valueOf(json.get("catType").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
    }

    @Override
    public JsonObject saveData(Cat entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("catType", entity.getCatType().name());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());
        return json;
    }
}