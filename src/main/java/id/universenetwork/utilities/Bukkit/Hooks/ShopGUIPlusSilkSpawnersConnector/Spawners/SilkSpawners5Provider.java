package id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Spawners;

import de.dustplanet.silkspawners.SilkSpawners;
import de.dustplanet.silkspawners.compat.api.NMSProvider;
import de.dustplanet.util.SilkUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SilkSpawners5Provider implements SilkSpawnersProvider {
    private SilkUtil silkUtil;

    @Override
    public void hookIntoSilkSpawners(Plugin silkSpawnersPlugin) {
        silkUtil = new SilkUtil((SilkSpawners) silkSpawnersPlugin);
    }

    @Override
    public String getName() {
        return "SilkSpawners";
    }

    @Override
    public ItemStack getSpawnerItem(EntityType entityType) {
        ItemStack itemStack = null;

        try {
            Method method = SilkUtil.class.getMethod("newSpawnerItem", Short.TYPE, String.class, Integer.TYPE, Boolean.TYPE);
            itemStack = (ItemStack) method.invoke(silkUtil, entityType.getTypeId(), null, 1, false);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException v4) {
            v4.printStackTrace();
        }

        return itemStack;
    }

    @Override
    public EntityType getSpawnerEntityType(ItemStack itemStack) {
        EntityType entityType = null;

        try {
            Method method = NMSProvider.class.getMethod("getSilkSpawnersNBTEntityID", ItemStack.class);
            entityType = EntityType.fromId((Short) method.invoke(silkUtil.nmsProvider, itemStack));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException v4) {
            v4.printStackTrace();
        }

        return entityType;
    }
}
