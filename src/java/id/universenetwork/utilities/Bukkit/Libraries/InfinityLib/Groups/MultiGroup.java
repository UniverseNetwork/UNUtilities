package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Groups;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideImplementation;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getRegistry;
import static io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.*;
import static java.util.Comparator.comparingInt;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.Sound.ITEM_BOOK_PAGE_TURN;

/**
 * A multi group which can hold other groups
 *
 * @author Mooy1
 */
@ParametersAreNonnullByDefault
public final class MultiGroup extends FlexItemGroup {
    final ItemGroup[] subGroups;
    final String name;

    public MultiGroup(String key, ItemStack item, ItemGroup... subGroups) {
        this(key, item, 3, subGroups);
    }

    public MultiGroup(String key, ItemStack item, int tier, ItemGroup... subGroups) {
        super(new NamespacedKey(plugin, key), item, tier);
        Arrays.sort(subGroups, comparingInt(ItemGroup::getTier));
        this.subGroups = subGroups;
        this.name = ItemUtils.getItemName(item);
    }

    @Override
    public boolean isVisible(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        return mode.equals(SlimefunGuideMode.SURVIVAL_MODE);
    }

    @Override
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        openGuide(p, profile, mode, 1);
    }

    void openGuide(Player p, PlayerProfile profile, SlimefunGuideMode mode, int page) {
        SlimefunGuideImplementation guide = getRegistry().getSlimefunGuide(mode);
        profile.getGuideHistory().add(this, page);
        ChestMenu menu = new ChestMenu(name);
        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(pl -> pl.playSound(pl.getLocation(), ITEM_BOOK_PAGE_TURN, 1, 1));
        for (int i = 0; i < 9; i++)
            menu.addItem(i, getBackground(), getEmptyClickHandler());
        String back = GRAY + getLocalization().getMessage(p, "guide.back.guide");
        menu.addItem(1, getBackButton(p, "", back));
        menu.addMenuClickHandler(1, (pl, s, is, action) -> {
            profile.getGuideHistory().goBack(guide);
            return false;
        });
        int index = 9;
        int target = (36 * (page - 1)) - 1;
        while (target < (subGroups.length - 1) && index < 45) {
            target++;
            ItemGroup category = subGroups[target];
            menu.addItem(index, category.getItem(p));
            menu.addMenuClickHandler(index, (pl, slot, item, action) -> {
                SlimefunGuide.openItemGroup(profile, category, mode, 1);
                return false;
            });
            index++;
        }
        int pages = target == subGroups.length - 1 ? page : (subGroups.length - 1) / 36 + 1;
        for (int i = 45; i < 54; i++)
            menu.addItem(i, getBackground(), getEmptyClickHandler());
        menu.addItem(46, getPreviousButton(p, page, pages));
        menu.addMenuClickHandler(46, (pl, slot, item, action) -> {
            int next = page - 1;
            if (next > 0) openGuide(p, profile, mode, next);
            return false;
        });
        menu.addItem(52, getNextButton(p, page, pages));
        menu.addMenuClickHandler(52, (pl, slot, item, action) -> {
            int next = page + 1;
            if (next <= pages) openGuide(p, profile, mode, next);
            return false;
        });
        menu.open(p);
    }

    @Override
    public void register(SlimefunAddon addon) {
        super.register(addon);
        for (ItemGroup category : subGroups) if (!category.isRegistered()) category.register(addon);
    }
}