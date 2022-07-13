package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptVotifierHook;

import java.util.function.Function;

public class GoGetter<O, I> extends ch.njol.skript.util.Getter<O, I> {
    final Function<I, O> getter;

    public GoGetter(Function<I, O> getter) {
        this.getter = getter;
    }

    @Override
    public O get(I input) {
        return getter.apply(input);
    }
}