package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage.Storage;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage.PrivateStorage;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage.SlimefunChest;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage.ChestProtectionLevel.PUBLIC;
import static id.universenetwork.utilities.Bukkit.UNUtilities.createKey;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static org.bukkit.Material.*;

public class PublicChests {
    public PublicChests(PrivateStorage addon, io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup) {
        SlimefunItemStack chestOak = new SlimefunItemStack("OAK_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThjMTk4OGUzM2RjZGZlZmZkNjE5YjkyYWI5ZjQ3Y2Y1YzNjNmQ3ZGRhZDQyNjM3ZDNlYWFhYjI3NTcifX19", "&6Oak chest", getLore(2, false));
        SlimefunItemStack chestBirch = new SlimefunItemStack("BIRCH_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjdmN2NiMmQ0ZWMwZTBjNjFlNzlhMDZjZjA0YjBkMTYxMDVmNzdkYTk2OTEzYTY4OWE0ZGM5NTI3N2I5MzczYiJ9fX0=", "&6Birch chest", getLore(2, false));
        SlimefunItemStack chestSpruce = new SlimefunItemStack("SPRUCE_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVmNWIwYTQ1MzA1MTE1N2JkNjRiY2Q4YzcyMGQwNmZlNzhlYmM0ODU1M2M0YzBlNWI5OTMxMjY1YjFhZTc1YyJ9fX0=", "&6Spruce chest", getLore(2, false));
        SlimefunItemStack chestJungle = new SlimefunItemStack("JUNGLE_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjEzMjIxMmE2ZTYwMzMwNzg5NmU3YTQzNTY1OTcyNjU3MDQwMTM2ZjZhODRhYjFhODgyOWVmMDA2MTIzOWNjNSJ9fX0=", "&6Jungle chest", getLore(2, false));
        SlimefunItemStack chestAcacia = new SlimefunItemStack("ACACIA_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY2OGNkZDhiNDQ3OGM5OWVlNjM0NTA2YThjZjIyNzkyMTRkYjY2ZjUwNWRkOWFmMjU5YWVlN2ZlYTFlZGYwZSJ9fX0=", "&6Acacia chest", getLore(2, false));
        SlimefunItemStack chestDarkOak = new SlimefunItemStack("DARK_OAK_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmMzOGM5YjJmZDExYzliMDIwODEzYTg3MTBhNjdmYmVhYzU2YjYxZTkyMzVmNWQ3ZDg5ZWQ5YjdhMTU5ZDQ0NSJ9fX0=", "&6Dark Oak chest", getLore(2, false));
        SlimefunItemStack chestIron = new SlimefunItemStack("IRON_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThlNTU0NGFmN2Y1NDg5Y2MyNzQ5MWNhNjhmYTkyMzg0YjhlYTVjZjIwYjVjODE5OGFkYjdiZmQxMmJjMmJjMiJ9fX0=", "&6Iron chest", getLore(3, false));
        SlimefunItemStack chestGold = new SlimefunItemStack("GOLDEN_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDUyY2VlZDA2NDgzNWVhMjMyZTY1NmE3M2Y4MmVkNzYxODI3ODU5YzkxMzQ0OTMxNGI4ZmQyMWIzZDExZDYifX19", "&6Golden chest", getLore(4, false));
        SlimefunItemStack chestDiamond = new SlimefunItemStack("DIAMOND_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FkYmNmYjI4ODIxNWE4ZDE1M2RkZmRkYjM2YmQyZWQ3YTM3YWRkMzU2NjJmODYzM2Y3MTFkMmRmY2ViNDE3YyJ9fX0=", "&6Diamond chest", getLore(5, false));
        SlimefunItemStack chestEmerald = new SlimefunItemStack("EMERALD_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmFkMzVlYmRiMTI1ZmJhNDIxMjk4ZDQyYzIwMmM3N2M3NWI0MmNjOTljOTQ5MzlmNjM3NjQwYWMxODFmIn19fQ==", "&6Emerald chest", getLore(6, false));
        SlimefunItemStack chestObsidian = new SlimefunItemStack("OBSIDIAN_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODU1YmU4NzM2NTJjYmZkZjRkODhmYTgxMjc2ZDI0OGQyYjdlZWY3YTZkNGYzZWRjYzkyZmU1NzU4NWJmNGQifX19", "&6Obsidian chest", getLore(4, true));
        SlimefunItemStack chestSteel = new SlimefunItemStack("STEEL_CHEST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjljYjNkMTlkYjUyOWEyMTVjZjYyNjk3NTkxY2MxM2ZiOGM3ODZhOGYyN2I3NTI4YzMyYWMyOTg2Yjk2NzBjNCJ9fX0=", "&6Steel chest", getLore(5, true));
        new SlimefunChest(PUBLIC, 18, true, itemGroup, chestOak, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(OAK_LOG), new ItemStack(IRON_NUGGET), new ItemStack(OAK_LOG), new ItemStack(CHEST), new ItemStack(OAK_LOG), new ItemStack(IRON_NUGGET), new ItemStack(OAK_LOG), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 18, true, itemGroup, chestBirch, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(BIRCH_LOG), new ItemStack(IRON_NUGGET), new ItemStack(BIRCH_LOG), new ItemStack(CHEST), new ItemStack(BIRCH_LOG), new ItemStack(IRON_NUGGET), new ItemStack(BIRCH_LOG), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 18, true, itemGroup, chestSpruce, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(SPRUCE_LOG), new ItemStack(IRON_NUGGET), new ItemStack(SPRUCE_LOG), new ItemStack(CHEST), new ItemStack(SPRUCE_LOG), new ItemStack(IRON_NUGGET), new ItemStack(SPRUCE_LOG), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 18, true, itemGroup, chestJungle, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(JUNGLE_LOG), new ItemStack(IRON_NUGGET), new ItemStack(JUNGLE_LOG), new ItemStack(CHEST), new ItemStack(JUNGLE_LOG), new ItemStack(IRON_NUGGET), new ItemStack(JUNGLE_LOG), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 18, true, itemGroup, chestAcacia, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(ACACIA_LOG), new ItemStack(IRON_NUGGET), new ItemStack(ACACIA_LOG), new ItemStack(CHEST), new ItemStack(ACACIA_LOG), new ItemStack(IRON_NUGGET), new ItemStack(ACACIA_LOG), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 18, true, itemGroup, chestDarkOak, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(DARK_OAK_LOG), new ItemStack(IRON_NUGGET), new ItemStack(DARK_OAK_LOG), new ItemStack(CHEST), new ItemStack(DARK_OAK_LOG), new ItemStack(IRON_NUGGET), new ItemStack(DARK_OAK_LOG), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 27, true, itemGroup, chestIron, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(IRON_INGOT), new ItemStack(IRON_NUGGET), new ItemStack(IRON_INGOT), new ItemStack(CHEST), new ItemStack(IRON_INGOT), new ItemStack(IRON_NUGGET), new ItemStack(IRON_INGOT), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 36, true, itemGroup, chestGold, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), SlimefunItems.GOLD_8K, new ItemStack(IRON_NUGGET), SlimefunItems.GOLD_8K, chestIron, SlimefunItems.GOLD_8K, new ItemStack(IRON_NUGGET), SlimefunItems.GOLD_8K, new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 45, true, itemGroup, chestDiamond, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(GLASS), new ItemStack(IRON_NUGGET), new ItemStack(DIAMOND), chestGold, new ItemStack(DIAMOND), new ItemStack(IRON_NUGGET), new ItemStack(GLASS), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 54, true, itemGroup, chestEmerald, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(GLASS), new ItemStack(IRON_NUGGET), new ItemStack(EMERALD), chestDiamond, new ItemStack(EMERALD), new ItemStack(IRON_NUGGET), new ItemStack(GLASS), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 36, false, itemGroup, chestObsidian, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), new ItemStack(OBSIDIAN), new ItemStack(IRON_NUGGET), new ItemStack(OBSIDIAN), chestIron, new ItemStack(OBSIDIAN), new ItemStack(IRON_NUGGET), new ItemStack(OBSIDIAN), new ItemStack(IRON_NUGGET)}).register(addon);
        new SlimefunChest(PUBLIC, 45, false, itemGroup, chestSteel, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(IRON_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(IRON_NUGGET), SlimefunItems.STEEL_INGOT, chestObsidian, SlimefunItems.STEEL_INGOT, new ItemStack(IRON_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(IRON_NUGGET)}).register(addon);
        new Research(createKey("wooden_chests"), 606, "Top Tier Storage", 20).addItems(chestOak, chestBirch, chestSpruce, chestJungle, chestAcacia, chestDarkOak).register();
        new Research(createKey("metal_chests"), 608, "Improved Storage", 16).addItems(chestIron, chestGold, chestDiamond, chestEmerald).register();
        new Research(createKey("hardened_chests"), 607, "Hardened Storage", 24).addItems(chestObsidian, chestSteel).register();
    }

    String[] getLore(int size, boolean explosions) {
        if (explosions)
            return new String[]{"&7Size: &e" + size + "x9", "&bWithstands Explosions", "", "&rThis Chest can be opened by anyone", "&rwho can open Chests in your Area"};
        else
            return new String[]{"&7Size: &e" + size + "x9", "", "&rThis Chest can be opened by anyone", "&rwho can open Chests in your Area"};
    }
}