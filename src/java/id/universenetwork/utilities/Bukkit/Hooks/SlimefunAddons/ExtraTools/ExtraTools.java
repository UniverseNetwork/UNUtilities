package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Machines.*;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.*;
import static id.universenetwork.utilities.Bukkit.UNUtilities.createKey;

public class ExtraTools extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFInstance {
    int researchId = 4100;

    @Override
    public void Load() {
        new id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Tools.Hammer().register(this);
        new Research(createKey("HAMMER"), ++researchId, "Hammer", 3).addItems(HAMMER).register();
        new GoldTransmuter().register(this);
        new Research(createKey("GOLD_TRANSMUTER"), ++researchId, "Gold Transmuter", 12).addItems(GOLD_TRANSMUTER).register();
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
        new Research(createKey("ELECTRIC_COMPOSTER"), ++researchId, "Electric Composter", 18).addItems(ELECTRIC_COMPOSTER).register();
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
        new Research(createKey("ELECTRIC_COMPOSTER_2"), ++researchId, "Electric Composter II", 18).addItems(ELECTRIC_COMPOSTER_2).register();
        new CobblestoneGenerator().register(this);
        new Research(createKey("COBBLESTONE_GENERATOR"), ++researchId, "Cobblestone Generator", 40).addItems(COBBLESTONE_GENERATOR).register();
        new Vaporizer().register(this);
        new Research(createKey("VAPORIZER"), ++researchId, "Vaporizer", 18).addItems(VAPORIZER).register();
        new ConcreteFactory().register(this);
        new Research(createKey("CONCRETE_FACTORY"), ++researchId, "Concrete Factory", 12).addItems(CONCRETE_FACTORY).register();
        new Pulverizer().register(this);
        new Research(createKey("PULVERIZER"), ++researchId, "Pulverizer", 18).addItems(PULVERIZER).register();
    }
}