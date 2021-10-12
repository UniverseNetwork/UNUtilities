package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.lang.SkriptParser.ParseResult;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.OptionsPragma;

import java.io.File;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SQL.ScriptCredentials.currentPool;

@Patterns("$ pool <.+>")
public class EffOptionSQLPool extends OptionsPragma {
    @Override
    protected void register(File executingScript, ParseResult parseResult) {
        currentPool = parseResult.regexes.get(0).group();
    }
}