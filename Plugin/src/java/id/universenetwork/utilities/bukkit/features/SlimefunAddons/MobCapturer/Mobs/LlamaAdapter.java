package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Llama;

import java.util.List;

public class LlamaAdapter<T extends Llama> extends ChestedHorseAdapter<T> {
    public LlamaAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(json.get("color").getAsString()));
        return lore;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);
        entity.setColor(Llama.Color.valueOf(json.get("color").getAsString()));
        entity.setStrength(json.get("spitStrength").getAsInt());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("color", entity.getColor().name());
        json.addProperty("spitStrength", entity.getStrength());
        return json;
    }
}