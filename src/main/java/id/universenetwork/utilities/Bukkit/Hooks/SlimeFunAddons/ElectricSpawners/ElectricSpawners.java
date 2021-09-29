package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ElectricSpawners;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

public class ElectricSpawners {
    public ElectricSpawners() {
        if (Enabled("ElectricSpawners")) {
            boolean silkspawners = getPluginManager().isPluginEnabled("SilkSpawners");
            ItemGroup itemGroup = new ItemGroup(new NamespacedKey(plugin, "electric_spawners"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("db6bd9727abb55d5415265789d4f2984781a343c68dcaf57f554a5e9aa1cd")), "&9Electric Spawners"));
            Research research = new Research(new NamespacedKey(plugin, "electric_spawners"), 4820, "Powered Spawners", 30);
            for (String mob : get().getStringList(ADDONSSETTINGS.getConfigPath() + "ElectricSpawners.Mobs")) {
                try {
                    EntityType type = EntityType.valueOf(mob);
                    new ElectricSpawner(itemGroup, mob, type, research, silkspawners).register(addon);
                } catch (IllegalArgumentException x) {
                    getLogger().log(WARNING, prefix + " §eAn Error has occured while adding an Electric Spawner for the (posibly outdated or invalid) EntityType \"{0}\"", mob);
                } catch (Exception x) {
                    getLogger().log(SEVERE, x, () -> prefix + " §cAn Error has occured while adding an Electric Spawner for the EntityType \"" + mob + "\"");
                }
            }
            research.register();
            if (silkspawners)
                System.out.println(prefix + " §bSuccessfully Registered §dElectricSpawners §bAddon With §dSilkSpawners §bSupport");
            else System.out.println(prefix + " §bSuccessfully Registered §dElectricSpawners §bAddon");
        }
    }
}