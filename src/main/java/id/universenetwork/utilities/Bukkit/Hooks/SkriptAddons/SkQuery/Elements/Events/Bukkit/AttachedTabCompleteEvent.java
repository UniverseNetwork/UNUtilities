package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.List;

public class AttachedTabCompleteEvent extends Event implements Cancellable {
    static final HandlerList handlers = new HandlerList();
    final CommandSender sender;
    final Command command;
    final String[] args;
    final List<String> result = new ArrayList<>();
    boolean isCancelled = false;

    public AttachedTabCompleteEvent(CommandSender sender, Command command, String[] args) {
        this.sender = sender;
        this.command = command;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Command getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public List<String> getResult() {
        return result;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}