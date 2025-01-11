package net.gordyjack.jaavaa.enchantment.effect;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.gordyjack.jaavaa.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import net.minecraft.enchantment.effect.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

import java.util.stream.*;

//TODO: Fix this.
public record CurseOfTheCapriciousEffect(EnchantmentLevelBasedValue amount) implements EnchantmentLocationBasedEffect {
    public static final MapCodec<CurseOfTheCapriciousEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(CurseOfTheCapriciousEffect::amount)
            ).apply(instance, CurseOfTheCapriciousEffect::new)
    );
    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos, boolean newlyApplied) {
        JAAVAA.log("Curse of the Capricious effect applied", 'e');
        if (user instanceof PlayerEntity) {
            JAAVAA.log("Curse of the Capricious effect applied to player", 'e');
            if (pos != null && !world.isClient) {
                JAAVAA.log("Curse of the Capricious effect applied to player at position", 'e');
                Block randomBlock = Registries.BLOCK.getRandom(Random.create()).get().value();
                world.addEntities(Stream.of(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(randomBlock.asItem()))));
            }
        }
    }
    @Override
    public MapCodec<? extends EnchantmentLocationBasedEffect> getCodec() {
        return CODEC;
    }
    public static void onBlockMined(ItemStack tool, World world, BlockPos pos) {
        if (!world.isClient && tool.hasEnchantments() &&
                tool.getEnchantments().getSize() > 0) {
            for (var enchantment : tool.getEnchantments().getEnchantmentEntries()) {
                if (enchantment.getKey().getIdAsString().contains("curse_of_the_capricious")) {
                    Block randomBlock = Registries.BLOCK.getRandom(Random.create()).get().value();
                    ((ServerWorld)world).addEntities(Stream.of(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(randomBlock.asItem()))));
                }
            }
        }
    }
}
