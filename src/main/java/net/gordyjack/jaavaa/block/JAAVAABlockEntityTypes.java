package net.gordyjack.jaavaa.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.*;
import net.gordyjack.jaavaa.*;
import net.gordyjack.jaavaa.block.custom.entity.*;
import net.minecraft.block.entity.*;
import net.minecraft.registry.*;

public class JAAVAABlockEntityTypes {
    public static final BlockEntityType<AlloyFurnaceBlockEntity> ALLOY_FURNACE_BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(AlloyFurnaceBlockEntity::new, JAAVAABlocks.ALLOY_FURNACE).build();
    public static final BlockEntityType<RecyclingTableBlockEntity> RECYCLING_TABLE_BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(RecyclingTableBlockEntity::new, JAAVAABlocks.RECYCLING_TABLE).build();
    
    public static void init() {
        JAAVAA.log("Initializing block entity types");
        
        Registry.register(Registries.BLOCK_ENTITY_TYPE, JAAVAA.id("alloy_furnace"), ALLOY_FURNACE_BLOCK_ENTITY_TYPE);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, JAAVAA.id("recycling_table"), RECYCLING_TABLE_BLOCK_ENTITY_TYPE);
    }
}
