package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("[the] [buffered[ ]]image from [url] %string%")
public class ExprImageURL extends SimpleExpression<BufferedImage> {
    Expression<String> url;

    @Override
    protected BufferedImage[] get(Event event) {
        String u = url.getSingle(event);
        if (u == null) return null;
        try {
            return asArray(ImageIO.read(new URL(u)));
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