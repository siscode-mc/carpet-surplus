package carpetsurplus.helper;

import carpetsurplus.CarpetSurplusSettings;
import carpetsurplus.utils.DispenserUtils;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenserCauldronEmptyBucketBehaviour extends FallibleItemDispenserBehavior  {

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
        BlockPos offset = DispenserUtils.GetPositionPointedAt(pointer);

        Block blockInFront = stateInFront.getBlock();
        ItemStack itemStack = stack;

        if (!(blockInFront instanceof AbstractCauldronBlock))
            return stack;


        if (blockInFront instanceof LeveledCauldronBlock cauldronBlock) {
            if (!cauldronBlock.isFull(stateInFront)) {
                return stack;
            }

            if (blockInFront instanceof PowderSnowCauldronBlock) {
                itemStack = new ItemStack(Items.POWDER_SNOW_BUCKET);
            } else {
                itemStack = new ItemStack(Items.WATER_BUCKET);
            }
        }

        if (blockInFront instanceof LavaCauldronBlock cauldronBlock) {
            if (!cauldronBlock.isFull(stateInFront)) {
                return stack;
            }

            itemStack = new ItemStack(Items.LAVA_BUCKET);
        }

        world.setBlockState(offset, Blocks.CAULDRON.getDefaultState());
        return itemStack;
    }
}
