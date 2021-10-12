package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util;

import java.io.Serializable;
import java.util.Objects;

public class BiValue<T1, T2> implements Serializable {
    static final long serialVersionUID = 0L;
    T1 first;
    T2 second;

    protected BiValue() {
    }

    public BiValue(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "" + first + " & " + second + "";
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiValue biValue = (BiValue) o;
        return Objects.equals(first, biValue.first) && Objects.equals(second, biValue.second);

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}