package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Types;

import ch.njol.skript.lang.ParseContext;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class TypTeam {
    static {
        ch.njol.skript.registrations.Classes.registerClass(new ch.njol.skript.classes.ClassInfo<>(Team.class, "team").user("teams?").name("Team").description("Vanilla team").parser(new ch.njol.skript.classes.Parser<Team>() {
            @Override
            public Team parse(@NotNull String s, @NotNull ParseContext pc) {
                return org.bukkit.Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s);
            }

            @Override
            public boolean canParse(@NotNull ParseContext context) {
                return true;
            }

            @Override
            public @NotNull String toString(Team team, int flags) {
                return "" + team.getName();
            }

            @Override
            public @NotNull String toVariableNameString(Team team) {
                return "" + team.getName();
            }
        }));
    }
}