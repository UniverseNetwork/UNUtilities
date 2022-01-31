package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.TESSERACT;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Transfer.Tesseract.*;

public class TesseractBinder extends SlimefunItem {
    public TesseractBinder(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(bindTesseract());
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler bindTesseract() {
        return e -> {
            e.cancel();
            Optional<org.bukkit.block.Block> block = e.getClickedBlock();
            Optional<SlimefunItem> sfBlock = e.getSlimefunBlock();
            if (block.isPresent() && sfBlock.isPresent()) {
                org.bukkit.Location blockLocation = block.get().getLocation();
                SlimefunItem sfItem = sfBlock.get();
                ItemStack item = e.getItem();
                if (e.getPlayer().isSneaking()) {
                    String locString = PersistentDataAPI.getString(item.getItemMeta(), WIRELESS_LOCATION_KEY);
                    if (item != null && BlockStorage.checkID(blockLocation).equals(TESSERACT.getItemId()) && item.hasItemMeta() && locString != null) {
                        BlockStorage.addBlockInfo(blockLocation, "tesseract-pair-location", locString);
                        e.getPlayer().spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(org.bukkit.ChatColor.WHITE + "Tesseract Connected!"));
                    }
                } else if (sfBlock.isPresent() && sfItem.getId().equals(TESSERACT.getItemId()) && blockLocation != null) {
                    org.bukkit.inventory.meta.ItemMeta im = item.getItemMeta();
                    String locString = LocationToString(blockLocation);
                    PersistentDataAPI.setString(im, WIRELESS_LOCATION_KEY, locString);
                    item.setItemMeta(im);
                    setItemLore(item, blockLocation);
                }
            }
        };
    }
}