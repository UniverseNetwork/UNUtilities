package id.universenetwork.utilities.Bungee.Enums.Features;

public enum Discord {

    TOKEN("Features.Discord.token"),
    ESC("Features.Discord.channelid"),
    JETLT("Features.Discord.Embed.Join.title"),
    JED("Features.Discord.Embed.Join.description"),
    JEC("Features.Discord.Embed.Join.color"),
    LETLT("Features.Discord.Embed.Left.Title"),
    LED("Features.Discord.Embed.Left.description"),
    LEC("Features.Discord.Embed.Left.color");

    final String configPath;

    Discord(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
