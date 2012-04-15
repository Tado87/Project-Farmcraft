package net.minecraft.src;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class BlockJumpBlock extends Block
{
    protected BlockJumpBlock(int i, int j)
    {
        super(i, j, Material.ground);
    }
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        entity.motionY += 4.5;
    }
}
