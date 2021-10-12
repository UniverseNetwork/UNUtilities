package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("[the] [buffered[ ]]image stored as %string%")
public class ExprImageLocal extends SimpleExpression<BufferedImage> {
    Expression<String> url;

    @Override
    protected BufferedImage[] get(Event event) {
        String u = url.getSingle(event);
        if (u == null) return null;
        try {
            return asArray(ImageIO.read(new File(Skript.getInstance().getDataFolder().getAbsolutePath() + File.separator + Skript.SCRIPTSFOLDER + File.separator + u + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends BufferedImage> getReturnType() {
        return BufferedImage.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "image from url";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        url = (Expression<String>) expressions[0];
        return true;
    }
}