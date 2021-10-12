package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.OptionsPragma;

import java.io.File;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.PermissionsHandler.enable;

@Name("skQueryPerms Option")
@Description("Enables skQuery as a permissions manager. You will then be able to access expressions that represent player permissions and change them with Skript.")
@Examples("script options:;->$ use permissions")
@Patterns("$ use permissions")
public class EffOptionSkriptPermissionsManager extends OptionsPragma {
    @Override
    protected void register(File executingScript, ParseResult parseResult) {
        enable();
    }
}