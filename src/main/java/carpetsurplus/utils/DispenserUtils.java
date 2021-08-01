package carpetsurplus.utils;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class  DispenserUtils {
    public static BlockState GetStatePointedAt(BlockPointer dispenser, World world){
        BlockState state = dispenser.getBlockState();
        Direction facing = state.get(Properties.FACING);
        BlockPos offset = dispenser.getPos().offset(facing);
        return world.getBlockState(offset);
    }

    public static BlockPos GetPositionPointedAt(BlockPointer dispenser) {
        BlockState state = dispenser.getBlockState();
        Direction facing = state.get(Properties.FACING);
        return dispenser.getPos().offset(facing);
    }
}
