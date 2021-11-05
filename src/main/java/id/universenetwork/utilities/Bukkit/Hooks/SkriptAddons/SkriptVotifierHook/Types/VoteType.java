package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook.Types;

import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import com.vexsoftware.votifier.model.Vote;

public class VoteType {
    static {
        ch.njol.skript.registrations.Classes.registerClass(new ch.njol.skript.classes.ClassInfo<>(Vote.class, "vote").user("vote").name("Vote").description("A vote from votifier.").since("1.0.0").defaultExpression(new ch.njol.skript.expressions.base.EventValueExpression<>(Vote.class)).parser(new Parser<>() {
            @Override
            public Vote parse(String s, ParseContext context) {
                return null;
            }

            @Override
            public boolean canParse(ParseContext context) {
                return false;
            }

            @Override
            public String toString(Vote vote, int i) {
                return vote.toString();
            }

            @Override
            public String toVariableNameString(Vote vote) {
                return toString(vote, 0);
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        }));
    }
}