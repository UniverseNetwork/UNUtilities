package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimefunOreChunks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import org.bukkit.inventory.ItemStack;

public class OreChunk extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    final int amplifier;
    final String name;
    final MultiBlockMachine machine;
    final ItemStack output;

    public OreChunk(SlimefunOreChunks addon, ItemGroup itemGroup, String id, String name, int amplifier, String texture, ItemStack output) {
        this(addon, itemGroup, id, name, "&7Use an Ore Crusher to turn this into Dust", amplifier, texture, RecipeType.ORE_CRUSHER, output);
    }

    public OreChunk(SlimefunOreChunks addon, ItemGroup itemGroup, String ID, String name, String lore, int amplifier, String texture, RecipeType machine, ItemStack output) {
        super(itemGroup, new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack(ID, texture, "&r" + name, lore), RecipeType.GEO_MINER, new ItemStack[0]);
        this.amplifier = amplifier;
        this.name = name;
        this.machine = (MultiBlockMachine) machine.getMachine();
        this.output = output;
        new OreResource(id.universenetwork.utilities.Bukkit.UNUtilities.createKey(ID.toLowerCase(java.util.Locale.ROOT)), this).register();
    }

    public String getName() {
        return name;
    }

    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public void postRegister() {
        if (!machine.isDisabled()) machine.addRecipe(new ItemStack[]{getItem()}, output);
    }
}