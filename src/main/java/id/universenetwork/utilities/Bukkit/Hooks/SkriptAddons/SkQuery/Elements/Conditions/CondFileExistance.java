package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.io.File;

@Name("File existance")
@Description("Checks whether or not a file at the defined path(s) exist.")
@Patterns({"file %string% (1¦does|2¦does(n't| not)) exist", "existance of [file] %string% is %boolean%"})
// Last syntax is to support Umbaska.
public class CondFileExistance extends Condition {
    Expression<Boolean> check;
    Expression<String> files;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        files = (Expression<String>) expressions[0];
        setNegated(parseResult.mark == 1);
        if (expressions.length > 1)
            check = (Expression<Boolean>) expressions[0];
        return true;
    }

    @Override
    public boolean check(Event event) {
        if (files == null)
            return !isNegated();
        Boolean negated = (check != null) ? check.getSingle(event) : isNegated();
        File file = new File(files.getSingle(event));
        return file.exists() == negated;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "file existance" + files != null ? files.toString(event, debug) : "";
    }
}