package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook.Events.OnlinePlayerVoteEvent;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getServer;

public class SkriptVotifierHook implements org.bukkit.event.Listener {
    public SkriptVotifierHook() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled("SkriptVotifierHook")) {
            if (getServer().getPluginManager().isPluginEnabled("Votifier")) {
                try {
                    id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptVotifierHook", "Types", "Events", "Expressions");
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(this);
                System.out.println(prefix + " §aVotifier found. §bSuccessfully Registered §6SkriptVotifierHook §bAddon");
            } else
                getServer().getLogger().warning(prefix + " §eVotifier not found. §cYou need Votifier to use §6SkriptVotifierHook §cAddon");
        }
    }

    @org.bukkit.event.EventHandler
    public void Vote(com.vexsoftware.votifier.model.VotifierEvent e) {
        com.vexsoftware.votifier.model.Vote v = e.getVote();
        org.bukkit.entity.Player p = getServer().getPlayerExact(v.getUsername());
        if (p != null) getServer().getPluginManager().callEvent(new OnlinePlayerVoteEvent(p, v));
    }
}