package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage.StorageUnit;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getRegistry;
import static org.bukkit.FluidCollisionMode.NEVER;
import static org.bukkit.Material.AIR;

public class Commands extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    public Commands() {
        super("infinityexpansion", "Do /infinityexpansion help for a list of commands", "unutilities.command.infinityexpansion", true, "ie", "ix", "infinity");
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("giverecipe")) {
                if (args.length != 2) {
                    sender.sendMessage("Usage: /ie giverecipe <ID>");
                    return;
                }
                SlimefunItem sfItem = SlimefunItem.getById(args[1].toUpperCase());
                if (sfItem == null || sfItem instanceof io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine || sfItem.getRecipeType().equals(io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.GEO_MINER)) {
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
                if (item.getType().equals(AIR)) {
                    p.sendMessage(ChatColor.RED + "You must be holding an item!");
                    return;
                }
                p.sendMessage(item.toString());
            } else if (args[0].equalsIgnoreCase("setdata")) {
                if (args.length != 4) {
                    sender.sendMessage(ChatColor.RED + "You must specify a key and value to set!");
                    return;
                }
                Player p = (Player) sender;
                Block target = p.getTargetBlockExact(8, NEVER);
                if (target == null || target.getType() == Material.AIR) {
                    p.sendMessage(ChatColor.RED + "You need to target a block to use this command!");
                    return;
                }
                String id = BlockStorage.getLocationInfo(target.getLocation(), "id");
                if (id == null) {
                    p.sendMessage(ChatColor.RED + "You need to target a slimefun block to use this command!");
                    return;
                }
                if (args[3].equals("id")) {
                    p.sendMessage(ChatColor.RED + "You cannot change the id of this block, it could cause internal issues!");
                    return;
                }
                if (args[4].equals("\\remove")) {
                    p.sendMessage(ChatColor.GREEN + "Successfully removed value of key '" + args[3] + "' in " + id);
                    BlockStorage.addBlockInfo(target, args[3], null);
                } else {
                    p.sendMessage(ChatColor.GREEN + "Successfully set key '" + args[0] + "' to value '" + args[3] + "' in " + id);
                    BlockStorage.addBlockInfo(target, args[3], args[4]);
                }
                SlimefunItem unit = SlimefunItem.getById(id);
                if (unit instanceof StorageUnit) ((StorageUnit) unit).reloadCache(target);
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        List<String> arg = new ArrayList<>();
        Block target = ((Player) sender).getTargetBlockExact(8, NEVER);
        if (args.length == 1) {
            arg.add("giverecipe");
            arg.add("printitem");
            arg.add("setdata");
        } else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("giverecipe"))
                for (SlimefunItem item : getRegistry().getEnabledSlimefunItems()) arg.add(item.getId());
            else if (args[1].equalsIgnoreCase("setdata")) {
                if (target != null || !target.getType().equals(AIR)) if (BlockStorage.hasBlockInfo(target)) {
                    arg.addAll(BlockStorage.getLocationInfo(target.getLocation()).getKeys());
                    arg.remove("id");
                }
            }
        } else if (args.length == 3 && !args[2].equals("id")) {
            if (target != null || !target.getType().equals(AIR)) {
                String current = BlockStorage.getLocationInfo(target.getLocation(), args[2]);
                if (current != null) {
                    arg.add(current);
                    arg.add("\\remove");
                }
            }
        }
        return arg;
    }
}