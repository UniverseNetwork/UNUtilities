package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public final class CoolDowns {
    final Map<UUID, Long> map = new HashMap<>();
    final long cd;

    public boolean check(UUID uuid) {
        return System.currentTimeMillis() - map.getOrDefault(uuid, 0L) >= cd;
    }

    public void reset(UUID uuid) {
        map.put(uuid, System.currentTimeMillis());
    }

    public boolean checkAndReset(UUID uuid) {
        boolean check = check(uuid);
        if (check) reset(uuid);
        return check;
    }
}