package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import org.bukkit.inventory.ItemStack;

final class MachineInput {
    final java.util.List<ItemStack> items = new java.util.ArrayList<>(2);
    int amount;

    MachineInput(ItemStack item) {
        add(item);
    }

    MachineInput add(ItemStack item) {
        items.add(item);
        amount += item.getAmount();
        return this;
    }
}