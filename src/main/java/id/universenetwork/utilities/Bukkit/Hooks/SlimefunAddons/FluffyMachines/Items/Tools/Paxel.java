package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.FluffyItems;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Paxel extends SlimefunItem implements org.bukkit.event.Listener, NotPlaceable {
    public final Set<Material> axeBlocks = Stream.of(Tag.LOGS.getValues(), Tag.PLANKS.getValues(), Tag.WOODEN_STAIRS.getValues(), Tag.SIGNS.getValues(), Tag.WOODEN_FENCES.getValues(), Tag.FENCE_GATES.getValues(), Tag.WOODEN_TRAPDOORS.getValues(), Tag.WOODEN_PRESSURE_PLATES.getValues(), Tag.WOODEN_DOORS.getValues(), Tag.WOODEN_SLABS.getValues(), Tag.WOODEN_BUTTONS.getValues(), Tag.BANNERS.getValues(), Tag.LEAVES.getValues(), new HashSet<>(Arrays.asList(Material.CHEST, Material.TRAPPED_CHEST, Material.CRAFTING_TABLE, Material.SMITHING_TABLE, Material.LOOM, Material.CARTOGRAPHY_TABLE, Material.FLETCHING_TABLE, Material.BARREL, Material.JUKEBOX, Material.CAMPFIRE, Material.BOOKSHELF, Material.JACK_O_LANTERN, Material.CARVED_PUMPKIN, Material.PUMPKIN, Material.MELON, Material.COMPOSTER, Material.BEEHIVE, Material.BEE_NEST, Material.NOTE_BLOCK, Material.LADDER, Material.COCOA_BEANS, Material.DAYLIGHT_DETECTOR, Material.MUSHROOM_STEM, Material.RED_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK, Material.BAMBOO, Material.VINE, Material.LECTERN))).flatMap(Set::stream).collect(Collectors.toSet());

    public Paxel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        Events.registerListeners(this);
    }

    @EventHandler(ignoreCancelled = true)
    void onMine(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
        Player p = e.getPlayer();
        SlimefunItem sfItem = SlimefunItem.getByItem(p.getInventory().getItemInMainHand());
        if (sfItem != null && sfItem.equals(FluffyItems.PAXEL.getItem())) {
            boolean netherite = false;
            Block b = e.getClickedBlock();
            ItemStack item = p.getInventory().getItemInMainHand();
            if (b == null) return;
            Material blockType = b.getType();
            if (item.getType().equals(Material.NETHERITE_PICKAXE) || item.getType().equals(Material.NETHERITE_AXE) || item.getType().equals(Material.NETHERITE_SHOVEL))
                netherite = true;
            if (SlimefunTag.EXPLOSIVE_SHOVEL_BLOCKS.isTagged(blockType)) {
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
    void onEntityHit(EntityDamageByEntityEvent e) {
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