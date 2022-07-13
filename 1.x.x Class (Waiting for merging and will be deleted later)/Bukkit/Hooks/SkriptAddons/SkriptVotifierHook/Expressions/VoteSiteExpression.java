package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptVotifierHook.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.vexsoftware.votifier.model.Vote;

public class VoteSiteExpression extends SimplePropertyExpression<Vote, String> {
    public static final String PATTERN = "([web]site|server[ ]list|service) [name]";

    static {
        SimplePropertyExpression.register(VoteSiteExpression.class, String.class, PATTERN, "vote");
    }

    @Override
    public String convert(Vote vote) {
        return (vote == null) ? null : vote.getServiceName();
    }

    @Override
    protected String getPropertyName() {
        return "website";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}