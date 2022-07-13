package id.universenetwork.utilities.bukkit.libraries.InfinityLib.Machines;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

final class MachineInput {
    final List<ItemStack> items = new ArrayList<>(2);
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