package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.ZombieVillager;

import java.util.List;

public class ZombieVillagerAdapter extends ZombieAdapter<ZombieVillager> {
    public ZombieVillagerAdapter() {
        super(ZombieVillager.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Profession: " + ChatColor.WHITE + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(json.get("profession").getAsString()));
        return lore;
    }

    @Override
    public JsonObject saveData(ZombieVillager entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("profession", entity.getVillagerProfession().name());
        json.addProperty("conversionPlayer", entity.getConversionPlayer() == null ? null : entity.getConversionPlayer().getUniqueId().toString());
        return json;
    }

    @Override
    public void apply(ZombieVillager entity, JsonObject json) {
        super.apply(entity, json);
        entity.setVillagerProfession(org.bukkit.entity.Villager.Profession.valueOf(json.get("profession").getAsString()));
        com.google.gson.JsonElement player = json.get("conversionPlayer");
        if (!player.isJsonNull())
            entity.setConversionPlayer(org.bukkit.Bukkit.getOfflinePlayer(java.util.UUID.fromString(player.getAsString())));
    }
}