package carpetsurplus.mixins;

import carpetsurplus.helper.DispenserCauldronBucketBehaviour;
import carpetsurplus.helper.DispenserCauldronEmptyBucketBehaviour;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(DispenserBlock.class)
public abstract class DispenserMixin {
    @Inject(method = "getBehaviorForItem", at=@At("HEAD"), cancellable = true)
    private void registerBehaviours(ItemStack stack, CallbackInfoReturnable<DispenserBehavior> cir) {
        Item item = stack.getItem();
        Item[] filledBuckets = { Items.WATER_BUCKET, Items.POWDER_SNOW_BUCKET, Items.LAVA_BUCKET };

        if (item == Items.BUCKET) {
            cir.setReturnValue(new DispenserCauldronEmptyBucketBehaviour());
        }
        if (Arrays.stream(filledBuckets).anyMatch(x -> x == item)) {
            cir.setReturnValue(new DispenserCauldronBucketBehaviour());
        }
    }
}
