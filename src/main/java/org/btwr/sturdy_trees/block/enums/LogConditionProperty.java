package org.btwr.sturdy_trees.block.enums;

import com.google.common.collect.Lists;
import net.minecraft.state.property.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogConditionProperty extends EnumProperty<LogCondition> {

    protected LogConditionProperty(String name, Collection<LogCondition> values) {
        super(name, LogCondition.class, values);
    }

    /**
     * Creates a LogCondition property containing all break type values.
     *
     * @param name the name of the property
     */
    public static LogConditionProperty of(String name) {
        return of(name, type -> true);
    }

    /**
     * Creates a LogCondition property with values filtered by a predicate.
     *
     * @param name the name of the property
     * @param filter predicate determining which values are allowed
     */
    public static LogConditionProperty of(String name, Predicate<LogCondition> filter) {
        return of(
                name,
                Arrays.stream(LogCondition.values())
                        .filter(filter)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Creates a LogCondition property containing the given explicit values.
     *
     * @param name the name of the property
     * @param values allowed values for the property
     */
    public static LogConditionProperty of(String name, LogCondition... values) {
        return of(name, Lists.newArrayList(values));
    }

    /**
     * Creates a LogCondition property containing the given collection of values.
     *
     * @param name the name of the property
     * @param values allowed values for the property
     */
    public static LogConditionProperty of(String name, Collection<LogCondition> values) {
        return new LogConditionProperty(name, values);
    }
}
