package net.gordyjack.jaavaa.block;

import net.gordyjack.jaavaa.block.enums.*;
import net.minecraft.state.property.*;

public class JAAVAABlockProperties {
    public static final BooleanProperty BACK_POWERED = BooleanProperty.of("back_powered");
    public static final BooleanProperty LEFT_POWERED = BooleanProperty.of("left_powered");
    public static final BooleanProperty RIGHT_POWERED = BooleanProperty.of("right_powered");
    public static final EnumProperty<DecoderMode> DECODER_MODE = EnumProperty.of("mode", DecoderMode.class);
    public static final EnumProperty<DecoderTarget> DECODER_TARGET = EnumProperty.of("target", DecoderTarget.class);
    public static final IntProperty BLOCKTANT_POSITION = IntProperty.of("position", 0b00000001, 0b11111111);
    public static final IntProperty LUMINANCE = IntProperty.of("luminance", 0, 15);
    public static final IntProperty DELAY = IntProperty.of("delay", 0, 7);
    public static final IntProperty PULSE = IntProperty.of("pulse", 0, 7);
}
