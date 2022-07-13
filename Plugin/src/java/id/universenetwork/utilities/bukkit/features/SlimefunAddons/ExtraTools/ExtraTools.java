package id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools;

import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Machines.CobblestoneGenerator;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Machines.ConcreteFactory;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Machines.ElectricComposter;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Machines.GoldTransmuter;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Machines.Pulverizer;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Machines.Vaporizer;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.ExtraTools.Implementations.Tool.Hammer;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.SfAddon;
import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

public class ExtraTools extends SfAddon {
    private int researchId = 4100;

    @Override
    public void Load() {
        new Hammer().register(this);
        new Research(UNUtilities.createKey("HAMMER"),
                ++researchId, "Hammer", 3)
                .addItems(ETItems.HAMMER).register();
        new GoldTransmuter().register(this);
        new Research(UNUtilities.createKey("GOLD_TRANSMUTER"),
                ++researchId, "Gold Transmuter", 12)
                .addItems(ETItems.GOLD_TRANSMUTER).register();
        new ElectricComposter(ElectricComposter.Tier.ONE) {
            @Override
            public int getEnergyConsumption() {
                return 9;
            }

            @Override
            public int getSpeed() {
                return 1;
            }
        }.register(this);
        new Research(UNUtilities.createKey("ELECTRIC_COMPOSTER"),
                ++researchId, "Electric Composter", 18)
                .addItems(ETItems.ELECTRIC_COMPOSTER).register();
        new ElectricComposter(ElectricComposter.Tier.TWO) {
            @Override
            public int getEnergyConsumption() {
                return 25;
            }

            @Override
            public int getSpeed() {
                return 4;
            }
        }.register(this);
        new Research(UNUtilities.createKey("ELECTRIC_COMPOSTER_2"),
                ++researchId, "Electric Composter II", 18)
                .addItems(ETItems.ELECTRIC_COMPOSTER_2).register();
        new CobblestoneGenerator().register(this);
        new Research(UNUtilities.createKey("COBBLESTONE_GENERATOR"),
                ++researchId, "Cobblestone Generator", 40)
                .addItems(ETItems.COBBLESTONE_GENERATOR).register();
        new Vaporizer().register(this);
        new Research(UNUtilities.createKey("VAPORIZER"),
                ++researchId, "Vaporizer", 18)
                .addItems(ETItems.VAPORIZER).register();
        new ConcreteFactory().register(this);
        new Research(UNUtilities.createKey("CONCRETE_FACTORY"),
                ++researchId, "Concrete Factory", 12)
                .addItems(ETItems.CONCRETE_FACTORY).register();
        new Pulverizer().register(this);
        new Research(UNUtilities.createKey("PULVERIZER"),
                ++researchId, "Pulverizer", 18)
                .addItems(ETItems.PULVERIZER).register();
    }
}