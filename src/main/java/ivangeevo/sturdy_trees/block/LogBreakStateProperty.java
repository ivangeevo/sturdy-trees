package ivangeevo.sturdy_trees.block;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.state.property.EnumProperty;

public class LogBreakStateProperty
        extends EnumProperty<LogBreakState> {

    protected LogBreakStateProperty(String name, Collection<LogBreakState> values) {
        super(name, LogBreakState.class, values);
    }

    public static LogBreakStateProperty of(String name) {
        return LogBreakStateProperty.of(name, (LogBreakState breakType) -> true);
    }

    public static LogBreakStateProperty of(String name, Predicate<LogBreakState> filter) {
        return LogBreakStateProperty.of(name, Arrays.stream(LogBreakState.values()).filter(filter).collect(Collectors.toList()));
    }


    public static LogBreakStateProperty of(String name, LogBreakState... values) {
        return LogBreakStateProperty.of(name, Lists.newArrayList(values));
    }

    public static LogBreakStateProperty of(String name, Collection<LogBreakState> values) {
        return new LogBreakStateProperty(name, values);
    }
}

