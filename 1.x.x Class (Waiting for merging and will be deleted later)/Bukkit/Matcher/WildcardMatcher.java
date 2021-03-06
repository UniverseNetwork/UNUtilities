package id.universenetwork.utilities.bukkit.Matcher;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WildcardMatcher {
    final List<Pattern> patterns;

    public WildcardMatcher(@NotNull final Collection<String> wildcards) {
        this.patterns = wildcards.stream().map(WildcardMatcher::convertToRegex).map(regex -> Pattern.compile(regex, Pattern.CASE_INSENSITIVE)).collect(Collectors.toList());
    }

    public boolean matches(@Nullable String value) {
        if (value == null) return false;
        for (Pattern pattern : patterns) if (pattern.matcher(value.trim()).matches()) return true;
        return false;
    }

    static String convertToRegex(String wildcardString) {
        Pattern pattern = Pattern.compile("[^*]+|(\\*)");
        Matcher matcher = pattern.matcher(wildcardString);
        StringBuilder regex = new StringBuilder();
        while (matcher.find()) {
            if (matcher.group(1) != null) matcher.appendReplacement(regex, ".*");
            else matcher.appendReplacement(regex, "\\\\Q" + matcher.group(0) + "\\\\E");
        }
        matcher.appendTail(regex);
        return regex.toString();
    }
}