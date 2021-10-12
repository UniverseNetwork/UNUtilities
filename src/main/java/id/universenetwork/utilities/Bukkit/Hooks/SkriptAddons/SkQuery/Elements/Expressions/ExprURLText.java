package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

@Patterns("text from [url] %string%")
public class ExprURLText extends SimplePropertyExpression<String, String> {
    @Override
    protected String getPropertyName() {
        return "URL";
    }

    @SuppressWarnings("resource")
    @Override
    public String convert(String s) {
        try {
            URL url = new URL(s);
            Scanner a = new Scanner(url.openStream());
            StringBuilder str = new StringBuilder();
            boolean first = true;
            while (a.hasNext()) {
                if (first) str = new StringBuilder(a.next());
                else str.append(" ").append(a.next());
                first = false;
            }
            return str.toString();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}