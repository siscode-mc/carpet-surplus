package carpetsurplus.mixins;

import carpetsurplus.CarpetSurplusSettings;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnHelper.class)
public class SpawnHelperMixin {

    @Inject(
            method = "spawnEntitiesInChunk(Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/WorldChunk;Lnet/minecraft/world/SpawnHelper$Checker;Lnet/minecraft/world/SpawnHelper$Runner;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void spawnEntitiesInChunk(SpawnGroup group, ServerWorld world, WorldChunk chunk, SpawnHelper.Checker checker, SpawnHelper.Runner runner, CallbackInfo ci) {
        if (CarpetSurplusSettings.heightmapIndependentSpawning) {
            ChunkPos chunkPos = chunk.getPos();
            int maxY = chunk.getHighestNonEmptySectionYOffset() + 16;
            for (int secY = world.getBottomY(); secY < maxY; secY += 16) {
                if(world.random.nextFloat() > 0.25f) continue;
                int x = chunkPos.getStartX() + world.random.nextInt(16);
                int y = secY + world.random.nextInt(16);
                int z = chunkPos.getStartZ() + world.random.nextInt(16);
                SpawnHelper.spawnEntitiesInChunk(group, world, chunk, new BlockPos(x, y, z), checker, runner);
            }
            ci.cancel();
        }
    }
}
