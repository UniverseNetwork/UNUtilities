package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

import static org.bukkit.Tag.*;

public class Paxel extends SlimefunItem implements org.bukkit.event.Listener, io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public final Set<Material> axeBlocks = java.util.stream.Stream.of(LOGS.getValues(), PLANKS.getValues(), WOODEN_STAIRS.getValues(), SIGNS.getValues(), WOODEN_FENCES.getValues(), FENCE_GATES.getValues(), WOODEN_TRAPDOORS.getValues(), WOODEN_PRESSURE_PLATES.getValues(), WOODEN_DOORS.getValues(), WOODEN_SLABS.getValues(), WOODEN_BUTTONS.getValues(), BANNERS.getValues(), LEAVES.getValues(), new java.util.HashSet<>(java.util.Arrays.asList(Material.CHEST, Material.TRAPPED_CHEST, Material.CRAFTING_TABLE, Material.SMITHING_TABLE, Material.LOOM, Material.CARTOGRAPHY_TABLE, Material.FLETCHING_TABLE, Material.BARREL, Material.JUKEBOX, Material.CAMPFIRE, Material.BOOKSHELF, Material.JACK_O_LANTERN, Material.CARVED_PUMPKIN, Material.PUMPKIN, Material.MELON, Material.COMPOSTER, Material.BEEHIVE, Material.BEE_NEST, Material.NOTE_BLOCK, Material.LADDER, Material.COCOA_BEANS, Material.DAYLIGHT_DETECTOR, Material.MUSHROOM_STEM, Material.RED_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK, Material.BAMBOO, Material.VINE, Material.LECTERN))).flatMap(Set::stream).collect(java.util.stream.Collectors.toSet());

    public Paxel(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @EventHandler(ignoreCancelled = true)
    void onMine(org.bukkit.event.player.PlayerInteractEvent e) {
        if (!e.getAction().equals(org.bukkit.event.block.Action.LEFT_CLICK_BLOCK)) return;
        Player p = e.getPlayer();
        SlimefunItem sfItem = SlimefunItem.getByItem(p.getInventory().getItemInMainHand());
        if (sfItem != null && sfItem.equals(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.FluffyItems.PAXEL.getItem())) {
            boolean netherite = false;
            org.bukkit.block.Block b = e.getClickedBlock();
            ItemStack item = p.getInventory().getItemInMainHand();
            if (b == null) return;
            Material blockType = b.getType();
            if (item.getType().equals(Material.NETHERITE_PICKAXE) || item.getType().equals(Material.NETHERITE_AXE) || item.getType().equals(Material.NETHERITE_SHOVEL))
                netherite = true;
            if (io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag.EXPLOSIVE_SHOVEL_BLOCKS.isTagged(blockType)) {
                if (netherite) item.setType(Material.NETHERITE_SHOVEL);
                else item.setType(Material.DIAMOND_SHOVEL);
            } else if (axeBlocks.contains(blockType)) {
                if (netherite) item.setType(Material.NETHERITE_AXE);
                else item.setType(Material.DIAMOND_AXE);
            } else {
                if (netherite) item.setType(Material.NETHERITE_PICKAXE);
                else item.setType(Material.DIAMOND_PICKAXE);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    void onEntityHit(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        ItemStack item = p.getInventory().getItemInMainHand();
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem instanceof Paxel) {
            boolean netherite = item.getType().equals(Material.NETHERITE_PICKAXE) || item.getType().equals(Material.NETHERITE_AXE) || item.getType().equals(Material.NETHERITE_SHOVEL);
            if (netherite) item.setType(Material.NETHERITE_AXE);
            else item.setType(Material.DIAMOND_AXE);
        }
    }
}