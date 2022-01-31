package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Tools;

import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.HAMMER;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems.extra_tools;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MAGIC_WORKBENCH;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getPermissionsService;
import static org.bukkit.Material.IRON_INGOT;
import static org.bukkit.Material.STICK;

public class Hammer extends SimpleSlimefunItem<ToolUseHandler> {
    public Hammer() {
        super(extra_tools, HAMMER, MAGIC_WORKBENCH, new ItemStack[]{new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), new ItemStack(STICK), new ItemStack(IRON_INGOT), null, new ItemStack(STICK), null});
    }

    @NotNull
    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            if (getPermissionsService().hasPermission(e.getPlayer(), Hammer.this)) {
                Block b = e.getBlock();
                ItemStack drop = getDrop(b);
                if (drop != null) {
                    //Can't throw NPEs now
                    b.getLocation().getWorld().dropItemNaturally(b.getLocation(), drop);
                    e.setDropItems(false);
                }
            }
        };
    }

    public ItemStack getDrop(Block b) {
        Material m = b.getType();
        switch (m) {
            case STONE:
            case GRANITE:
            case DIORITE:
            case ANDESITE:
            case COBBLESTONE:
                return new ItemStack(Material.GRAVEL);
            case GRAVEL:
            case GRASS_BLOCK:
            case DIRT:
            case COARSE_DIRT:
            case PODZOL:
                return new ItemStack(Material.SAND);
            case IRON_ORE:
                return SlimefunItems.IRON_DUST;
            case GOLD_ORE:
                return SlimefunItems.GOLD_DUST;
            case NETHERRACK:
                return new ItemStack(Material.SOUL_SAND);
        }
        return null;
    }
}