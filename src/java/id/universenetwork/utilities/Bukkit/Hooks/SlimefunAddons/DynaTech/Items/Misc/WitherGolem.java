package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.block.BlockFace.*;

public class WitherGolem extends io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine {
    public WitherGolem(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item) {
        super(itemGroup, item, new ItemStack[]{null, new ItemStack(Material.CARVED_PUMPKIN), null, null, new ItemStack(Material.POLISHED_BLACKSTONE), null, null, new ItemStack(Material.POLISHED_BLACKSTONE), null}, SELF);
    }

    @Override
    public void onInteract(@NotNull Player p, @NotNull Block b) {
        Block pumpkinHead = b.getRelative(UP);
        Block bottomBlackstone = b.getRelative(DOWN);
        p.getWorld().spawnEntity(p.getLocation(), org.bukkit.entity.EntityType.WITHER_SKELETON);
        pumpkinHead.setType(Material.AIR);
        b.setType(Material.AIR);
        bottomBlackstone.setType(Material.AIR);
    }
}