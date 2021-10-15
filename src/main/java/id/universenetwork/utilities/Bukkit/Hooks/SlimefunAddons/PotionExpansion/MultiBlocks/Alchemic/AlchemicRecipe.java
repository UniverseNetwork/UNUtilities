package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.MultiBlocks.Alchemic;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class AlchemicRecipe {
    final ItemStack ingredient;
    final PotionType potion;
    final ItemStack output;

    @ParametersAreNonnullByDefault
    public AlchemicRecipe(List<ItemStack> input, ItemStack output) {
        this.ingredient = input.get(1);
        this.potion = getPotionType(input.get(7));
        this.output = output;
    }

    private PotionType getPotionType(@NotNull ItemStack potion) {
        if (potion.getType() == Material.POTION && potion.hasItemMeta()) {
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            return meta.getBasePotionData().getType();
        }
        return null;
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    public PotionType getPotion() {
        return potion;
    }

    public ItemStack getOutput() {
        return output;
    }
}