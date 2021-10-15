package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.CraftingBlock;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineRecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class MobDataInfuser extends CraftingBlock implements EnergyNetComponent {
    static final MachineRecipeType TYPE = new MachineRecipeType("mob_data_infuser", MobData.INFUSER);
    final int energy;

    public MobDataInfuser(ItemGroup category, SlimefunItemStack stack, RecipeType type, ItemStack[] recipe, int energy) {
        super(category, stack, type, recipe);
        addRecipesFrom(TYPE);
        this.energy = energy;
    }

    @Override
    protected void craft(Block b, BlockMenu menu, Player p) {
        if (getCharge(menu.getLocation()) < this.energy) p.sendMessage(ChatColor.RED + "Not enough energy!");
        else super.craft(b, menu, p);
    }

    @Override
    protected void onSuccessfulCraft(BlockMenu menu, ItemStack toOutput) {
        setCharge(menu.getLocation(), 0);
    }

    @NotNull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return this.energy;
    }
}