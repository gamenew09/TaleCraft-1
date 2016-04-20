package de.longor.talecraft.script.wrappers.item;

import java.util.List;

import de.longor.talecraft.TaleCraft;
import de.longor.talecraft.script.wrappers.IObjectWrapper;
import net.minecraft.item.Item;

public class ItemObjectWrapper implements IObjectWrapper {
	private Item item;

	public ItemObjectWrapper(Item item) {
		this.item = item;
	}

	@Override
	public Item internal() {
		return item;
	}

	@Override
	public List<String> getOwnPropertyNames() {
		return TaleCraft.globalScriptManager.getOwnPropertyNames(this);
	}

	public String getUnlocalizedName() {
		return item.getUnlocalizedName();
	}
	
	public String getRegistryName() {
		return item.getRegistryName();
	}

}
