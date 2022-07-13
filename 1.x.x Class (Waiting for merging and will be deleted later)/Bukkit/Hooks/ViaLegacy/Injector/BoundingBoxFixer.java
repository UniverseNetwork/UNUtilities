package id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Injector;

import java.lang.reflect.Field;
import java.util.Arrays;

import static id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Reflection.ReflectionAPI.getFieldAccessible;
import static id.universenetwork.utilities.bukkit.UNUtilities.prefix;
import static java.util.logging.Level.SEVERE;
import static org.bukkit.Bukkit.getLogger;

public class BoundingBoxFixer {
    public static void fixLilyPad() {
        try {
            Class<?> blockWaterLilyClass = NMSReflection.getNMSBlock("BlockWaterLily");
            Field boundingBoxField = getFieldAccessible(blockWaterLilyClass, "a");
            Object boundingBox = boundingBoxField.get(null);
            setBoundingBox(boundingBox, 0.0625, 0.0, 0.0625, 0.9375, 0.015625, 0.9375);
        } catch (Exception e) {
            getLogger().log(SEVERE, prefix + " §cCould not fix lily pad bounding box.", e);
        }
    }

    public static void fixLadder() {
        try {
            Class<?> blockLadderClass = NMSReflection.getNMSBlock("BlockLadder");
            Field boundingBoxNorthField, boundingBoxSouthField, boundingBoxWestField, boundingBoxEastField;
            int serverProtocol = com.viaversion.viaversion.api.Via.getAPI().getServerVersion().lowestSupportedVersion();
            if (serverProtocol <= 340) {
                boundingBoxEastField = getFieldAccessible(blockLadderClass, "b");
                boundingBoxWestField = getFieldAccessible(blockLadderClass, "c");
                boundingBoxSouthField = getFieldAccessible(blockLadderClass, "d");
                boundingBoxNorthField = getFieldAccessible(blockLadderClass, "e");
            } else if (serverProtocol <= 404) {
                boundingBoxEastField = getFieldAccessible(blockLadderClass, "c");
                boundingBoxWestField = getFieldAccessible(blockLadderClass, "o");
                boundingBoxSouthField = getFieldAccessible(blockLadderClass, "p");
                boundingBoxNorthField = getFieldAccessible(blockLadderClass, "q");
            } else if (serverProtocol <= 754) {
                boundingBoxEastField = getFieldAccessible(blockLadderClass, "c");
                boundingBoxWestField = getFieldAccessible(blockLadderClass, "d");
                boundingBoxSouthField = getFieldAccessible(blockLadderClass, "e");
                boundingBoxNorthField = getFieldAccessible(blockLadderClass, "f");
            } else {
                boundingBoxEastField = getFieldAccessible(blockLadderClass, "d");
                boundingBoxWestField = getFieldAccessible(blockLadderClass, "e");
                boundingBoxSouthField = getFieldAccessible(blockLadderClass, "f");
                boundingBoxNorthField = getFieldAccessible(blockLadderClass, "g");
            }
            setBoundingBox(boundingBoxEastField.get(null), 0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
            setBoundingBox(boundingBoxWestField.get(null), 0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            setBoundingBox(boundingBoxSouthField.get(null), 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
            setBoundingBox(boundingBoxNorthField.get(null), 0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
        } catch (Exception e) {
            getLogger().log(SEVERE, prefix + " §cCould not fix ladder bounding box.", e);
        }
    }

    static void setBoundingBox(Object boundingBox, double... values) throws ReflectiveOperationException {
        if (boundingBox.getClass().getSimpleName().equals("VoxelShapeArray")) {
            setVoxelShapeArray(boundingBox, values);
            return;
        }
        if (boundingBox.getClass().getSimpleName().equals("AxisAlignedBB")) {
            setAxisAlignedBB(boundingBox, values);
            return;
        }
        // Tuinity support
        if (boundingBox.getClass().getSimpleName().equals("AABBVoxelShape")) {
            setAABBVoxelShape(boundingBox, values);
            return;
        }
        throw new IllegalStateException("Unknown bounding box type: " + boundingBox.getClass().getName());
    }

    static void setAABBVoxelShape(Object boundingBox, double[] values) throws ReflectiveOperationException {
        for (Field field : boundingBox.getClass().getFields()) {
            // Set data for internally used AxisAlignedBB
            if (field.getType().getSimpleName().equals("AxisAlignedBB")) setBoundingBox(field.get(boundingBox), values);
            // Clear the cache
            if (field.getType().getSimpleName().equals("DoubleList")) {
                Object doubleList = field.get(boundingBox);
                doubleList.getClass().getMethod("clear").invoke(doubleList);
            }
        }
    }

    static void setAxisAlignedBB(Object boundingBox, double[] values) throws ReflectiveOperationException {
        Field[] doubleFields = Arrays.stream(boundingBox.getClass().getDeclaredFields()).filter(f -> f.getType() == double.class && !java.lang.reflect.Modifier.isStatic(f.getModifiers())).toArray(Field[]::new);
        if (doubleFields.length < 6)
            throw new IllegalStateException("Invalid field count for " + boundingBox.getClass().getName() + ": " + doubleFields.length);
        for (int i = 0; i < 6; i++) {
            Field currentField = doubleFields[i];
            currentField.setAccessible(true);
            currentField.setDouble(boundingBox, values[i]);
        }
    }

    static void setVoxelShapeArray(Object voxelShapeArray, double[] values) throws ReflectiveOperationException {
        Field[] doubleListFields = Arrays.stream(voxelShapeArray.getClass().getDeclaredFields()).filter(f -> f.getType().getSimpleName().equals("DoubleList")).toArray(Field[]::new);
        if (doubleListFields.length < 3)
            throw new IllegalStateException("Invalid field count for " + voxelShapeArray.getClass().getName() + ": " + doubleListFields.length);
        // fastutil is relocated on Spigot but not on Paper
        String doubleArrayListClass = doubleListFields[0].getType().getName().replace("DoubleList", "DoubleArrayList");
        java.lang.reflect.Method wrapMethod = Class.forName(doubleArrayListClass).getMethod("wrap", double[].class);
        for (int i = 0; i < 3; i++) {
            double[] array = {values[i], values[i + 3]};
            Field field = doubleListFields[i];
            field.setAccessible(true);
            field.set(voxelShapeArray, wrapMethod.invoke(null, array));
        }
    }
}