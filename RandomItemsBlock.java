package com.randomitems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RandomItemsBlock extends Block {
    public RandomItemsBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            // Стон жителя
            world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_HURT, SoundCategory.BLOCKS, 1.0f, 1.0f);

            // Случайный предмет из всех зарегистрированных (кроме воздуха)
            Item item = Items.DIRT;
            for (int i = 0; i < 20; i++) {
                var entry = Registries.ITEM.getRandom(world.random);
                if (entry.isPresent() && entry.get().value() != Items.AIR) {
                    item = entry.get().value();
                    break;
                }
            }

            ItemScatterer.spawn(world, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, new ItemStack(item));
        }
        return ActionResult.SUCCESS;
    }
}
