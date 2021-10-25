package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.StackUtils.isSimilar;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.consumeItem;

@Getter
public final class CraftingBlockRecipe {
    final ItemStack[] recipe;
    final ItemStack output;
    final SlimefunItem item;

    CraftingBlockRecipe(ItemStack output, ItemStack[] recipe) {
        this.output = output;
        this.recipe = ItemStackSnapshot.wrapArray(recipe);
        this.item = SlimefunItem.getByItem(output);
    }

    boolean check(ItemStackSnapshot[] input) {
        for (int i = 0; i < recipe.length; i++) {
            boolean similar = isSimilar(input[i], recipe[i]);
            if (!similar || (recipe[i] != null && recipe[i].getAmount() > input[i].getAmount())) return false;
        }
        return true;
    }

    boolean check(Player p) {
        return item == null || item.canUse(p, true);
    }

    void consume(ItemStack[] input) {
        for (int i = 0; i < recipe.length; i++)
            if (recipe[i] != null) consumeItem(input[i], recipe[i].getAmount(), true);
    }
}