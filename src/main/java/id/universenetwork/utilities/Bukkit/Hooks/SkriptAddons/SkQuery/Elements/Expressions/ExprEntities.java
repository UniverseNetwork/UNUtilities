package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;

@UsePropertyPatterns
@PropertyFrom("chunks")
@PropertyTo("entities")
public class ExprEntities extends SimpleExpression<Entity> {
    Expression<Chunk> chunk;

    @Override
    protected Entity[] get(Event event) {
        ArrayList<Entity> entities = new ArrayList<>();
        for (Chunk c : chunk.getAll(event)) entities.addAll(Arrays.asList(c.getEntities()));
        return entities.toArray(new Entity[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "tile entities";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        chunk = (Expression<Chunk>) expressions[0];
        return true;
    }
}