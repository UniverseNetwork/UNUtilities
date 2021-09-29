package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.slimefunTickCount;

public final class PoweredBedrock extends SlimefunItem implements EnergyNetComponent {
    final int energy;

    public PoweredBedrock(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe, int energy) {
        super(category, item, type, recipe);
        this.energy = energy;
        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                if (slimefunTickCount % 8 == 0) return;
                Location l = b.getLocation();
                if (getCharge(l) < energy) {
                    if (b.getType() != Material.NETHERITE_BLOCK) b.setType(Material.NETHERITE_BLOCK);
                } else if (b.getType() != Material.BEDROCK) {
                    b.setType(Material.BEDROCK);
                    removeCharge(l, energy);
                }
            }
        });
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return this.energy * 2;
    }
}