package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.BungeeAddon.Skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import com.google.common.io.ByteStreams;
import id.universenetwork.utilities.bukkit.UNUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.Field;

import static id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.BungeeAddon.BungeeAddon.CHANNEL;
import static org.bukkit.Bukkit.*;

public class EffGrabServers extends ch.njol.skript.lang.Effect implements org.bukkit.plugin.messaging.PluginMessageListener {
    static final Field DELAYED;
    public static String[] servers;

    static {
        Skript.registerEffect(EffGrabServers.class, "grab all [bungee[ ]cord] servers");
        Field _DELAYED = null;
        try {
            _DELAYED = ch.njol.skript.effects.Delay.class.getDeclaredField("delayed");
            _DELAYED.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Skript.warning("Skript's delayed field couldn't be found. " + "You may not get errors related to delays when using this effect.");
        }
        DELAYED = _DELAYED;
    }

    Event event;

    public EffGrabServers() {
        org.bukkit.plugin.messaging.Messenger messenger = getServer().getMessenger();
        messenger.registerIncomingPluginChannel(UNUtilities.plugin, CHANNEL, this);
        messenger.registerOutgoingPluginChannel(UNUtilities.plugin, CHANNEL);
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        ch.njol.skript.lang.parser.ParserInstance.get().setHasDelayBefore(Kleenean.TRUE);
        return true;
    }

    @Override
    protected void execute(Event e) {
        getScheduler().runTaskAsynchronously(UNUtilities.plugin, () -> {
            java.util.Optional<? extends Player> player = getOnlinePlayers().stream().findAny();
            if (!player.isPresent()) {
                getLogger().warning(UNUtilities.prefix + " §eTried to grab all bungeecord servers, but no players were online.");
                return;
            }
            event = e;
            com.google.common.io.ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("GetServers");
            player.get().sendPluginMessage(UNUtilities.plugin, CHANNEL, out.toByteArray());
        });
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "grab all bungeecord servers";
    }

    @Override
    protected TriggerItem walk(Event e) {
        debug(e, true);
        try {
            ((java.util.Set<Event>) DELAYED.get(null)).add(e);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        execute(e);
        return null;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        com.google.common.io.ByteArrayDataInput in = ByteStreams.newDataInput(message);
        if ("GetServers".equals(in.readUTF())) {
            servers = in.readUTF().split(", ");
            getScheduler().runTask(UNUtilities.plugin, () -> TriggerItem.walk(getNext(), event));
        }
    }
}