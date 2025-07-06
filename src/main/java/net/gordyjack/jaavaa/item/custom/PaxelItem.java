package net.gordyjack.jaavaa.item.custom;

import net.gordyjack.jaavaa.data.*;
import net.minecraft.item.*;

public class PaxelItem extends Item {
    public PaxelItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(settings.tool(material, JAAVAATags.Blocks.PAXEL_MINEABLE, attackDamage, attackSpeed, 0.0f));
    }
}
