package id.universenetwork.utilities.Bungee.API.Chat;

import id.universenetwork.utilities.Universal.API.Enums.FontEnum;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import static id.universenetwork.utilities.Bungee.Utils.Color.Translator;

public class CenteredText {
    final static int CENTER_PX = 154;

    public static void sendCentered(CommandSender Sender, String Text) {
        if (Text == null || Text.equals("")) {
            Sender.sendMessage("");
            return;
        }
        Text = Translator(Text);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
        for (char c : Text.toCharArray()) {
            if (c == '§') previousCode = true;
            else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                FontEnum dFI = FontEnum.getFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
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
        Sender.sendMessage(sb + Text);
    }

    public static void sendCentered(ProxiedPlayer Player, String Text) {
        sendCentered((CommandSender) Player, Text);
    }

    public static String makeCentered(String Text) {
        String[] lines = Translator(Text).split("\n", 40);
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