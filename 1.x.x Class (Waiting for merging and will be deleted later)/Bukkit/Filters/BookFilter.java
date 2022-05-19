package id.universenetwork.utilities.Bukkit.Filters;

import id.universenetwork.utilities.Bukkit.Handlers.BookExploitHandler;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static id.universenetwork.utilities.Bukkit.Filters.FilterAction.CREATE;
import static id.universenetwork.utilities.Bukkit.Handlers.BookExploitHandler.limitLoggingString;
import static java.util.logging.Level.WARNING;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Material.WRITABLE_BOOK;
import static org.bukkit.Material.WRITTEN_BOOK;

public final class BookFilter {
    final Set<ClickEvent.Action> filterActions;
    final BookExploitHandler config;

    public BookFilter(@NotNull final Set<ClickEvent.Action> filterActions, @NotNull final BookExploitHandler config) {
        this.filterActions = filterActions;
        this.config = config;
    }

    @Nullable
    public ItemStack filterBook(@Nullable final ItemStack originalItem, @NotNull final Player player, @NotNull final FilterAction action) {
        if (originalItem == null) return null;
        if (originalItem.getType() != WRITTEN_BOOK && originalItem.getType() != WRITABLE_BOOK)
            return null;
        ItemStack newItem = originalItem.clone();
        ItemMeta itemMeta = newItem.getItemMeta();
        if (!(itemMeta instanceof BookMeta)) return null;
        final BookMeta filteredBookMeta = filterBookMeta((BookMeta) itemMeta, player, action);
        if (filteredBookMeta != null) {
            newItem.setItemMeta(filteredBookMeta);
            return newItem;
        } else return null;
    }

    @Nullable
    public BookMeta filterBookMeta(@NotNull final BookMeta bookMeta, @NotNull final Player player, @NotNull final FilterAction action) {
        if (config.isWorldDisabled(player.getWorld())) return null;
        if (!config.checkAction(action)) return null;
        if (action == CREATE && player.hasPermission("unutilities.bookexploitfix.exempt")) return null;

        // Spigot returns an anonymous object inheriting from AbstractList, which lazily parses each page on access.
        // This has the side-effect that each get(i) method returns a new object parsed from the original, overwriting
        // any modifications to the old reference. Since we need all pages anyway, we just store them in a regular list.
        final List<BaseComponent[]> pages = new ArrayList<>(bookMeta.spigot().getPages());
        boolean bookFiltered = false;
        for (final BaseComponent[] page : pages) {
            final boolean pageFiltered = filterPage(page);
            bookFiltered = bookFiltered || pageFiltered;
        }
        if (bookFiltered) {
            bookMeta.spigot().setPages(pages);
            return bookMeta;
        } else return null;
    }

    boolean filterPage(@NotNull final BaseComponent[] page) {
        boolean pageFiltered = false;
        for (final BaseComponent component : page) {
            final boolean componentFiltered = filterComponent(component);
            pageFiltered = pageFiltered || componentFiltered;
        }
        return pageFiltered;
    }

    boolean filterComponent(@NotNull final BaseComponent component) {
        boolean componentFiltered = false;
        final ClickEvent clickEvent = component.getClickEvent();
        if (clickEvent != null && filterActions.contains(clickEvent.getAction())) {
            String content = clickEvent.getValue();
            if (!config.isContentPermitted(content)) {
                component.setClickEvent(null);
                componentFiltered = true;
                getLogger().log(WARNING, "Filtered illegal content from item!");
                getLogger().log(WARNING, limitLoggingString(clickEvent.toString()));
            }
        }
        final List<BaseComponent> extra = component.getExtra();
        if (extra != null) for (final BaseComponent subComponent : extra) {
            final boolean subComponentFiltered = filterComponent(subComponent);
            componentFiltered = componentFiltered || subComponentFiltered;
        }
        return componentFiltered;
    }
}