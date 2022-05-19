package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkWhitelist;

import static ch.njol.skript.Skript.*;

@id.universenetwork.utilities.Bukkit.Annotations.AddonName("SkWhitelist")
public class SkWhitelist extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons {
    @Override
    public void Load() {
        registerEffect(EffReloadWhitelist.class, "reload whitelist");
        registerExpression(SExprWhitelist.class, org.bukkit.OfflinePlayer.class, ch.njol.skript.lang.ExpressionType.PROPERTY, "whitelist");
        registerCondition(CondServerWhitelist.class, "server is whitelisted", "server is(n'| no)t whitelisted");
        registerCondition(CondPlayerWhitelist.class, "%player% is whitelisted", "%player% is(n'| no)t whitelisted");
        System.out.println(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §bSuccessfully Registered §6SkWhitelist §bAddon");
    }
}