package com.example.an_addon.init;

import com.example.an_addon.ANProjectileShieldBlock;
import com.example.an_addon.ANShieldMainMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ANShieldMainMod.MODID);

    public static final RegistryObject<Block> ARS_SHIELD_BLOCK = BLOCKS.register("ars_shield_block",
            () -> new ANProjectileShieldBlock(Block.Properties.copy(Blocks.GLASS).instabreak().lightLevel(state -> 8)));
}
