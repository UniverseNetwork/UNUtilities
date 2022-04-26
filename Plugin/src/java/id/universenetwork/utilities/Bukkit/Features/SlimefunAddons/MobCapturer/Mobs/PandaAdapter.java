package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Panda;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;

public class PandaAdapter extends AnimalsAdapter<Panda> {
    public PandaAdapter() {
        super(Panda.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Main Gene: " + ChatColor.WHITE + humanize(json.get("mainGene").getAsString()));
        lore.add(ChatColor.GRAY + "Hidden Gene: " + ChatColor.WHITE + humanize(json.get("hiddenGene").getAsString()));
        return lore;
    }

    @Override
    public void apply(Panda entity, JsonObject json) {
        super.apply(entity, json);
        entity.setMainGene(Panda.Gene.valueOf(json.get("mainGene").getAsString()));
        entity.setHiddenGene(Panda.Gene.valueOf(json.get("hiddenGene").getAsString()));
    }

    @Override
    public JsonObject saveData(Panda entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("mainGene", entity.getMainGene().name());
        json.addProperty("hiddenGene", entity.getHiddenGene().name());
        return json;
    }
}