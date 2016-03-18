package de.longor.talecraft.voxelator.predicates;

import de.longor.talecraft.util.MutableBlockPos;
import de.longor.talecraft.voxelator.CachedWorldDiff;
import de.longor.talecraft.voxelator.VXPredicate;
import net.minecraft.util.math.BlockPos;

public final class VXPredicateHeightLimit extends VXPredicate {
	private final int height;

	public VXPredicateHeightLimit(int height) {
		this.height = height;
	}

	@Override
	public boolean test(BlockPos pos, BlockPos center, MutableBlockPos offset, CachedWorldDiff fworld) {
		return pos.getY() <= height;
	}

}
