package carpetsurplus.mixins;

import carpetsurplus.CarpetSurplusSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntity_despawnMixin extends LivingEntity {
    protected MobEntity_despawnMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "checkDespawn", at = @At("HEAD"), cancellable = true)
    private void despawnRangeMixin(CallbackInfo ci) {
        if (CarpetSurplusSettings.localDespawning) {
            if (!(this.world instanceof ServerWorld world)) return;
            ChunkPos chunk = this.getChunkPos();
            var tickets = world.getChunkManager().threadedAnvilChunkStorage.getTicketManager().getTicketSet(chunk.toLong());
            if (tickets.isEmpty() || tickets.first().getType() != ChunkTicketType.PLAYER) {
                ci.cancel();
            }
        }
    }
}
