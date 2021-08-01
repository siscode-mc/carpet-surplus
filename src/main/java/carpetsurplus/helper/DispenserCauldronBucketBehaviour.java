package carpetsurplus.helper;

import carpetsurplus.CarpetSurplusSettings;
import carpetsurplus.utils.DispenserUtils;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenserCauldronBucketBehaviour  extends FallibleItemDispenserBehavior {
    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack){
        if (CarpetSurplusSettings.dispensersInteractCauldron){
            return handle(pointer,stack);
        } else {
            return super.dispenseSilently(pointer,stack);
        }
    }

    private ItemStack handle(BlockPointer pointer, ItemStack stack) {
        World world = pointer.getWorld();
        if (world.isClient)
            return stack;

        BlockState stateInFront = DispenserUtils.GetStatePointedAt(pointer, world);
        BlockPos positionInFront = DispenserUtils.GetPositionPointedAt(pointer);

        Block blockInFront = stateInFront.getBlock();

        if (!(blockInFront instanceof CauldronBlock))
            return stack;

        Item itemKind = stack.getItem();
        if (itemKind == Items.WATER_BUCKET) {
            world.setBlockState(positionInFront, Blocks.WATER_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
            return new ItemStack(Items.BUCKET);
        }

        if (itemKind == Items.LAVA_BUCKET) {
            world.setBlockState(positionInFront, Blocks.LAVA_CAULDRON.getDefaultState());
            return new ItemStack(Items.BUCKET);
        }

        if (itemKind == Items.POWDER_SNOW_BUCKET) {
            world.setBlockState(positionInFront, Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
            return new ItemStack(Items.BUCKET);
        }

        return stack;
    }
}
