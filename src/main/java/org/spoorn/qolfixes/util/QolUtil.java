package org.spoorn.qolfixes.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;

import java.util.Set;

public class QolUtil {
    
    public static final Set<Class<? extends Item>> INCREASE_MAX_STACK_ITEMS = Set.of(
            SaddleItem.class,
            SnowballItem.class,
            BoatItem.class,
            StewItem.class,
            EggItem.class,
            EnchantedBookItem.class,
            EnderPearlItem.class,
            PotionItem.class,
            HorseArmorItem.class,
            MusicDiscItem.class,
            HoneyBottleItem.class,
            BucketItem.class,
            MilkBucketItem.class,
            EntityBucketItem.class,
            PowderSnowBucketItem.class
    );
    
    public static void givePlayerItemOrDrop(PlayerEntity player, ItemStack stack) {
        if (!player.giveItemStack(stack)) {
            player.dropStack(stack);
        }
    }
}
