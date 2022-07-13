package id.universenetwork.utilities.bukkit.libraries.InfinityLib.Machines;

import id.universenetwork.utilities.bukkit.libraries.InfinityLib.Common.StackUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.consumeItem;

final class MachineBlockRecipe {
    final String[] strings;
    final int[] amounts;
    final ItemStack output;
    Map<String, MachineInput> lastMatch;

    MachineBlockRecipe(ItemStack output, ItemStack[] input) {
        this.output = output;
        Map<String, Integer> strings = new java.util.HashMap<>();
        for (ItemStack item : input)
            if (item != null && !item.getType().isAir()) {
                String string = StackUtils.getId(item);
                if (string == null) string = item.getType().name();
                strings.compute(string, (k, v) -> v == null ? item.getAmount() : v + item.getAmount());
            }
        this.strings = strings.keySet().toArray(new String[0]);
        amounts = ArrayUtils.toPrimitive(strings.values().toArray(new Integer[0]));
    }

    boolean check(Map<String, MachineInput> map) {
        for (int i = 0; i < strings.length; i++) {
            MachineInput input = map.get(strings[i]);
            if (input == null || input.amount < amounts[i]) return false;
        }
        lastMatch = map;
        return true;
    }

    void consume() {
        for (int i = 0; i < strings.length; i++) {
            int consume = amounts[i];
            for (ItemStack item : lastMatch.get(strings[i]).items) {
                int amt = item.getAmount();
                if (amt >= consume) {
                    consumeItem(item, consume, true);
                    break;
                } else {
                    consumeItem(item, amt, true);
                    consume -= amt;
                }
            }
        }
    }
}