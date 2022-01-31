package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Abstracts;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.inventory.ItemStack;

@javax.annotation.ParametersAreNonnullByDefault
public abstract class AbstractEnergyCrafter extends id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.TickingMenuBlock implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    protected final int energy;
    protected final int statusSlot;

    public AbstractEnergyCrafter(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe, int energy, int statusSlot) {
        super(itemGroup, item, recipeType, recipe);
        this.energy = energy;
        this.statusSlot = statusSlot;
    }

    @Override
    protected final void tick(org.bukkit.block.Block block, BlockMenu blockMenu) {
        if (blockMenu.hasViewer()) {
            int charge = getCharge(block.getLocation());
            //not enough energy
            if (charge < this.energy)
                blockMenu.replaceExistingItem(this.statusSlot, new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(org.bukkit.Material.RED_STAINED_GLASS_PANE, "&cNot enough energy!", "", "&aCharge: " + charge + "/" + this.energy + " J", ""));
            else update(blockMenu);
        }
    }

    public abstract void update(BlockMenu blockMenu);

    @org.jetbrains.annotations.NotNull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public final int getCapacity() {
        return this.energy;
    }

    @Override
    protected final int[] getInputSlots(me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu menu, ItemStack item) {
        return new int[0];
    }
}