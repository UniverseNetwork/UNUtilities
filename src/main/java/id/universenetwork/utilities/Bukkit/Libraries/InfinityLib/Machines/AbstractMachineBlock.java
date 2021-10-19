package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@Setter
@ParametersAreNonnullByDefault
public abstract class AbstractMachineBlock extends TickingMenuBlock implements EnergyNetComponent {
    protected int energyPerTick = -1;
    protected int energyCapacity = -1;

    public AbstractMachineBlock(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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

    @NotNull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public final void register(@NotNull SlimefunAddon addon) {
        if (energyPerTick == -1) throw new IllegalStateException("You must call .energyPerTick() before registering!");
        if (energyCapacity == -1) energyCapacity = energyPerTick * 2;
        super.register(addon);
    }
}