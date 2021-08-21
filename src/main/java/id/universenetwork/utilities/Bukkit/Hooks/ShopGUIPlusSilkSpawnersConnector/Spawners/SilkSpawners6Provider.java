package id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Spawners;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import de.dustplanet.silkspawners.SilkSpawners;
import de.dustplanet.silkspawners.compat.api.NMSProvider;
import de.dustplanet.util.SilkUtil;
import id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.NMS.Utils;
import id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.NMS.Version;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SilkSpawners6Provider implements SilkSpawnersProvider {
    private SilkUtil silkUtil;
    private BiMap<EntityType, String> entityTypesMap;

    @Override
    public void hookIntoSilkSpawners(Plugin silkSpawnersPlugin) {
        silkUtil = new SilkUtil((SilkSpawners) silkSpawnersPlugin);
        loadEntityTypes();
    }

    @Override
    public String getName() {
        return "SilkSpawners";
    }

    @Override
    public ItemStack getSpawnerItem(EntityType entityType) {
        ItemStack itemStack = null;
        String entityTypeName = entityTypesMap.containsKey(entityType) ? (String) entityTypesMap.get(entityType) : entityType.name().toLowerCase();

        try {
            Method method = SilkUtil.class.getMethod("newSpawnerItem", String.class, String.class, Integer.TYPE, Boolean.TYPE);
            itemStack = (ItemStack) method.invoke(silkUtil, entityTypeName, null, 1, false);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException v5) {
            v5.printStackTrace();
        }

        return itemStack;
    }

    @Override
    public EntityType getSpawnerEntityType(ItemStack itemStack) {
        EntityType entityType = null;

        try {
            Method method = NMSProvider.class.getMethod("getSilkSpawnersNBTEntityID", ItemStack.class);
            String entityTypeName = ((String) method.invoke(silkUtil.nmsProvider, itemStack)).toUpperCase();
            if (entityTypesMap.containsValue(entityTypeName.toLowerCase())) {
                entityType = (EntityType) entityTypesMap.inverse().get(entityTypeName.toLowerCase());
            } else {
                try {
                    entityType = EntityType.valueOf(entityTypeName);
                } catch (IllegalArgumentException v6) {
                    entityType = getEntityTypeFromStringWithoutUnderScores(entityTypeName);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException v7) {
            v7.printStackTrace();
        }

        return entityType;
    }

    private EntityType getEntityTypeFromStringWithoutUnderScores(String entityTypeName) {
        EntityType[] v2 = EntityType.values();
        int v3 = v2.length;

        for (int v4 = 0; v4 < v3; ++v4) {
            EntityType entityType = v2[v4];
            if (entityType.name().replace("_", "").equalsIgnoreCase(entityTypeName)) {
                return entityType;
            }
        }

        return null;
    }

    private void loadEntityTypes() {
        entityTypesMap = HashBiMap.create();
        if (Utils.isNmsVersionLowerThan(Version.v1_16)) {
            entityTypesMap.put(EntityType.valueOf("PIG_ZOMBIE"), "zombie_pigman");
        }

        entityTypesMap.put(EntityType.MUSHROOM_COW, "mooshroom");
        if (Utils.isNmsVersionLowerThan(Version.v1_11)) {
            entityTypesMap.put(EntityType.IRON_GOLEM, "VillagerGolem");
        } else if (Utils.isNmsVersionLowerThan(Version.v1_13)) {
            entityTypesMap.put(EntityType.IRON_GOLEM, "villager_golem");
        }

    }
}
