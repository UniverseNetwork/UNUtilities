package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.OptionsPragma;

import java.io.File;

@Name("Preinit Driver Option")
@Description("Allows you to initialize an SQL driver before accessing it. Not required for an SQL connection.")
@Examples("script options:;->$ init com.mysql.jdbc.Driver;->$ db url jdbc:mysql://localhost:3306/skript;->$ db username admin;->$ db password heil_putin")
@Patterns("$ init <.+>")
public class EffOptionSQLInit extends OptionsPragma {
    @Override
    protected void register(File executingScript, ParseResult parseResult) {
        try {
            Class.forName(parseResult.regexes.get(0).group());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}