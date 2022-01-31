package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.MultiBlocks;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.FluffyItems.GENERATOR_CORE;

public class CrankGenerator extends MultiBlockMachine implements EnergyNetProvider {
    public static final int RATE = 16;
    public static final int CAPACITY = 64;

    public CrankGenerator(ItemGroup itemGroup, SlimefunItemStack item) {
        super(itemGroup, item, new ItemStack[]{null, null, null, null, new ItemStack(Material.LEVER), null, null, GENERATOR_CORE, null}, BlockFace.SELF);
    }

    @Override
    public int getGeneratedOutput(@NotNull Location location, @NotNull Config config) {
        return 0;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public void onInteract(Player p, Block b) {
        String id;
        Block core = b.getRelative(BlockFace.DOWN);
        if (BlockStorage.hasBlockInfo(core)) {
            id = BlockStorage.getLocationInfo(core.getLocation(), "id");
            if (id.equals("GENERATOR_CORE")) {
                addCharge(core.getLocation(), RATE);
                p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 0.5F, 0.5F);
            } else Utils.send(p, "&cMissing generator core");
        } else Utils.send(p, "&cMissing generator core");
    }
}