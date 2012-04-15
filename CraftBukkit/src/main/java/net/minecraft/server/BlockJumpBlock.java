package net.minecraft.server;


public class BlockJumpBlock extends Block
{

    protected BlockJumpBlock(int i, int j)
    {
        super(i, j, Material.WOOD);
    }
public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
      entity.motY += 4.0;
    }
}
