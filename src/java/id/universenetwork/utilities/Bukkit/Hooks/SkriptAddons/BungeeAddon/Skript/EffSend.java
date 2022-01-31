package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon.Skript;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon.BungeeAddon.CHANNEL;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class EffSend extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon.AsyncEffect {
    static {
        ch.njol.skript.Skript.registerEffect(EffSend.class, "(send|move) %players% to [server] %string%");
    }

    Expression<Player> players;
    Expression<String> server;

    public EffSend() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, CHANNEL);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        server = (Expression<String>) exprs[1];
        return true;
    }

    @Override
    protected void execute(org.bukkit.event.Event e) {
        String server = this.server.getSingle(e);
        if (server != null) for (Player player : players.getArray(e)) {
            com.google.common.io.ByteArrayDataOutput out = com.google.common.io.ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(player.getName());
            out.writeUTF(server);
            player.sendPluginMessage(plugin, CHANNEL, out.toByteArray());
        }
    }
}