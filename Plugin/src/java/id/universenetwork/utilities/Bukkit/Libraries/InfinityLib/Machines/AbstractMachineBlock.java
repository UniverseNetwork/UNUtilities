package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;

@lombok.Setter
public abstract class AbstractMachineBlock extends TickingMenuBlock implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    protected int energyPerTick = -1;
    protected int energyCapacity = -1;

    public AbstractMachineBlock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup category, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void tick(Block b, BlockMenu menu) {
        if (getCharge(menu.getLocation()) < energyPerTick) {
            if (menu.hasViewer()) menu.replaceExistingItem(getStatusSlot(), NO_ENERGY_ITEM);
        } else if (process(b, menu)) removeCharge(menu.getLocation(), energyPerTick);
    }

    protected abstract boolean process(Block b, BlockMenu menu);

    protected abstract int getStatusSlot();

    @Override
    public final int getCapacity() {
        return energyCapacity;
    }

    @Override
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public final void register(io.github.thebusybiscuit.slimefun4.api.SlimefunAddon addon) {
        if (energyPerTick == -1) throw new IllegalStateException("You must call .energyPerTick() before registering!");
        if (energyCapacity == -1) energyCapacity = energyPerTick * 2;
        super.register(addon);
    }
}