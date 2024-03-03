package ivangeevo.sturdy_trees.state.property;

import ivangeevo.sturdy_trees.block.util.LogType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class ModProperties {

    public static final EnumProperty<LogType> LOG_TYPE = EnumProperty.of("log_type", LogType.class);
    public static final IntProperty BREAK_LEVEL = IntProperty.of("break_level_stripped",0,3);

}
