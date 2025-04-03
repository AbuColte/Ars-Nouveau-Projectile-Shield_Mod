package com.example.an_addon.entities;

import com.hollingsworth.arsnouveau.common.entity.EntityProjectileSpell;
import com.hollingsworth.arsnouveau.setup.registry.ModEntities;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.*;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.CapabilityProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class ANProjectileShieldBlockEntity extends BlockEntity {
    public ANProjectileShieldBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.ARS_SHIELD_BE.get(), pos, state);
    }
    int timer = 0;
    boolean isActive = true;
    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        ANProjectileShieldBlockEntity tile = (ANProjectileShieldBlockEntity) blockEntity;
        if (!level.isClientSide() && tile.isActive){
            tile.timer++;
            if (tile.timer > 10){
                tile.timer = 0;
                spawnParticles(level, pos);
                tile.hurtMobs();
            }
        }

    }
    final int RANGE = 15;
    private void hurtMobs() {
        BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AABB box = new AABB(topCorner, bottomCorner);

        List<Entity> entities = this.level.getEntities(null, box);
        for (Entity target : entities) {
            if (target instanceof EntityProjectileSpell && !(target instanceof Player)) {
                // Store position before discarding
                double x = target.getX();
                double y = target.getY();
                double z = target.getZ();

                // Remove the projectile
                target.discard();

                // Play explosion effect (client-side only)
                if (!level.isClientSide) {
                    // Explosion particles
                    for (int i = 0; i < 20; i++) {
                        level.addParticle(ParticleTypes.EXPLOSION,
                                x, y, z,
                                (level.random.nextDouble() - 0.5) * 0.5,
                                level.random.nextDouble() * 0.5,
                                (level.random.nextDouble() - 0.5) * 0.5);
                    }

                    // Additional smoke particles
                    level.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 0, 0, 0);
                }
                if (!level.isClientSide) {
                    level.playSound(null,
                            new BlockPos((int)x, (int)y, (int)z),
                            SoundEvents.GENERIC_EXPLODE,
                            SoundSource.BLOCKS,
                            1.0f,
                            (1.0f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f));
                }
            }
        }
    }
    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        double d0 = 0.5625;
        RandomSource randomsource = pLevel.random;
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            BlockPos blockpos = pPos.relative(direction);
            if (!pLevel.getBlockState(blockpos).isSolidRender(pLevel, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getStepX() : (double)randomsource.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction.getStepY() : (double)randomsource.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getStepZ() : (double)randomsource.nextFloat();
                pLevel.addParticle(DustParticleOptions.REDSTONE, (double)pPos.getX() + d1, (double)pPos.getY() + d2, (double)pPos.getZ() + d3, 0.0, 0.0, 0.0);
            }
        }

    }

}
