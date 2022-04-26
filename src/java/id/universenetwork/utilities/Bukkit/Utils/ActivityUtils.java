package id.universenetwork.utilities.Bukkit.Utils;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Sets;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static org.bukkit.Bukkit.getServer;

public class ActivityUtils {
    static final String NAME = getServer().getClass().getPackage().getName();
    static final String VERSION = NAME.substring(NAME.lastIndexOf('.') + 1);
    static final Set<String> IGNORE_JOB_SITE_VERSIONS = Sets.newHashSet("v1_16_R1", "v1_16_R2", "v1_16_R3");
    static final List<String> remappedVersion = new ArrayList<>();

    static Method VILLAGER_GET_HANDLE_METHOD;
    static Method VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD;
    static Method BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD;
    static Method CURRENT_ACTIVITY_METHOD;
    static Method SET_SCHEDULE_METHOD;

    static Field ACTIVITIES_FIELD;

    static Object ACTIVITY_CORE;
    static Object ACTIVITY_IDLE;
    static Object ACTIVITY_WORK;
    static Object ACTIVITY_MEET;
    static Object ACTIVITY_REST;
    static Object SCHEDULE_EMPTY;
    static Object SCHEDULE_SIMPLE;
    static Object SCHEDULE_VILLAGER_DEFAULT;
    static Object SCHEDULE_VILLAGER_BABY;

