package id.universenetwork.utilities.Bukkit.Utils;

import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActivityUtils {
    static final String NAME = Bukkit.getServer().getClass().getPackage().getName();
    static final String VERSION = NAME.substring(NAME.lastIndexOf('.') + 1);
    static final Set<String> IGNORE_JOB_SITE_VERSIONS = Sets.newHashSet("v1_16_R1", "v1_16_R2", "v1_16_R3", "v1_17_R1");
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
    static String EntityLiving_1_17 = "net.minecraft.world.entity.EntityLiving";
    static String BehaviorController_1_17 = "net.minecraft.world.entity.ai.BehaviorController";
    static String Activity_1_17 = "net.minecraft.world.entity.schedule.Activity";
    static String Schedule_1_17 = "net.minecraft.world.entity.schedule.Schedule";
    static String EntityLiving_OLD = "net.minecraft.server." + VERSION + ".EntityLiving";
    static String BehaviorController_OLD = "net.minecraft.server." + VERSION + ".BehaviorController";
    static String Activity_OLD = "net.minecraft.server." + VERSION + ".Activity";
    static String Schedule_OLD = "net.minecraft.server." + VERSION + ".Schedule";

    static {
        try {
            VILLAGER_GET_HANDLE_METHOD = Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity.CraftVillager").getMethod("getHandle");
            Map<String, String> activitiesFieldNameMap = new HashMap<>();
            activitiesFieldNameMap.put("v1_14_R1", "g");
            activitiesFieldNameMap.put("v1_15_R1", "g");
            activitiesFieldNameMap.put("v1_16_R1", "j");
            activitiesFieldNameMap.put("v1_16_R2", "j");
            activitiesFieldNameMap.put("v1_16_R3", "j");
            activitiesFieldNameMap.put("v1_17_R1", "j");
            if (VERSION.equals("v1_17_R1")) {
                VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD = Class.forName(EntityLiving_1_17).getMethod("getBehaviorController");
                BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD = Class.forName(BehaviorController_1_17).getMethod("getSchedule");
                CURRENT_ACTIVITY_METHOD = Class.forName(Schedule_1_17).getMethod("a", int.class);
                SET_SCHEDULE_METHOD = Class.forName(BehaviorController_1_17).getMethod("setSchedule", Class.forName(Schedule_1_17));
                ACTIVITIES_FIELD = Class.forName(BehaviorController_1_17).getDeclaredField(activitiesFieldNameMap.get(VERSION));
                ACTIVITY_CORE = Class.forName(Activity_1_17).getField("CORE").get(null);
                ACTIVITY_IDLE = Class.forName(Activity_1_17).getField("IDLE").get(null);
                ACTIVITY_WORK = Class.forName(Activity_1_17).getField("WORK").get(null);
                ACTIVITY_MEET = Class.forName(Activity_1_17).getField("MEET").get(null);
                ACTIVITY_REST = Class.forName(Activity_1_17).getField("REST").get(null);
                SCHEDULE_EMPTY = Class.forName(Schedule_1_17).getField("EMPTY").get(null);
                SCHEDULE_SIMPLE = Class.forName(Schedule_1_17).getField("SIMPLE").get(null);
                SCHEDULE_VILLAGER_DEFAULT = Class.forName(Schedule_1_17).getField("VILLAGER_DEFAULT").get(null);
                SCHEDULE_VILLAGER_BABY = Class.forName(Schedule_1_17).getField("VILLAGER_BABY").get(null);
            } else {
                VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD = Class.forName(EntityLiving_OLD).getMethod("getBehaviorController");
                BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD = Class.forName(BehaviorController_OLD).getMethod("getSchedule");
                CURRENT_ACTIVITY_METHOD = Class.forName(Schedule_OLD).getMethod("a", int.class);
                SET_SCHEDULE_METHOD = Class.forName(BehaviorController_OLD).getMethod("setSchedule", Class.forName(Schedule_OLD));
                ACTIVITIES_FIELD = Class.forName(BehaviorController_OLD).getDeclaredField(activitiesFieldNameMap.get(VERSION));
                ACTIVITY_CORE = Class.forName(Activity_OLD).getField("CORE").get(null);
                ACTIVITY_IDLE = Class.forName(Activity_OLD).getField("IDLE").get(null);
                ACTIVITY_WORK = Class.forName(Activity_OLD).getField("WORK").get(null);
                ACTIVITY_MEET = Class.forName(Activity_OLD).getField("MEET").get(null);
                ACTIVITY_REST = Class.forName(Activity_OLD).getField("REST").get(null);
                SCHEDULE_EMPTY = Class.forName(Schedule_OLD).getField("EMPTY").get(null);
                SCHEDULE_SIMPLE = Class.forName(Schedule_OLD).getField("SIMPLE").get(null);
                SCHEDULE_VILLAGER_DEFAULT = Class.forName(Schedule_OLD).getField("VILLAGER_DEFAULT").get(null);
                SCHEDULE_VILLAGER_BABY = Class.forName(Schedule_OLD).getField("VILLAGER_BABY").get(null);
            }
            ACTIVITIES_FIELD.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setActivitiesNormal(Villager villager) {
        try {
            ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).clear();
            ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).add(ACTIVITY_CORE);
            Object currentSchedule = BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)));
            Object currentActivity;
            if (currentSchedule == null) currentActivity = ACTIVITY_IDLE;
            else currentActivity = CURRENT_ACTIVITY_METHOD.invoke(currentSchedule, (int) villager.getWorld().getTime());
            ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).add(currentActivity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setActivitiesEmpty(Villager villager) {
        try {
            ((Set) ACTIVITIES_FIELD.get(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)))).clear();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setScheduleNormal(Villager villager) {
        try {
            SET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager)), villager.isAdult() ? (villager.getProfession() == Villager.Profession.NITWIT ? SCHEDULE_SIMPLE : SCHEDULE_VILLAGER_DEFAULT) : SCHEDULE_VILLAGER_BABY);
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
            if (currentSchedule == null) {
                return false;
            }
            Object currentActivity = CURRENT_ACTIVITY_METHOD.invoke(currentSchedule, (int) villager.getWorld().getTime());
            return badActivity(currentActivity, villager);
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }

        return false;
    }

    public static boolean wouldBeBadActivity(Villager villager) {
        Object wouldBeSchedule = villager.isAdult() ? (villager.getProfession() == Villager.Profession.NITWIT ? SCHEDULE_VILLAGER_DEFAULT : SCHEDULE_SIMPLE) : SCHEDULE_VILLAGER_BABY;
        try {
            Object currentActivity = CURRENT_ACTIVITY_METHOD.invoke(wouldBeSchedule, (int) villager.getWorld().getTime());
            return badActivity(currentActivity, villager);
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }

        return false;
    }

    static boolean badActivity(Object activity, Villager villager) {
        if (activity == ACTIVITY_REST)
            return villager.getMemory(MemoryKey.HOME) == null || isPlaceholderMemory(villager, MemoryKey.HOME);
        if (activity == ACTIVITY_WORK)
            return !IGNORE_JOB_SITE_VERSIONS.contains(VERSION) && (villager.getMemory(MemoryKey.JOB_SITE) == null || isPlaceholderMemory(villager, MemoryKey.JOB_SITE));
        if (activity == ACTIVITY_MEET)
            return villager.getMemory(MemoryKey.MEETING_POINT) == null || isPlaceholderMemory(villager, MemoryKey.MEETING_POINT);
        return false;
    }

    public static void replaceBadMemories(Villager villager) {
        if (villager.getMemory(MemoryKey.HOME) == null)
            villager.setMemory(MemoryKey.HOME, new Location(villager.getWorld(), villager.getLocation().getBlockX(), -10000, villager.getLocation().getBlockZ()));
        if (villager.getMemory(MemoryKey.JOB_SITE) == null && !IGNORE_JOB_SITE_VERSIONS.contains(VERSION))
            villager.setMemory(MemoryKey.JOB_SITE, new Location(villager.getWorld(), villager.getLocation().getBlockX(), -10000, villager.getLocation().getBlockZ()));
        if (villager.getMemory(MemoryKey.MEETING_POINT) == null)
            villager.setMemory(MemoryKey.MEETING_POINT, new Location(villager.getWorld(), villager.getLocation().getBlockX(), -10000, villager.getLocation().getBlockZ()));
    }

    public static boolean isPlaceholderMemory(Villager villager, MemoryKey<Location> memoryKey) {
        Location memoryLocation = villager.getMemory(memoryKey);
        return memoryLocation != null && memoryLocation.getY() < 0;
    }

    public static void clearPlaceholderMemories(Villager villager) {
        if (villager.getMemory(MemoryKey.HOME) != null && isPlaceholderMemory(villager, MemoryKey.HOME))
            villager.setMemory(MemoryKey.HOME, null);
        if (villager.getMemory(MemoryKey.JOB_SITE) != null && isPlaceholderMemory(villager, MemoryKey.JOB_SITE))
            villager.setMemory(MemoryKey.JOB_SITE, null);
        if (villager.getMemory(MemoryKey.MEETING_POINT) != null && isPlaceholderMemory(villager, MemoryKey.MEETING_POINT))
            villager.setMemory(MemoryKey.MEETING_POINT, null);
    }

    public static boolean isScheduleNormal(Villager villager) {
        try {
            return BEHAVIOUR_CONTROLLER_GET_SCHEDULE_METHOD.invoke(VILLAGER_GET_BEHAVIOUR_CONTROLLER_METHOD.invoke(VILLAGER_GET_HANDLE_METHOD.invoke(villager))) == (villager.isAdult() ? (villager.getProfession() == Villager.Profession.NITWIT ? SCHEDULE_SIMPLE : SCHEDULE_VILLAGER_DEFAULT) : SCHEDULE_VILLAGER_BABY);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }
}