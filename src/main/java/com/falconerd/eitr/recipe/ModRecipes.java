/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.recipe;

import com.falconerd.eitr.block.ModBlocks;
import com.falconerd.eitr.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void init() {
        // Shapeless recipe example
        // GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotCopper), ModBlocks.oreCopper);

        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.7f);
    }
}
