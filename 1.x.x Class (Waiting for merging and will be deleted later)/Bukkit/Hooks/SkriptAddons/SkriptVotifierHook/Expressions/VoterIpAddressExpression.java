package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.vexsoftware.votifier.model.Vote;

public class VoterIpAddressExpression extends SimplePropertyExpression<Vote, String> {
    public static final String PATTERN = "[(voter|sender)] [ip(-| )]address";

    static {
        SimplePropertyExpression.register(VoterIpAddressExpression.class, String.class, PATTERN, "vote");
    }

    @Override
    public String convert(Vote vote) {
        return vote.getAddress();
    }

    @Override
    protected String getPropertyName() {
        return "ip address";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}