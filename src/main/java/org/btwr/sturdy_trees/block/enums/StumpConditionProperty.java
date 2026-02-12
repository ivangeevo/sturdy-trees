package org.btwr.sturdy_trees.block.enums;

import com.google.common.collect.Lists;
import net.minecraft.state.property.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StumpConditionProperty extends EnumProperty<StumpCondition> {

    protected StumpConditionProperty(String name, Collection<StumpCondition> values) {
        super(name, StumpCondition.class, values);
    }

    /**
     * Creates a LogBreakType property containing all break type values.
     *
     * @param name the name of the property
     */
    public static StumpConditionProperty of(String name) {
        return of(name, type -> true);
    }

    /**
     * Creates a LogBreakType property with values filtered by a predicate.
     *
     * @param name the name of the property
     * @param filter predicate determining which values are allowed
     */
    public static StumpConditionProperty of(String name, Predicate<StumpCondition> filter) {
        return of(
                name,
                Arrays.stream(StumpCondition.values())
                        .filter(filter)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Creates a LogBreakType property containing the given explicit values.
     *
     * @param name the name of the property
     * @param values allowed values for the property
     */
    public static StumpConditionProperty of(String name, StumpCondition... values) {
        return of(name, Lists.newArrayList(values));
    }

    /**
     * Creates a LogBreakType property containing the given collection of values.
     *
     * @param name the name of the property
     * @param values allowed values for the property
     */
    public static StumpConditionProperty of(String name, Collection<StumpCondition> values) {
        return new StumpConditionProperty(name, values);
    }
}
