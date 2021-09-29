package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization;

public class HotbarPet extends SlimefunItem {
    static final long MESSAGE_DELAY = 2000;
    static final Map<UUID, Long> messageDelay = new HashMap<>();
    final ItemStack food;

    public HotbarPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, ENHANCED_CRAFTING_TABLE, recipe);
        this.food = food;
    }

    public ItemStack getFavouriteFood() {
        return food;
    }

    public boolean checkAndConsumeFood(Player player) {
        if (!player.getInventory().containsAtLeast(getFavouriteFood(), 1)) {
            if (messageDelay.getOrDefault(player.getUniqueId(), 0L) <= System.currentTimeMillis()) {
                getLocalization().sendMessage(player, "hotbarpets.neglected-pet", true, msg -> msg.replace("%pet%", getItemName()));
                messageDelay.put(player.getUniqueId(), System.currentTimeMillis() + MESSAGE_DELAY);
            }
            return false;
        }
        player.getInventory().removeItem(getFavouriteFood());
        return true;
    }

    public static Map<UUID, Long> getMessageDelay() {
        return messageDelay;
    }
}