package com.colde.an_addon.entities;

import com.colde.an_addon.ANShieldMainMod;
import com.colde.an_addon.init.BlockInit;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntites {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ANShieldMainMod.MODID);


    public static final RegistryObject<BlockEntityType<ANProjectileShieldBlockEntity>> ARS_SHIELD_BE =
            BLOCK_ENTITIES.register("ars_shield_be", () ->
                    BlockEntityType.Builder.of(ANProjectileShieldBlockEntity::new,
                            BlockInit.ARS_SHIELD_BLOCK.get()).build(null));


}
