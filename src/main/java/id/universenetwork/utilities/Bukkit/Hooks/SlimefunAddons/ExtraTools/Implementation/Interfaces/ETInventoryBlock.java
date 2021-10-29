package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Interfaces;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

public interface ETInventoryBlock {
    int[] getInputSlots();

    int[] getOutputSlots();

    default void createPreset(io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem item, java.util.function.Consumer<BlockMenuPreset> setup) {
        String title = item.getItemName();
        new BlockMenuPreset(item.getId(), title) {
            @Override
            public void init() {
                setup.accept(this);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return flow == ItemTransportFlow.INSERT ? getInputSlots() : getOutputSlots();
            }

            @Override
            public boolean canOpen(org.bukkit.block.Block b, org.bukkit.entity.Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK) && io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getPermissionsService().hasPermission(p, item);
            }
        };
    }
}