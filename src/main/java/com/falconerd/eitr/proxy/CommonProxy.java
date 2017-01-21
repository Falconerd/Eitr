/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.proxy;

import com.falconerd.eitr.mana.IMana;
import com.falconerd.eitr.mana.Mana;
import com.falconerd.eitr.mana.ManaStorage;
import net.minecraft.item.Item;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {

    }

    public void init() {
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana.class);
    }
}
