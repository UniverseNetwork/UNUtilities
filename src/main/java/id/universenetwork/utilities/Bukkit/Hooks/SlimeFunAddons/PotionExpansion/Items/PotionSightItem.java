package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects.EffectsManager;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects.PotionSightEffect;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects.PotionSightType;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Settings;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import org.bukkit.inventory.ItemStack;

public class PotionSightItem extends SlimefunItem {
    final PotionSightType potionSightType;

    public PotionSightItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, PotionSightType potionSightType) {
        super(itemGroup, item, recipeType, recipe);
        this.potionSightType = potionSightType;
    }

    @Override
    public void preRegister() {
        addItemHandler(onConsume());
    }

    ItemConsumptionHandler onConsume() {
        return (e, p, item) -> EffectsManager.addEffect(p, new PotionSightEffect(potionSightType, Settings.getPotionDuration()));
    }
}