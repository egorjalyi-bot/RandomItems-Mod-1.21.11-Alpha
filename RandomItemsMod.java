package com.randomitems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class RandomItemsMod implements ModInitializer {
    public static final String MOD_ID = "randomitems";
    public static final Identifier ID = Identifier.of(MOD_ID, "random_items");

    public static Block RANDOM_ITEMS_BLOCK;

    @Override
    public void onInitialize() {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, ID);
        RANDOM_ITEMS_BLOCK = Registry.register(Registries.BLOCK, blockKey,
                new RandomItemsBlock(AbstractBlock.Settings.create()
                        .registryKey(blockKey)
                        .mapColor(MapColor.YELLOW)
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.WOOD)));

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, ID);
        Registry.register(Registries.ITEM, itemKey,
                new BlockItem(RANDOM_ITEMS_BLOCK, new Item.Settings()
                        .registryKey(itemKey)
                        .useBlockPrefixedTranslationKey()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> entries.add(RANDOM_ITEMS_BLOCK));
    }
}
