package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Constants;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Events;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.GlowEnchant;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.McMMOEvents;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Constants.GLOW_ENCHANT;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Constants.SLIMEFUN_VERSION;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static java.lang.Integer.parseInt;
import static java.util.Collections.singletonList;
import static java.util.logging.Level.INFO;
import static org.bukkit.Bukkit.*;

public class FluffyMachines /*implements TabExecutor*/ {
    public static final HashMap<ItemStack, List<Pair<ItemStack, List<RecipeChoice>>>> shapedVanillaRecipes = new HashMap<>();
    public static final HashMap<ItemStack, List<Pair<ItemStack, List<RecipeChoice>>>> shapelessVanillaRecipes = new HashMap<>();

    public FluffyMachines() {
        if (Enabled("FluffyMachines")) {
            boolean mcMMO = getPluginManager().isPluginEnabled("McMMO");
            boolean NCP = getPluginManager().isPluginEnabled("NoCheatPlus");
            // Register Glow
            try {
                if (!Enchantment.isAcceptingRegistrations()) {
                    Field accepting = Enchantment.class.getDeclaredField("acceptingNew");
                    accepting.setAccessible(true);
                    accepting.set(null, true);
                }
            } catch (IllegalAccessException | NoSuchFieldException ignored) {
                getLogger().warning(prefix + " Failed to register enchantment.");
            }
            registerGlow();

            // Register ACT Recipes
            Iterator<Recipe> recipeIterator = recipeIterator();
            while (recipeIterator.hasNext()) {
                Recipe r = recipeIterator.next();
                if (r instanceof ShapedRecipe) {
                    ShapedRecipe sr = (ShapedRecipe) r;
                    List<RecipeChoice> rc = new ArrayList<>();
                    ItemStack key = new ItemStack(sr.getResult().getType(), 1);

                    // Convert the recipe to a list
                    for (Map.Entry<Character, RecipeChoice> choice : sr.getChoiceMap().entrySet())
                        if (choice.getValue() != null) rc.add(choice.getValue());
                    if (!shapedVanillaRecipes.containsKey(key))
                        shapedVanillaRecipes.put(key, new ArrayList<>(singletonList(new Pair<>(sr.getResult(), rc))));
                    else shapedVanillaRecipes.get(key).add(new Pair<>(sr.getResult(), rc));
                } else if (r instanceof ShapelessRecipe) {
                    ShapelessRecipe slr = (ShapelessRecipe) r;
                    ItemStack key = new ItemStack(slr.getResult().getType(), 1);

                    // Key has a list of recipe options
                    if (!shapelessVanillaRecipes.containsKey(key))
                        shapelessVanillaRecipes.put(key, new ArrayList<>(singletonList(new Pair<>(slr.getResult(), slr.getChoiceList()))));
                    else shapelessVanillaRecipes.get(key).add(new Pair<>(slr.getResult(), slr.getChoiceList()));
                }
            }

            // Register McMMO Events
            if (mcMMO) registerListeners(new McMMOEvents());

            // Get Slimefun Numerical Version
            try {
                Matcher matcher = Constants.VERSION_PATTERN.matcher(SLIMEFUN_VERSION);
                if (matcher.find()) {
                    int parsedVersion = parseInt(matcher.group(2));
                    if (parsedVersion < 844)
                        System.out.println(prefix + " §eYou are running a Slimefun version before DEV 844. FluffyMachines requires you to update your Slimefun version so that barrels remain functional. Update before 4/15/2021, or players may encounter issues with FluffyMachines that I am not accountable for.");
                    else Constants.SLIMEFUN_UPDATED = true;
                } else
                    System.out.println(prefix + " §eYou are running a RC version of Slimefun or running a custom build. FluffyMachines requires you to update your Slimefun version so that barrels remain functional. Update before 4/15/2021, or players may encounter issues with FluffyMachines that I am not accountable for");
            } catch (NumberFormatException e) {
                return;
            }

            // Registering Items
            FluffyItemSetup.setup();

            // Register Events Class
            registerListeners(new Events());
            if (mcMMO && !NCP)
                System.out.println(prefix + " §bSuccessfully Registered §dFluffyMachines §bAddon With §dmcMMO §bSupport");
            else if (!mcMMO && NCP)
                System.out.println(prefix + " §bSuccessfully Registered §dFluffyMachines §bAddon With §dNoCheatPlus §bSupport");
            else if (mcMMO && NCP)
                System.out.println(prefix + " §bSuccessfully Registered §dFluffyMachines §bAddon With §dmcMMO & NoCheatPlus §bSupport");
            else System.out.println(prefix + " §bSuccessfully Registered §dFluffyMachines §bAddon");
        }
    }

/*  @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("FluffyMachines > Gotta be longer than that");
            return true;
        }
        if (args[0].equalsIgnoreCase("replace") && sender instanceof Player) {
            Player p = ((Player) sender);
            ItemStack item = p.getInventory().getItemInMainHand();
            if (SlimefunItem.getByItem(item) != null)
                if (SlimefunItem.getByItem(item) == FluffyItems.WATERING_CAN.getItem())
                    p.getInventory().setItemInMainHand(FluffyItems.WATERING_CAN.clone());
            return true;
        } else if (args[0].equalsIgnoreCase("save") && sender.hasPermission("fluffymachines.admin")) {
            saveAllPlayers();
            return true;
        } else if (args[0].equalsIgnoreCase("meta") && sender instanceof Player) {
            Player p = (Player) sender;
            Utils.send(p, String.valueOf(p.getInventory().getItemInMainHand().getItemMeta()));
            return true;
        } else if (args[0].equalsIgnoreCase("rawmeta") && sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(String.valueOf(p.getInventory().getItemInMainHand().getItemMeta()).replace("§", "&"));
            return true;
        } else if (args[0].equalsIgnoreCase("addinfo") && sender.hasPermission("fluffymachines.admin") && sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 3)
                Utils.send(p, "&cPlease specify the key and the data");
            else {
                RayTraceResult rayResult = p.rayTraceBlocks(5d);
                if (rayResult != null && rayResult.getHitBlock() != null && BlockStorage.hasBlockInfo(rayResult.getHitBlock())) {
                    BlockStorage.addBlockInfo(rayResult.getHitBlock(), args[1], args[2]);
                    Utils.send(p, "&aInfo has been added.");
                } else Utils.send(p, "&cYou must be looking at a Slimefun block");
            }
            return true;
        }
        return false;
    }


    @Override
    @ParametersAreNonnullByDefault
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("fluffymachines.admin"))
                return StringUtil.copyPartialMatches(args[0], Arrays.asList("replace", "save", "meta", "rawmeta", "addinfo"), new ArrayList<>());
            else
                return StringUtil.copyPartialMatches(args[0], Arrays.asList("replace", "meta", "rawmeta"), new ArrayList<>());
        } else return Collections.emptyList();
    }*/

    void saveAllPlayers() {
        Iterator<PlayerProfile> iterator = PlayerProfile.iterator();
        int players = 0;
        while (iterator.hasNext()) {
            PlayerProfile profile = iterator.next();
            profile.save();
            players++;
        }
        if (players > 0) getLogger().log(INFO, "Auto-saved all player data for {0} player(s)!", players);
    }

    void registerGlow() {
        Enchantment glowEnchantment = new GlowEnchant(GLOW_ENCHANT, new String[]{"SMALL_PORTABLE_CHARGER", "MEDIUM_PORTABLE_CHARGER", "BIG_PORTABLE_CHARGER", "LARGE_PORTABLE_CHARGER", "CARBONADO_PORTABLE_CHARGER", "PAXEL"});

        // Prevent double-registration errors
        if (Enchantment.getByKey(glowEnchantment.getKey()) == null) Enchantment.registerEnchantment(glowEnchantment);
    }
}