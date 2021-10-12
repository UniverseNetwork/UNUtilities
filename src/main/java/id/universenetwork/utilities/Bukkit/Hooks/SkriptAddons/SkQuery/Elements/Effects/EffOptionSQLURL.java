package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.OptionsPragma;

import java.io.File;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SQL.ScriptCredentials.setURL;

@Name("SQL URL Option")
@Description("Sets the script-wide database connection url. Required for an SQL connection.")
@Examples("script options:;->$ init com.mysql.jdbc.Driver;->$ db url jdbc:mysql://localhost:3306/skript;->$ db username admin;->$ db password heil_putin")
@Patterns("$ db url <.+>")
public class EffOptionSQLURL extends OptionsPragma {
    @Override
    protected void register(File executingScript, ParseResult parseResult) {
        setURL(executingScript, parseResult.regexes.get(0).group());
    }
}