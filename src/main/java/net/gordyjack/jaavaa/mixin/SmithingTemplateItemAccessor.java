package net.gordyjack.jaavaa.mixin;

import net.minecraft.item.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

import java.util.*;

@Mixin(SmithingTemplateItem.class)
public interface SmithingTemplateItemAccessor {
    @Accessor("DESCRIPTION_FORMATTING")
    static Formatting getDescriptionFormatting() {
        throw new AssertionError();
    }

    @Invoker("getNetheriteUpgradeEmptyBaseSlotTextures")
    static List<Identifier> getNetheriteUpgradeEmptyBaseSlotTextures() {
        throw new AssertionError();
    }
    @Invoker("getNetheriteUpgradeEmptyAdditionsSlotTextures")
    static List<Identifier> getNetheriteUpgradeEmptyAdditionsSlotTextures() {
        throw new AssertionError();
    }
}
