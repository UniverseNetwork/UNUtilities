package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class AlternateBreakEvent extends BlockBreakEvent {
    public AlternateBreakEvent(Block b, Player p) {
        super(b, p);
    }
}