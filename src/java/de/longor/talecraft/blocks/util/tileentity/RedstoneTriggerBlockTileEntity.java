package de.longor.talecraft.blocks.util.tileentity;

import java.util.List;

import de.longor.talecraft.blocks.TCTileEntity;
import de.longor.talecraft.invoke.BlockTriggerInvoke;
import de.longor.talecraft.invoke.EnumTriggerState;
import de.longor.talecraft.invoke.FileScriptInvoke;
import de.longor.talecraft.invoke.IInvoke;
import de.longor.talecraft.invoke.Invoke;
import net.minecraft.nbt.NBTTagCompound;

public class RedstoneTriggerBlockTileEntity extends TCTileEntity {
	IInvoke triggerInvoke;

	public RedstoneTriggerBlockTileEntity() {
		triggerInvoke = BlockTriggerInvoke.ZEROINSTANCE;
	}

	@Override
	public void init() {
		// don't do anything
	}

	@Override
	public void readFromNBT_do(NBTTagCompound compound) {
		triggerInvoke = IInvoke.Serializer.read(compound.getCompoundTag("triggerInvoke"));
	}

	@Override
	public NBTTagCompound writeToNBT_do(NBTTagCompound comp) {
		comp.setTag("triggerInvoke", IInvoke.Serializer.write(triggerInvoke));
		return comp;
	}

	public void invokeFromUpdateTick(EnumTriggerState triggerState) {
		if(this.worldObj.isRemote)
			return;

		Invoke.invoke(triggerInvoke, this, null, triggerState);
	}

	@Override
	public void commandReceived(String command, NBTTagCompound data) {
		if(command.equals("trigger")) {
			Invoke.invoke(triggerInvoke, this, null, EnumTriggerState.ON);
			return;
		}

		if(command.equals("reload")) {
			if(triggerInvoke != null && triggerInvoke instanceof FileScriptInvoke) {
				((FileScriptInvoke)triggerInvoke).reloadScript();
			}
			return;
		}

		super.commandReceived(command, data);
	}

	@Override
	public String getName() {
		return "RedstoneTrigger@"+pos;
	}

	@Override
	public String toString() {
		return "RedstoneTriggerTileEntity:{}";
	}

	@Override
	public void getInvokes(List<IInvoke> invokes) {
		invokes.add(triggerInvoke);
	}

	public IInvoke getInvoke() {
		return triggerInvoke;
	}

	//	@Override
	//	public void getInvokesAsDataCompounds(List<NBTTagCompound> invokes) {
	//		invokes.add(triggerInvoke);
	//	}

	@Override
	public void getInvokeColor(float[] color) {
		color[0] = 0.75f;
		color[1] = 0.0f;
		color[2] = 0.0f;
	}

}
