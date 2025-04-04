package com.example.an_addon.init;

import com.example.an_addon.ANShieldMainMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ANShieldMainMod.MODID);

    public static final RegistryObject<Item> ARS_SHIELD_BLOCK = ITEMS.register("ars_shield_block",
            () -> new BlockItem(BlockInit.ARS_SHIELD_BLOCK.get(), new Item.Properties()));
}
