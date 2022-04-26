package id.universenetwork.utilities.Bukkit.API.Chat;

import id.universenetwork.utilities.Universal.API.Enums.FontEnum;
import org.bukkit.entity.Player;

import static id.universenetwork.utilities.Bukkit.Utils.Color.Translate;

public class CenteredText {
    final static int CENTER_PX = 154;

    public static void sendCentered(Player Player, String Text) {
        if (Text == null || Text.equals("")) {
            Player.sendMessage("");
            return;
        }
        Text = Translate(Text);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
        for (char c : Text.toCharArray()) {
            if (c == '§') previousCode = true;
            else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                FontEnum FI = FontEnum.getFontInfo(c);
                messagePxSize += isBold ? FI.getBoldLength() : FI.getLength();
                messagePxSize++;
            }
        }
        int CENTER_PX = 154;
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = FontEnum.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        Player.sendMessage(sb + Text);
    }

    public static void sendCentered(org.bukkit.command.CommandSender Sender, String Text) {
        sendCentered((Player) Sender, Text);
    }

    public static String makeCentered(String Text) {
        String[] lines = Translate(Text).split("\n", 40);
        StringBuilder returnMessage = new StringBuilder();
        for (String line : lines) {
            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;
            for (char c : line.toCharArray()) {
                if (c == '§') previousCode = true;
                else if (previousCode) {
                    previousCode = false;
                    isBold = c == 'l';
                } else {
                    FontEnum FI = FontEnum.getFontInfo(c);
                    messagePxSize = isBold ? messagePxSize + FI.getBoldLength() : messagePxSize + FI.getLength();
                    messagePxSize++;
                }
            }
            int toCompensate = CENTER_PX - messagePxSize / 2;
            int spaceLength = FontEnum.SPACE.getLength() + 1;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while (compensated < toCompensate) {
                sb.append(" ");
                compensated += spaceLength;
            }
            returnMessage.append(sb).append(line).append("\n");
        }
        return returnMessage.toString();
    }
}