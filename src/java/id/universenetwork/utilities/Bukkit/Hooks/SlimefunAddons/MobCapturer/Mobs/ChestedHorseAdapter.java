package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.WHITE;

public class ChestedHorseAdapter<T extends ChestedHorse> extends AbstractHorseAdapter<T> {
    public ChestedHorseAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> l = super.getLore(json);
        l.add(GRAY + "Chest: " + WHITE + json.get("chest").getAsBoolean());
        return l;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);
        entity.setCarryingChest(json.get("chest").getAsBoolean());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject j = super.saveData(entity);
        j.addProperty("chest", entity.isCarryingChest());
        return j;
    }

    @Override
    public Map<String, ItemStack> saveInventory(T entity) {
        Map<String, ItemStack> i = new HashMap<>();
        for (int s = 0; s < entity.getInventory().getSize(); s++) {
            i.put(String.valueOf(s), entity.getInventory().getItem(s));
        }
        return i;
    }

    @Override
    public void applyInventory(T entity, Map<String, ItemStack> inventory) {
        for (Map.Entry<String, ItemStack> e : inventory.entrySet())
            entity.getInventory().setItem(Integer.parseInt(e.getKey()), e.getValue());
    }
}