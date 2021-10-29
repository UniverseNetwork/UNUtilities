package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum PillagersLimiter {
    // Pillagers Limitter Variable Settings
    ENABLED("Features.PillagersLimiter.enabled"),
    STOPPER_ENABLED("Features.PillagersLimiter.Stopper.Enabled"),
    STOPPER_USE_HARD_LIMIT("Features.PillagersLimiter.Stopper.Use-Hard-Limit"),
    STOPPER_HARD_LIMIT_AMOUNT("Features.PillagersLimiter.Stopper.Hard-Limit-Amount"),
    STOPPER_IGNORE_LEADERS("Features.PillagersLimiter.Stopper.Ignore-Leaders"),
    STOPPER_IGNORE_NAMED("Features.PillagersLimiter.Stopper.Ignore-Named"),
    STOPPER_IGNORE_RAIDERS("Features.PillagersLimiter.Stopper.Ignore-Raiders"),
    LIMITER_ENABLED("Features.PillagersLimiter.Limiter.Enabled"),
    LIMITER_RADIUS_X("Features.PillagersLimiter.Limiter.Radius-X"),
    LIMITER_RADIUS_Y("Features.PillagersLimiter.Limiter.Radius-Y"),
    LIMITER_RADIUS_Z("Features.PillagersLimiter.Limiter.Radius-Z"),
    LIMITER_STOP_AT_AMOUNT("Features.PillagersLimiter.Limiter.Stop-At-Amount"),
    LIMITER_IGNORE_LEADERS("Features.PillagersLimiter.Limiter.Ignore-Leaders"),
    LIMITER_IGNORE_NAMED("Features.PillagersLimiter.Limiter.Ignore-Named"),
    LIMITER_IGNORE_RAIDERS("Features.PillagersLimiter.Limiter.Ignore-Raiders"),
    REMOVER_ENABLED("Features.PillagersLimiter.Remover.Enabled"),
    REMOVER_IGNORE_LEADERS("Features.PillagersLimiter.Remover.Ignore-Leaders"),
    REMOVER_IGNORE_NAMED("Features.PillagersLimiter.Remover.Ignore-Named"),
    PATROL_REMOVER_ENABLED("Features.PillagersLimiter.Patrol-Remover.Enabled"),
    PATROL_ONLY_REMOVE_TARGET("Features.PillagersLimiter.Patrol-Remover.Only-Remove-Target"),
    MESSAGES_USAGE("Features.PillagersLimiter.Messages.Usage"),
    MESSAGES_COUNT("Features.PillagersLimiter.Messages.Count"),
    MESSAGES_REMOVE("Features.PillagersLimiter.Messages.Remove"),
    MESSAGES_DISABLED("Features.PillagersLimiter.Messages.Usage");
    final String configPath;

    PillagersLimiter(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}