package id.universenetwork.utilities.bukkit.features.SlimefunAddons.SlimefunOreChunks;

import id.universenetwork.utilities.bukkit.features.SlimefunAddons.SfAddon;
import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;

public class SlimefunOreChunks extends SfAddon {
    @Override
    public void Load() {
        ItemGroup itemGroup = new ItemGroup(UNUtilities.createKey("ore_chunks"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("dde8f949bbf3a42782c531fbf8de9dc2d8cd84dd7cb8f5d5328eeda83956aac8")), "&6Ore Chunks"));
        new OreChunk(itemGroup, "IRON_ORE_CHUNK", "Iron Ore Chunk", 4, "44cc1ccc75d0f724af8a5fe273edaf4c6d5951f9e4d038f9f16e4f2673ce3833", SlimefunItems.IRON_DUST).register(this);
        new OreChunk(itemGroup, "GOLD_ORE_CHUNK", "Gold Ore Chunk", 2, "3184478b211439f3e2c509c3424ea5ff2fce73825c8bebf96cfccd103e4922eb", SlimefunItems.GOLD_DUST).register(this);
        new OreChunk(itemGroup, "COPPER_ORE_CHUNK", "Copper Ore Chunk", 5, "60d748757d6efddde852e0a4a1a9b92f2e4c58b1ea9a1731a32f6cedf2c23b36", SlimefunItems.COPPER_DUST).register(this);
        new OreChunk(itemGroup, "TIN_ORE_CHUNK", "Tin Ore Chunk", 3, "de2c955177ff65a2d55af17743755090a5a6b68b3586ccbc31a342dad9ef7799", SlimefunItems.TIN_DUST).register(this);
        new OreChunk(itemGroup, "SILVER_ORE_CHUNK", "Silver Ore Chunk", 2, "dde8f949bbf3a42782c531fbf8de9dc2d8cd84dd7cb8f5d5328eeda83956aac8", SlimefunItems.SILVER_DUST).register(this);
        new OreChunk(itemGroup, "ALUMINUM_ORE_CHUNK", "Aluminum Ore Chunk", 4, "46732368c980b4c27495664bd50b5820cc37c573fb37a88f34c5d3a0dec66219", SlimefunItems.ALUMINUM_DUST).register(this);
        new OreChunk(itemGroup, "LEAD_ORE_CHUNK", "Lead Ore Chunk", 2, "2333fcec07c89c5fdb886caf5e3ebf8c6a536dd662b31f91c1a6dbd913bc3db0", SlimefunItems.LEAD_DUST).register(this);
        new OreChunk(itemGroup, "ZINC_ORE_CHUNK", "Zinc Ore Chunk", 3, "63f82f20266b4b8e0456110379f941fca16413846e231e8ac202dc2caf7ffb41", SlimefunItems.ZINC_DUST).register(this);
        new OreChunk(itemGroup, "MAGNESIUM_ORE_CHUNK", "Magnesium Ore Chunk", 4, "e8c99d857a5b34331699ce6b5449d8d75f6c50b294ea1a29108f66ca086528bb", SlimefunItems.MAGNESIUM_DUST).register(this);
        new OreChunk(itemGroup, "NICKEL_ORE_CHUNK", "Nickel Ore Chunk", "&7You can smelt this into an Ingot in a Smeltery", 2, "3ba30df8316cdfe3c5b1ad7aa9775c94c3ad5e502ea1254efeb41344f7962381", RecipeType.SMELTERY, SlimefunItems.NICKEL_INGOT).register(this);
        new OreChunk(itemGroup, "COBALT_ORE_CHUNK", "Cobalt Ore Chunk", "&7You can smelt this into an Ingot in a Smeltery", 1, "ec54a54b1a49c29686be1c6e3e05dd068f85e994c8c893838cc5878b5446bc8a", RecipeType.SMELTERY, SlimefunItems.COBALT_INGOT).register(this);
    }
}