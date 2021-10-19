package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.Manager.Color.sendTranslate;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getRegistry;
import static java.util.Collections.emptyList;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.getLocationInfo;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.hasBlockInfo;
import static org.bukkit.FluidCollisionMode.NEVER;
import static org.bukkit.Material.AIR;

public class Commands extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    final boolean enabled;

    public Commands(boolean enabled) {
        super("infinityexpansion", "unutilities.command.infinityexpansion", true);
        this.enabled = enabled;
    }

    @Override
    public void Execute(CommandSender sender, Command command, String[] args) {
        if (enabled) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("giverecipe")) {
                    if (args.length != 2) {
                        sender.sendMessage("Usage: /ie giverecipe <ID>");
                        return;
                    }
                    SlimefunItem sfItem = SlimefunItem.getById(args[1].toUpperCase());
                    if (sfItem == null || sfItem instanceof MultiBlockMachine || sfItem.getRecipeType() == RecipeType.GEO_MINER) {
                        sender.sendMessage(ChatColor.RED + "Invalid Slimefun item!");
                        return;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Gave recipe for " + sfItem.getItemName());
                    Player p = (Player) sender;
                    List<ItemStack> recipe = new ArrayList<>();
                    for (ItemStack e : sfItem.getRecipe()) if (e != null) recipe.add(e);
                    p.getInventory().addItem(recipe.toArray(new ItemStack[0]));
                } else if (args[0].equalsIgnoreCase("printitem")) {
                    Player p = (Player) sender;
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == AIR) {
                        p.sendMessage(ChatColor.RED + "You must be holding an item!");
                        return;
                    }
                    p.sendMessage(item.toString());
                }
            }
        } else sendTranslate(sender, getConfig().getString("disabledMessage"));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, Command command, String str, String[] args) {
        if (enabled) {
            Block target = ((Player) sender).getTargetBlockExact(8, NEVER);
            List<String> arg = new ArrayList<>();
            if (args.length == 1) {
                arg.add("giverecipe");
                arg.add("printitem");
                arg.add("setdata");
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("giverecipe"))
                    for (SlimefunItem item : getRegistry().getEnabledSlimefunItems()) arg.add(item.getId());
                else if (args[1].equalsIgnoreCase("setdata")) {
                    if (target != null || target.getType() != AIR) if (hasBlockInfo(target)) {
                        arg.addAll(getLocationInfo(target.getLocation()).getKeys());
                        arg.remove("id");
                    }
                }
            } else if (args.length == 3 && !args[2].equals("id")) {
                if (target != null || target.getType() != AIR) {
                    String current = getLocationInfo(target.getLocation(), args[2]);
                    if (current != null) {
                        arg.add(current);
                        arg.add("\\remove");
                        return arg;
                    }
                }
            }
        }
        return emptyList();
    }
}