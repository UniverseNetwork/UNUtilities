package id.universenetwork.utilities.Bukkit.Utils;

import id.universenetwork.utilities.Universal.Enums.Font;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static id.universenetwork.utilities.Bukkit.UNUtilities.translateColor;

@lombok.experimental.UtilityClass
public class Text {
    final static int CENTER_PX = 154;

    /**
     * Send a message to {@link CommandSender} with prefix and tranlated color codes
     */
    public static void send(CommandSender Sender, String Text) {
        Sender.sendMessage(translateColor(Text));
    }

    /**
     * Send a message to {@link Player} with prefix and tranlated color codes
     */
    public static void send(Player Player, String Text) {
        Player.sendMessage(translateColor(Text));
    }

    /**
     * Send a center-aligned message to {@link Player} with a translated prefix and color codes
     */
    public static void sendCentered(Player Player, String Text) {
        if (Text == null || Text.equals("")) {
            Player.sendMessage("");
            return;
        }
        Text = translateColor(Text);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
        for (char c : Text.toCharArray())
            if (c == '§') previousCode = true;
            else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                Font FI = Font.getFontInfo(c);
                messagePxSize += isBold ? FI.getBoldLength() : FI.getLength();
                messagePxSize++;
            }
        int CENTER_PX = 154;
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = Font.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        Player.sendMessage(sb + Text);
    }

    /**
     * Send a center-aligned message to {@link CommandSender} with a translated prefix and color codes
     */
    public static void sendCentered(org.bukkit.command.CommandSender Sender, String Text) {
        sendCentered((Player) Sender, Text);
    }
}