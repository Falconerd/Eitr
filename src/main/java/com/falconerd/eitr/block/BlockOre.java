/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Aetherium is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/aetherium
 */
package com.falconerd.eitr.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOre extends BlockBase {
    public BlockOre(String name) {
        super(Material.ROCK, name);

        setHardness(3f);
        setResistance(5f);
    }

    @Override
    public BlockOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
