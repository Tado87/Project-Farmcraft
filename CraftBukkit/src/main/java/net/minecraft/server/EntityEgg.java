package net.minecraft.server;

// CraftBukkit start
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerEggThrowEvent;
// CraftBukkit end

public class EntityEgg extends EntityProjectile {

    public EntityEgg(World world) {
        super(world);
    }

    public EntityEgg(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    public EntityEgg(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        // CraftBukkit start
        if (movingobjectposition.entity != null) {
            if (org.bukkit.craftbukkit.event.CraftEventFactory.handleProjectileEvent((org.bukkit.entity.Projectile) this.getBukkitEntity(), movingobjectposition.entity, DamageSource.projectile(this, this.shooter), 0)) {
                ; // Original code does nothing *yet*
            }
        }

        boolean hatching = !this.world.isStatic && this.random.nextInt(8) == 0;
        int numHatching = (this.random.nextInt(32) == 0) ? 4 : 1;
        if (!hatching) {
            numHatching = 0;
        }

        EntityType hatchingType = EntityType.CHICKEN;

        if (this.shooter instanceof EntityPlayer) {
            org.bukkit.entity.Player player = (this.shooter == null) ? null : (org.bukkit.entity.Player) this.shooter.getBukkitEntity();

            PlayerEggThrowEvent event = new PlayerEggThrowEvent(player, (org.bukkit.entity.Egg) this.getBukkitEntity(), hatching, (byte) numHatching, hatchingType);
            this.world.getServer().getPluginManager().callEvent(event);

            hatching = event.isHatching();
            numHatching = event.getNumHatches();
            hatchingType = event.getHatchingType();
        }

        if (hatching) {
            for (int k = 0; k < numHatching; k++) {
                org.bukkit.entity.Entity entity = world.getWorld().spawn(new Location(world.getWorld(), this.locX, this.locY, this.locZ, this.yaw, 0.0F), hatchingType.getEntityClass(), SpawnReason.EGG);

                if (entity instanceof Animals) {
                    ((Animals) entity).setBaby();
                }
            }
        }
        // CraftBukkit end

        for (int j = 0; j < 8; ++j) {
            this.world.a("snowballpoof", this.locX, this.locY, this.locZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isStatic) {
            this.die();
        }
    }
}