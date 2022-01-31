package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Tools.Hammer;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class ExtraTools {
    int researchId = 4100;

    public ExtraTools() {
        if (Enabled("ExtraTools")) {
            new Hammer().register(addon);
            new Research(new NamespacedKey(plugin, "HAMMER"), ++researchId, "Hammer", 3).addItems(ETItems.HAMMER).register();
            new GoldTransmuter().register(addon);
            new Research(new NamespacedKey(plugin, "GOLD_TRANSMUTER"), ++researchId, "Gold Transmuter", 12).addItems(ETItems.GOLD_TRANSMUTER).register();
            new ElectricComposter(ElectricComposter.Tier.ONE) {
                @Override
                public int getEnergyConsumption() {
                    return 9;
                }

                @Override
                public int getSpeed() {
                    return 1;
                }
            }.register(addon);
            new Research(new NamespacedKey(plugin, "ELECTRIC_COMPOSTER"), ++researchId, "Electric Composter", 18).addItems(ETItems.ELECTRIC_COMPOSTER).register();
            new ElectricComposter(ElectricComposter.Tier.TWO) {
                @Override
                public int getEnergyConsumption() {
                    return 25;
                }

                @Override
                public int getSpeed() {
                    return 4;
                }
            }.register(addon);
            new Research(new NamespacedKey(plugin, "ELECTRIC_COMPOSTER_2"), ++researchId, "Electric Composter II", 18).addItems(ETItems.ELECTRIC_COMPOSTER_2).register();
            new CobblestoneGenerator().register(addon);
            new Research(new NamespacedKey(plugin, "COBBLESTONE_GENERATOR"), ++researchId, "Cobblestone Generator", 40).addItems(ETItems.COBBLESTONE_GENERATOR).register();
            new Vaporizer().register(addon);
            new Research(new NamespacedKey(plugin, "VAPORIZER"), ++researchId, "Vaporizer", 18).addItems(ETItems.VAPORIZER).register();
            new ConcreteFactory().register(addon);
            new Research(new NamespacedKey(plugin, "CONCRETE_FACTORY"), ++researchId, "Concrete Factory", 12).addItems(ETItems.CONCRETE_FACTORY).register();
            new Pulverizer().register(addon);
            new Research(new NamespacedKey(plugin, "PULVERIZER"), ++researchId, "Pulverizer", 18).addItems(ETItems.PULVERIZER).register();
            System.out.println(prefix + " §bSuccessfully Registered §dExtraTools §bAddon");
        }
    }
}