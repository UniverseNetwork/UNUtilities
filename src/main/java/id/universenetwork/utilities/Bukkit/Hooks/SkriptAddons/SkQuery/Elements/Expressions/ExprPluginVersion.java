package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getPluginManager;

@UsePropertyPatterns
@PropertyFrom("string")
@PropertyTo("version")
public class ExprPluginVersion extends SimplePropertyExpression<String, String> {
    @Override
    protected String getPropertyName() {
        return "plugin version";
    }

    @Override
    public String convert(String s) {
        Plugin p = getPluginManager().getPlugin(s);
        return p == null ? null : p.getDescription().getVersion();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}