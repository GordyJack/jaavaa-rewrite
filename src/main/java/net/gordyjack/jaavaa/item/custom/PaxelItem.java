package net.gordyjack.jaavaa.item.custom;

import net.gordyjack.jaavaa.data.*;
import net.minecraft.item.*;

public class PaxelItem extends MiningToolItem {
    public PaxelItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(material, JAAVAATags.Blocks.PAXEL_MINEABLE, attackDamage, attackSpeed, settings);
    }
}
