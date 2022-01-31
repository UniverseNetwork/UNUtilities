package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class SimpleBasePet extends HotbarPet {
    public SimpleBasePet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    public abstract void onUseItem(Player p);

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(onClick());
    }

    ItemUseHandler onClick() {
        return e -> {
            if (checkAndConsumeFood(e.getPlayer())) onUseItem(e.getPlayer());
        };
    }
}