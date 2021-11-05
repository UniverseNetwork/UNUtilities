package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkWhitelist;

import ch.njol.skript.Skript;

public class SkWhitelist {
    public SkWhitelist() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled("SkWhitelist")) {
            Skript.registerEffect(EffReloadWhitelist.class, "reload whitelist");
            Skript.registerExpression(SExprWhitelist.class, org.bukkit.OfflinePlayer.class, ch.njol.skript.lang.ExpressionType.PROPERTY, "whitelist");
            Skript.registerCondition(CondServerWhitelist.class, "server is whitelisted", "server is(n'| no)t whitelisted");
            Skript.registerCondition(CondPlayerWhitelist.class, "%player% is whitelisted", "%player% is(n'| no)t whitelisted");
            System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6SkWhitelist §bAddon");
        }
    }
}