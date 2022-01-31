package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Generators;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore.format;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore.formatEnergy;
import static org.bukkit.Material.GREEN_STAINED_GLASS_PANE;

@ParametersAreNonnullByDefault
public final class EnergyGenerator extends MenuBlock implements EnergyNetProvider {
    final GenerationType type;
    final int generation;

    public EnergyGenerator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int generation, GenerationType type) {
        super(category, item, recipeType, recipe);
        this.type = type;
        this.generation = generation;
    }

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
    }

    @NotNull
    @Override
    protected int[] getInputSlots(DirtyChestMenu dirtyChestMenu, ItemStack itemStack) {
        return new int[0];
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l);
        int gen = type.generate(Objects.requireNonNull(l.getWorld()), l.getBlock(), generation);
        if (inv.hasViewer()) {
            if (gen == 0)
                inv.replaceExistingItem(4, new CustomItemStack(GREEN_STAINED_GLASS_PANE, "&cNot generating", "&7Stored: &6" + format(getCharge(l)) + " J"));
            else
                inv.replaceExistingItem(4, new CustomItemStack(GREEN_STAINED_GLASS_PANE, "&aGeneration", "&7Type: &6" + type, "&7Generating: &6" + formatEnergy(gen) + " J/s ", "&7Stored: &6" + format(getCharge(l)) + " J"));
        }
        return gen;
    }

    @Override
    public int getCapacity() {
        return generation * 100;
    }

    @NotNull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }
}