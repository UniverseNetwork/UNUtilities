package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery;

import id.universenetwork.utilities.Bukkit.Events.UNUtilitiesDisableEvent;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.EvtLambdaWhen;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.DynamicEnumTypes;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaCondition;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Menus.FormattedSlotManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SQL.ScriptCredentials.clear;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getPluginManager;

public class SkQuery implements Listener {
    public static Boolean LIME_EDIT = true;

    public SkQuery() {
        if (Enabled("SkQuery")) {
            DynamicEnumTypes.register();
            addon.setLanguageFileDirectory("Lang");
            Registration.enableSnooper();
            getPluginManager().registerEvents(new FormattedSlotManager(), plugin);
            getPluginManager().registerEvents(this, plugin);
            //new Documentation(this);
            System.out.println(prefix + " §bSuccessfully Registered §6SkQuery §bAddon");
        }
    }

    @EventHandler
    public void onDisable(UNUtilitiesDisableEvent e) {
        clear();
        Set<LambdaCondition> limiter = EvtLambdaWhen.limiter;
        if (limiter == null || limiter.isEmpty()) return;
        EvtLambdaWhen.limiter.clear();
    }
}