    static {
        try {
            remappedVersion.add("v1_17_R1");
            if (remappedVersion.contains(VERSION)) {
                VILLAGER_GET_HANDLE_METHOD = Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity.CraftVillager").getMethod("getHandle");
                // net.minecraft.world.entity.EntityLiving.getBehaviorController
                VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD = Class.forName("net.minecraft.world.entity.EntityLiving").getMethod("getBehaviorController");
                //net.minecraft.world.entity.ai.BehaviorController
                BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD = Class.forName("net.minecraft.world.entity.ai.BehaviorController").getMethod("getSchedule");
                // net.minecraft.world.entity.schedule.Schedule
                CURRENT_ACTIVITY_METHOD = Class.forName("net.minecraft.world.entity.schedule.Schedule").getMethod("a", int.class);

                // net.minecraft.world.entity.ai.BehaviorController
                Map<String, String> activitiesFieldNameMap = new HashMap<>();
                activitiesFieldNameMap.put("v1_17_R1", "j");

                SET_SCHEDULE_METHOD = Class.forName("net.minecraft.world.entity.ai.BehaviorController").getMethod("setSchedule", Class.forName("net.minecraft.world.entity.schedule.Schedule"));
                ACTIVITIES_FIELD = Class.forName("net.minecraft.world.entity.ai.BehaviorController").getDeclaredField(activitiesFieldNameMap.get(VERSION));
                ACTIVITIES_FIELD.setAccessible(true);

                // net.minecraft.world.entity.schedule.Activity
                ACTIVITY_CORE = Class.forName("net.minecraft.world.entity.schedule.Activity").getField("a").get(null);
                ACTIVITY_IDLE = Class.forName("net.minecraft.world.entity.schedule.Activity").getField("b").get(null);
                ACTIVITY_WORK = Class.forName("net.minecraft.world.entity.schedule.Activity").getField("c").get(null);
                ACTIVITY_REST = Class.forName("net.minecraft.world.entity.schedule.Activity").getField("e").get(null);
                ACTIVITY_MEET = Class.forName("net.minecraft.world.entity.schedule.Activity").getField("f").get(null);

                // net.minecraft.world.entity.schedule.Schedule
                SCHEDULE_EMPTY = Class.forName("net.minecraft.world.entity.schedule.Schedule").getField("c").get(null);
                SCHEDULE_SIMPLE = Class.forName("net.minecraft.world.entity.schedule.Schedule").getField("d").get(null);
                SCHEDULE_VILLAGER_DEFAULT = Class.forName("net.minecraft.world.entity.schedule.Schedule").getField("f").get(null);
                SCHEDULE_VILLAGER_BABY = Class.forName("net.minecraft.world.entity.schedule.Schedule").getField("e").get(null);
            } else {
                VILLAGER_GET_HANDLE_METHOD = Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity.CraftVillager").getMethod("getHandle");
                VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD = Class.forName("net.minecraft.server." + VERSION + ".EntityLiving").getMethod("getBehaviorController");
                // 1.17.1 goes to net.minecraft.world.entity.ai.BehaviorController
                BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD = Class.forName("net.minecraft.server." + VERSION + ".BehaviorController").getMethod("getSchedule");
                // 1.17.1 goes to net.minecraft.world.entity.schedule.Schedule
                CURRENT_ACTIVITY_METHOD = Class.forName("net.minecraft.server." + VERSION + ".Schedule").getMethod("a", int.class);
                // 1.17.1 goes to net.minecraft.world.entity.ai.BehaviorController
                SET_SCHEDULE_METHOD = Class.forName("net.minecraft.server." + VERSION + ".BehaviorController").getMethod("setSchedule", Class.forName("net.minecraft.server." + VERSION + ".Schedule"));

                Map<String, String> activitiesFieldNameMap = new HashMap<>();
                activitiesFieldNameMap.put("v1_14_R1", "g");
                activitiesFieldNameMap.put("v1_15_R1", "g");
                activitiesFieldNameMap.put("v1_16_R1", "j");
                activitiesFieldNameMap.put("v1_16_R2", "j");
                activitiesFieldNameMap.put("v1_16_R3", "j");

                ACTIVITIES_FIELD = Class.forName("net.minecraft.server." + VERSION + ".BehaviorController").getDeclaredField(activitiesFieldNameMap.get(VERSION));
                ACTIVITIES_FIELD.setAccessible(true);

                // 1.17.1 goes to net.minecraft.world.entity.schedule.Activity
                ACTIVITY_CORE = Class.forName("net.minecraft.server." + VERSION + ".Activity").getField("CORE").get(null);
                ACTIVITY_IDLE = Class.forName("net.minecraft.server." + VERSION + ".Activity").getField("IDLE").get(null);
                ACTIVITY_WORK = Class.forName("net.minecraft.server." + VERSION + ".Activity").getField("WORK").get(null);
                ACTIVITY_MEET = Class.forName("net.minecraft.server." + VERSION + ".Activity").getField("MEET").get(null);
                ACTIVITY_REST = Class.forName("net.minecraft.server." + VERSION + ".Activity").getField("REST").get(null);
                // 1.17.1 goes to net.minecraft.world.entity.schedule.Schedule
                SCHEDULE_EMPTY = Class.forName("net.minecraft.server." + VERSION + ".Schedule").getField("EMPTY").get(null);
                SCHEDULE_SIMPLE = Class.forName("net.minecraft.server." + VERSION + ".Schedule").getField("SIMPLE").get(null);
                SCHEDULE_VILLAGER_DEFAULT = Class.forName("net.minecraft.server." + VERSION + ".Schedule").getField("VILLAGER_DEFAULT").get(null);
                SCHEDULE_VILLAGER_BABY = Class.forName("net.minecraft.server." + VERSION + ".Schedule").getField("VILLAGER_BABY").get(null);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setActivitiesNormal(Villager villager) {
        try {
            if (remappedVersion.contains(VERSION)) {
                ImmutableCollection origin = (ImmutableCollection) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)));
                Set<Object> mutable = (Set) origin.stream().collect(Collectors.toSet());
                mutable.clear();
                mutable.add(ACTIVITY_CORE);
                Object currentSchedule = BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)));
                Object currentActivity;
                if (currentSchedule == null) currentActivity = ACTIVITY_IDLE;
                else
                    currentActivity = CURRENT_ACTIVITY_METHOD.invoke(currentSchedule, (int) villager.getWorld().getTime());
                mutable.add(currentActivity);
            } else {
                ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).clear();
                ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).add(ACTIVITY_CORE);
                Object currentSchedule = BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)));
                Object currentActivity;
                if (currentSchedule == null) currentActivity = ACTIVITY_IDLE;
                else
                    currentActivity = CURRENT_ACTIVITY_METHOD.invoke(currentSchedule, (int) villager.getWorld().getTime());
                ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).add(currentActivity);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setActivitiesEmpty(Villager villager) {
        try {
            ImmutableCollection origin = (ImmutableCollection) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)));
            Set<Object> mutable = (Set) origin.stream().collect(Collectors.toSet());
            mutable.clear();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setScheduleNormal(Villager villager) {
        try {
            SET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)), villager.isAdult() ? (villager.getProfession().equals(Villager.Profession.NITWIT) ? SCHEDULE_SIMPLE : SCHEDULE_VILLAGER_DEFAULT) : SCHEDULE_VILLAGER_BABY);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setScheduleEmpty(Villager villager) {
        try {
            SET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)), SCHEDULE_EMPTY);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static boolean badCurrentActivity(Villager villager) {
        try {
            Object currentSchedule = BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)));
            if (currentSchedule == null) return false;
            Object currentActivity = CURRENT_ACTIVITY_METHOD.invoke(currentSchedule, (int) villager.getWorld().getTime());
            return badActivity(currentActivity, villager);
        } catch (IllegalAccessException | InvocationTargetException ignore) {
        }
        return false;
    }

    public static boolean wouldBeBadActivity(Villager villager) {
        Object wouldBeSchedule = villager.isAdult() ? (villager.getProfession().equals(Villager.Profession.NITWIT) ? SCHEDULE_VILLAGER_DEFAULT : SCHEDULE_SIMPLE) : SCHEDULE_VILLAGER_BABY;
        try {
            Object currentActivity = CURRENT_ACTIVITY_METHOD.invoke(wouldBeSchedule, (int) villager.getWorld().getTime());
            return badActivity(currentActivity, villager);
        } catch (IllegalAccessException | InvocationTargetException ignore) {
        }
        return false;
    }

    static boolean badActivity(Object activity, Villager villager) {
        if (activity.equals(ACTIVITY_REST))
            return villager.getMemory(MemoryKey.HOME) == null || isPlaceholderMemory(villager, MemoryKey.HOME);
        if (activity.equals(ACTIVITY_WORK))
            return !IGNORE_JOB_SITE_VERSIONS.contains(VERSION) && (villager.getMemory(MemoryKey.JOB_SITE) == null || isPlaceholderMemory(villager, MemoryKey.JOB_SITE));
        if (activity.equals(ACTIVITY_MEET))
            return villager.getMemory(MemoryKey.MEETING_POINT) == null || isPlaceholderMemory(villager, MemoryKey.MEETING_POINT);
        return false;
    }

    public static void replaceBadMemories(Villager villager) {
        try {
            if (villager.getMemory(MemoryKey.HOME) == null)
                villager.setMemory(MemoryKey.HOME, new Location(villager.getWorld(), villager.getLocation().getBlockX(), -10000, villager.getLocation().getBlockZ()));
            if (villager.getMemory(MemoryKey.JOB_SITE) == null && !IGNORE_JOB_SITE_VERSIONS.contains(VERSION))
                villager.setMemory(MemoryKey.JOB_SITE, new Location(villager.getWorld(), villager.getLocation().getBlockX(), -10000, villager.getLocation().getBlockZ()));
            if (villager.getMemory(MemoryKey.MEETING_POINT) == null)
                villager.setMemory(MemoryKey.MEETING_POINT, new Location(villager.getWorld(), villager.getLocation().getBlockX(), -10000, villager.getLocation().getBlockZ()));
        } catch (NullPointerException ignore) {
        }
    }

    public static boolean isPlaceholderMemory(Villager villager, MemoryKey<Location> memoryKey) {
        Location memoryLocation = villager.getMemory(memoryKey);
        return memoryLocation != null && memoryLocation.getY() < 0;
    }

    public static void clearPlaceholderMemories(Villager villager) {
        try {
            if (villager.getMemory(MemoryKey.HOME) != null && isPlaceholderMemory(villager, MemoryKey.HOME))
                villager.setMemory(MemoryKey.HOME, null);
            if (villager.getMemory(MemoryKey.JOB_SITE) != null && isPlaceholderMemory(villager, MemoryKey.JOB_SITE))
                villager.setMemory(MemoryKey.JOB_SITE, null);
            if (villager.getMemory(MemoryKey.MEETING_POINT) != null && isPlaceholderMemory(villager, MemoryKey.MEETING_POINT))
                villager.setMemory(MemoryKey.MEETING_POINT, null);
        } catch (NullPointerException ignore) {
        }
    }

    public static boolean isScheduleNormal(Villager villager) {
        try {
            return BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager))).equals((villager.isAdult() ? (villager.getProfession().equals(Villager.Profession.NITWIT) ? SCHEDULE_SIMPLE : SCHEDULE_VILLAGER_DEFAULT) : SCHEDULE_VILLAGER_BABY));
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }
}