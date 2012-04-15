package net.minecraft.server;

public class BlockSpeedy extends Block
{
  public BlockSpeedy(int i, int j)
  {
    super(i, j, Material.SAND);
  }

  public AxisAlignedBB e(World world, int i, int j, int k) {
    float f = 0.125F;

    return AxisAlignedBB.b(i, j, k, i + 1, j + 1 - f, k + 1);
  }
  
  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
      int meta = world.getData(i, j, k);
      if (meta == 0)          entity.motZ *= 1.35F;
      else if (meta == 1) entity.motX *= 1.35F;
      if (entity.motZ > 4) entity.motZ = 4;
      if (entity.motX > 4) entity.motX = 4;
      if (entity.motZ < -4) entity.motZ = -4;
      if (entity.motX < -4) entity.motX = -4;
     
  }
 
  public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving) {
	    int i = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;

	    if (i == 0) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0);
	    if (i == 1) paramWorld.setData(paramInt1, paramInt2, paramInt3, 1);
	    if (i == 2) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0);
	    if (i == 3) paramWorld.setData(paramInt1, paramInt2, paramInt3, 1);
	  }
  
  public int a(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return 202;
    }
    return (202-16);
  }
  
}