package com.xxmicloxx.NoteBlockAPI.songplayer;

import com.xxmicloxx.NoteBlockAPI.model.FadeType;
import com.xxmicloxx.NoteBlockAPI.utils.Interpolator;

public class Fade {
    FadeType type;
    byte fadeStart;
    byte fadeTarget;
    int fadeDuration;
    int fadeDone = 0;

    /**
     * Create new fade effect
     *
     * @param type         Type of fade effect
     * @param fadeDuration - duration of fade effect in ticks
     */
    public Fade(FadeType type, int fadeDuration) {
        this.type = type;
        this.fadeDuration = fadeDuration;
    }

    protected byte calculateFade() {
        if (type == FadeType.LINEAR) {
            if (fadeDone == fadeDuration) return -1; // no fade today
            double targetVolume = Interpolator.interpLinear(new double[]{0, fadeStart, fadeDuration, fadeTarget}, fadeDone);
            fadeDone++;
            return (byte) targetVolume;
        }
        fadeDone++;
        return -1;
    }

    protected int getFadeDone() {
        return fadeDone;
    }

    protected void setFadeStart(byte fadeStart) {
        this.fadeStart = fadeStart;
    }

    protected void setFadeTarget(byte fadeTarget) {
        this.fadeTarget = fadeTarget;
    }

    /**
     * Returns fade effect type
     *
     * @return {@link FadeType}
     */
    public FadeType getType() {
        return type;
    }

    /**
     * Set fade effect type
     *
     * @param type FadeType
     */
    public Fade setType(FadeType type) {
        this.type = type;
        return this;
    }

    /**
     * Returns duration of fade effect
     *
     * @return duration in ticks
     */
    public int getFadeDuration() {
        return fadeDuration;
    }

    /**
     * Set fade effect duration
     *
     * @param fadeDuration duration in ticks
     */
    public Fade setFadeDuration(int fadeDuration) {
        this.fadeDuration = fadeDuration;
        return this;
    }

    protected byte getFadeStart() {
        return fadeStart;
    }

    protected byte getFadeTarget() {
        return fadeTarget;
    }

    protected void setFadeDone(int fadeDone) {
        this.fadeDone = fadeDone;
    }

    public boolean isDone() {
        return fadeDone >= fadeDuration;
    }
}