/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Aetherium is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/aetherium
 */
package com.falconerd.eitr.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
    public static ItemBase ingotCopper;

    public static void init() {
        ingotCopper = register(new ItemBase("ingot_copper").setCreativeTab(CreativeTabs.MATERIALS));
    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if (item instanceof ItemBase) {
            ((ItemBase)item).registerItemModel();
        }

        return item;
    }
}
