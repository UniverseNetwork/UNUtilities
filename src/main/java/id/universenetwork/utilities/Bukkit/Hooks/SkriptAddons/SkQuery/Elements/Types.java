package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaCondition;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaEffect;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.Markup;

import static ch.njol.skript.Skript.error;
import static ch.njol.skript.log.ErrorQuality.SEMANTIC_ERROR;

public class Types extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.AbstractTask {
    @Override
    public void run() {
        Classes.registerClass(new ClassInfo<>(Markup.class, "markup").parser(new Parser<Markup>() {
            @Override
            public Markup parse(String s, ParseContext parseContext) {
                if (s.charAt(0) == '`' && s.charAt(s.length() - 1) == '`')
                    return new Markup(s.substring(1, s.length() - 1));
                else return null;
            }

            @Override
            public boolean canParse(ParseContext context) {
                return true;
            }

            @Override
            public String toString(Markup markup, int i) {
                return markup.toString();
            }

            @Override
            public String toVariableNameString(Markup markup) {
                return markup.toString();
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        }).serializer(new ch.njol.skript.classes.Serializer<Markup>() {
            @Override
            public Fields serialize(Markup markup) {
                Fields f = new Fields();
                f.putObject("src", markup.toString());
                return f;
            }

            @Override
            public void deserialize(Markup markup, Fields fieldContexts) {
                assert false;
            }

            @Override
            protected Markup deserialize(Fields fields) throws java.io.StreamCorruptedException {
                return new Markup((String) fields.getObject("src"));
            }

            @Override
            public boolean mustSyncDeserialization() {
                return true;
            }

            @Override
            public boolean canBeInstantiated(Class<? extends Markup> aClass) {
                return false;
            }

            @Override
            protected boolean canBeInstantiated() {
                return false;
            }
        }));
        Classes.registerClass(new ClassInfo<>(LambdaCondition.class, "predicate").parser(new Parser<LambdaCondition>() {
            @Override
            public LambdaCondition parse(String s, ParseContext parseContext) {
                if (s.length() > 2 && s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']') {
                    Condition e = Condition.parse(s.substring(1, s.length() - 1), null);
                    if (e == null) error(s + " is not a valid lambda statement.", SEMANTIC_ERROR);
                    else return new LambdaCondition(e);
                }
                return null;
            }

            @Override
            public boolean canParse(ParseContext context) {
                return true;
            }

            @Override
            public String toString(LambdaCondition lambdaCondition, int i) {
                return lambdaCondition.toString();
            }

            @Override
            public String toVariableNameString(LambdaCondition lambdaCondition) {
                return lambdaCondition.toString();
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        }));
        Classes.registerClass(new ClassInfo<>(LambdaEffect.class, "lambda").parser(new Parser<LambdaEffect>() {
            @Override
            public LambdaEffect parse(String s, ParseContext parseContext) {
                if (s.length() > 3 && s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']') {
                    if ("void".equals(s.substring(1, s.length() - 1))) return new LambdaEffect(true);
                    Effect e = Effect.parse(s.substring(1, s.length() - 1), null);
                    if (e == null) error(s + " is not a valid lambda statement.", SEMANTIC_ERROR);
                    else return new LambdaEffect(e);
                }
                return null;
            }

            @Override
            public boolean canParse(ParseContext context) {
                return true;
            }

            @Override
            public String toString(LambdaEffect lambdaEffect, int i) {
                return lambdaEffect.toString();
            }

            @Override
            public String toVariableNameString(LambdaEffect lambdaEffect) {
                return lambdaEffect.toString();
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        }));
    }
}