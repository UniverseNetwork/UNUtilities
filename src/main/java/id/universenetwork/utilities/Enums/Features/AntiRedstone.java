package id.universenetwork.utilities.Enums.Features;

public enum AntiRedstone {

 // Anti Redstone Variable Settings
    ENABLED("Features.AntiRedstone.enabled"),
    NOPERMISSION("Features.AntiRedstone.denyMessage"),

    REDSTONE("Features.AntiRedstone.redstone"),
    REPEATER("Features.AntiRedstone.repeater"),
    COMPARATOR("Features.AntiRedstone.comparator"),
    REDSTONE_TORCH("Features.AntiRedstone.redstone_torch.land"),
    WALL_REDSTONE_TORCH("Features.AntiRedstone.redstone_torch.wall"),
    OBSERVER("Features.AntiRedstone.observer"),
    REDSTONE_BLOCK("Features.AntiRedstone.redstone_block"),
    REDSTONE_LAMP("Features.AntiRedstone.redstone_lamp");

    private String configPath;

    AntiRedstone(String configPath){
        this.configPath = configPath;
    }

    public String getConfigPath(){
        return this.configPath;
    }
}
