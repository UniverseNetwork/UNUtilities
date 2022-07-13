package id.universenetwork.utilities.bukkit.features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;

public class HorseAdapter extends AbstractHorseAdapter<Horse> {
    public HorseAdapter() {
        super(Horse.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Style: " + ChatColor.WHITE + humanize(json.get("style").getAsString()));
        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + humanize(json.get("color").getAsString()));
        return lore;
    }

    @Override
    public void apply(Horse entity, JsonObject json) {
        super.apply(entity, json);
        entity.setStyle(Horse.Style.valueOf(json.get("style").getAsString()));
        entity.setColor(Horse.Color.valueOf(json.get("color").getAsString()));
    }

    @Override
    public JsonObject saveData(Horse entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("style", entity.getStyle().name());
        json.addProperty("color", entity.getColor().name());
        return json;
    }

    @Override
    public void applyInventory(Horse entity, Map<String, ItemStack> inventory) {
        super.applyInventory(entity, inventory);
        inventory.put("armor", entity.getInventory().getArmor());
    }

    @Override
    public Map<String, ItemStack> saveInventory(Horse entity) {
        Map<String, ItemStack> inventory = super.saveInventory(entity);
        inventory.put("armor", entity.getInventory().getArmor());
        return inventory;
    }
}