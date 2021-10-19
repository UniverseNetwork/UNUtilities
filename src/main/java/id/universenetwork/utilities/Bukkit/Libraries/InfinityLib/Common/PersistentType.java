package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public final class PersistentType<T, Z> implements PersistentDataType<T, Z> {
    public static final PersistentDataType<byte[], ItemStack> ITEM_STACK = new PersistentType<>(byte[].class, ItemStack.class, itemStack -> {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (BukkitObjectOutputStream output = new BukkitObjectOutputStream(bytes)) {
            output.writeObject(itemStack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes.toByteArray();
    }, arr -> {
        ByteArrayInputStream bytes = new ByteArrayInputStream(arr);
        try (BukkitObjectInputStream input = new BukkitObjectInputStream(bytes)) {
            return (ItemStack) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomItemStack(Material.STONE, "&cERROR");
        }
    });

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static final PersistentDataType<byte[], List<ItemStack>> ITEM_STACK_LIST = new PersistentType<byte[], List<ItemStack>>(byte[].class, (Class) List.class, list -> {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (BukkitObjectOutputStream output = new BukkitObjectOutputStream(bytes)) {
            for (ItemStack item : list) output.writeObject(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes.toByteArray();
    }, arr -> {
        ByteArrayInputStream bytes = new ByteArrayInputStream(arr);
        List<ItemStack> list = new ArrayList<>();
        try (BukkitObjectInputStream input = new BukkitObjectInputStream(bytes)) {
            while (bytes.available() > 0) list.add((ItemStack) input.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    });

    public static final PersistentDataType<byte[], Location> LOCATION = new PersistentType<>(byte[].class, Location.class, location -> {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (BukkitObjectOutputStream output = new BukkitObjectOutputStream(bytes)) {
            output.writeObject(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes.toByteArray();
    }, arr -> {
        ByteArrayInputStream bytes = new ByteArrayInputStream(arr);
        try (BukkitObjectInputStream input = new BukkitObjectInputStream(bytes)) {
            return (Location) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new Location(null, 0, 0, 0);
        }
    });

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static final PersistentDataType<byte[], List<String>> STRING_LIST = new PersistentType<byte[], List<String>>(byte[].class, (Class) List.class, list -> {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(list.size() * 20);
        for (String string : list) {
            byte[] arr = string.getBytes();
            bytes.write(arr.length);
            bytes.write(arr, 0, arr.length);
        }
        return bytes.toByteArray();
    }, arr -> {
        ByteArrayInputStream bytes = new ByteArrayInputStream(arr);
        List<String> list = new ArrayList<>(arr.length / 20);
        try {
            while (bytes.available() > 0) {
                byte[] string = new byte[bytes.read()];
                bytes.read(string, 0, string.length);
                list.add(new String(string));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    });

    @Deprecated
    public static final PersistentDataType<String, ItemStack> ITEM_STACK_OLD = new PersistentType<>(String.class, ItemStack.class, itemStack -> {
        YamlConfiguration config = new YamlConfiguration();
        config.set("item", itemStack);
        return config.saveToString();
    }, string -> {
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.loadFromString(string);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            return new CustomItemStack(Material.STONE, "&cERROR");
        }
        ItemStack item = config.getItemStack("item");
        return item != null ? item : new CustomItemStack(Material.STONE, "&cERROR");
    });

    final Class<T> primitive;
    final Class<Z> complex;
    final Function<Z, T> toPrimitive;
    final Function<T, Z> toComplex;

    @NotNull
    @Override
    public Class<T> getPrimitiveType() {
        return primitive;
    }

    @NotNull
    @Override
    public Class<Z> getComplexType() {
        return complex;
    }

    @NotNull
    @Override
    public T toPrimitive(Z complex, PersistentDataAdapterContext context) {
        return toPrimitive.apply(complex);
    }

    @NotNull
    @Override
    public Z fromPrimitive(T primitive, PersistentDataAdapterContext context) {
        return toComplex.apply(primitive);
    }
}