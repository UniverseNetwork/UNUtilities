package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Types;

import ch.njol.skript.lang.ParseContext;
import org.bukkit.scoreboard.Team;

public class TypTeam {
    static {
        ch.njol.skript.registrations.Classes.registerClass(new ch.njol.skript.classes.ClassInfo<>(Team.class, "team").user("teams?").name("Team").description("Vanilla team").parser(new ch.njol.skript.classes.Parser<Team>() {
            @Override
            public Team parse(String s, ParseContext pc) {
                return org.bukkit.Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s);
            }

            @Override
            public boolean canParse(ParseContext context) {
                return true;
            }

            @Override
            public String toString(Team team, int flags) {
                return "" + team.getName();
            }

            @Override
            public String toVariableNameString(Team team) {
                return "" + team.getName();
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        }));
    }
}