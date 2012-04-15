package net.minecraft.src;

public class BlockSpeedy extends Block
{
    public BlockSpeedy(int i, int j)
    {
        super(i, j, Material.sand);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1, (float)(j + 1) - f, k + 1);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        int meta = world.getBlockMetadata(i, j, k);

        if (meta == 0)
        {
            entity.motionZ *= 1.35F;
        }
        else if (meta == 1)
        {
            entity.motionX *= 1.35F;
        }

        if (entity.motionZ > 4)
        {
            entity.motionZ = 4;
        }

        if (entity.motionX > 4)
        {
            entity.motionX = 4;
        }

        if (entity.motionZ < -4)
        {
            entity.motionZ = -4;
        }

        if (entity.motionX < -4)
        {
            entity.motionX = -4;
        }
    }
    public int getBlockTextureFromSideAndMetadata(int i, int meta)
    {
        if (meta == 0)
        {
            return (203 + 16);
        }
        else
        {
            return 203;
        }
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 0);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 1);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 0);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 1);
        }
    }
}