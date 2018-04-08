package com.unrealdinnerbone.yatm.api;

import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class TelerporterEffect extends IForgeRegistryEntry.Impl<TelerporterEffect>
{

    public abstract int getTelerportTime();

    public abstract void spawnTeleportEffect(World world, DimBlockPos blockPos, EntityPlayer player);

    public abstract void spawnTelportArriveEffect(World world, DimBlockPos blockPos, EntityPlayer player);

    public abstract void spawnPreTeleportEffect(World world, DimBlockPos blockPos, double count);
}