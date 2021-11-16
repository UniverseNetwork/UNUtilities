package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.EvtLambdaWhen.limiter;

public class SkQuery extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons implements org.bukkit.event.Listener {
    public static boolean LIME_EDIT = true;

    public SkQuery() {
        super("SkQuery");
    }

    @Override
    public void Load() {
        id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.DynamicEnumTypes.register();
        addon.setLanguageFileDirectory("Lang");
        id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Registration.enableSnooper();
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(new id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Menus.FormattedSlotManager(), this);
        //new Documentation(this);
        System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6SkQuery §bAddon");
    }

    @org.bukkit.event.EventHandler
    public void onDisable(id.universenetwork.utilities.Bukkit.Events.UNUtilitiesDisableEvent e) {
        id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SQL.ScriptCredentials.clear();
        java.util.Set<id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaCondition> l = limiter;
        if (l == null || l.isEmpty()) return;
        limiter.clear();
    }
}