package com.unrealdinnerbone.yatm.common.effect;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.util.ParticleHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RingEffect extends TelerporterEffect {


    @Override
    public int getTelerportTime() {
        return 20;
    }

    @Override
    public void spawnTeleportEffect(World world, BlockPos blockPos, EntityPlayer player) {
        spawn(world, blockPos);
    }

    @Override
    public void spawnTelportArriveEffect(World world, BlockPos blockPos, EntityPlayer player) {
        spawn(world, blockPos);
    }

    @Override
    public void spawnPreTeleportEffect(World world, BlockPos blockPos, double count) {
        ParticleHelper.spawnParticleRing(world, EnumParticleTypes.FLAME,  blockPos.getX() + 0.5,  blockPos.getY() + (count / 9.0),   blockPos.getZ() + 0.5, 0, 0, 0, 0.1);
    }

    private void spawn(World world, BlockPos blockPos) {
        for (int i = 0; i < 20; i++) {
            ParticleHelper.spawnParticleRing(world, EnumParticleTypes.ENCHANTMENT_TABLE,  blockPos.getX() + 0.5,  blockPos.getY() + (i / 9.0),   blockPos.getZ() + 0.5, 0, 0, 0, 0.1);

        }
    }

}