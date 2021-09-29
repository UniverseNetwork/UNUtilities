package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SFMobDrops;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class Drop {
    final EntityType dropsFrom;
    final String slimefunId;
    final double chance;
    final String entityName;
    final String entityNbtTag;
    final int amount;

    public Drop(@Nonnull EntityType dropsFrom, @Nonnull String slimefunId, double chance, @Nullable String entityName, @Nullable String entityNbtTag, int amount) {
        this.dropsFrom = dropsFrom;
        this.slimefunId = slimefunId;
        this.chance = chance;
        this.entityName = entityName;
        this.entityNbtTag = entityNbtTag;
        this.amount = amount;
    }

    @Nonnull
    public EntityType getDropsFrom() {
        return dropsFrom;
    }

    @Nonnull
    public String getSlimefunId() {
        return slimefunId;
    }

    public double getChance() {
        return chance;
    }

    @Nullable
    public String getEntityName() {
        return entityName;
    }

    @Nullable
    public String getEntityNbtTag() {
        return entityNbtTag;
    }

    public int getAmount() {
        return amount;
    }

    @Nullable
    public SlimefunItem getSlimefunItem() {
        return SlimefunItem.getById(getSlimefunId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dropsFrom, slimefunId, chance, entityName, entityNbtTag, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Drop)) return false;
        final Drop drop = (Drop) o;
        return this.dropsFrom == drop.dropsFrom && this.slimefunId.equals(drop.slimefunId) && this.chance == drop.chance && Objects.equals(this.entityName, drop.entityName) && Objects.equals(this.entityNbtTag, drop.entityNbtTag) && this.amount == drop.amount;
    }
}