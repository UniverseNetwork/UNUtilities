package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Hurt Sound of NPC")
@Description({"Sets/gets the hurt sound of the specified NPC", "NOTE: Can only be used if the NPC's type is not a player. Additionally, this value will return <none> if it has not been set."})
@Examples({"set hurtsound of npc last spawned npc to \"entity.bat.death\"", "broadcast \"%hurtsound of npc last spawned npc%\""})
@Since("1.0")
@RequiredPlugins("Citizens")
public class ExprNPCHurtSound extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprNPCHurtSound.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "hurtsound of npc [with] [the] [id] %integers%");
    }

    Expression<Integer> ID;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        ID = (Expression<Integer>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Hurt sound of npc expression with expression integer: " + ID.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (ID.getSingle(event) != null)
            return new String[]{CitizensAPI.getNPCRegistry().getById(ID.getSingle(event)).data().get(NPC.HURT_SOUND_METADATA)};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        if (ID != null) for (Integer ids : ID.getAll(event)) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(ids);
            if (mode == Changer.ChangeMode.SET) {
                String sound = id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Utils.ScrubNPCSound.getSound((String) delta[0]);
                npc.data().setPersistent(NPC.HURT_SOUND_METADATA, sound);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return ch.njol.util.coll.CollectionUtils.array(String.class);
        return null;
    }
}