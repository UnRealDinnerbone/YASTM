package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;

public class BlockTeleporter extends Block implements ITileEntityProvider {

    private final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

    public BlockTeleporter() {
        super(Material.GROUND, MapColor.CYAN);
        this.fullBlock = false;
        this.setLightOpacity(255);
        this.setCreativeTab(CreativeTabs.TRANSPORTATION);
        this.setUnlocalizedName("teleporter");
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn instanceof EntityPlayerMP) {
                YatmWorldSaveData saveData = YatmWorldSaveData.get(worldIn);
                DimBlockPos dimBlockPos = new DimBlockPos(pos, worldIn.provider.getDimension());
                TileEntityTeleporter tileEntityTeleporter = (TileEntityTeleporter) worldIn.getTileEntity(pos);
                Yastm.getNetworkWrapper().sendTo(new PacketOpenSetFrequencyGUI(dimBlockPos, saveData.getTelerporterData().getIDFormPos(dimBlockPos), tileEntityTeleporter.getEffectColor().getRGB()), (EntityPlayerMP) playerIn);
            }
        }
        return true;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if(!worldIn.isRemote) {
            YatmWorldSaveData.get(worldIn).getTelerporterData().removeTeleporter(new DimBlockPos(pos, worldIn.provider.getDimension()));
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTeleporter();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

}
