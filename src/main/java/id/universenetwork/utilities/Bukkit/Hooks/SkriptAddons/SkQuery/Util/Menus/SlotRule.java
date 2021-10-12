package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Menus;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaEffect;
import org.bukkit.event.Event;

import static org.bukkit.Bukkit.dispatchCommand;
import static org.bukkit.Bukkit.getConsoleSender;

public class SlotRule {
    final Object callback;
    final boolean close;

    public SlotRule(Object callback, boolean close) {
        this.callback = callback;
        this.close = close;
    }

    public boolean willClose() {
        return close;
    }

    public Object getCallback() {
        return callback;
    }

    public void run(Event event) {
        if (callback == null) return;
        if (callback instanceof String) dispatchCommand(getConsoleSender(), (String) callback);
        else if (callback instanceof LambdaEffect) ((LambdaEffect) callback).walk(event);
    }

    @Override
    public SlotRule clone() {
        return new SlotRule(callback, close);
    }
}