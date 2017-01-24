/*
 * Author: Falconerd
 * Date: 2017/01/19
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.block;

import com.falconerd.eitr.block.analyzer.BlockAnalyzer;
import com.falconerd.eitr.block.counter.BlockCounter;
import com.falconerd.eitr.block.harvester.BlockHarvester;
import com.falconerd.eitr.block.insulated_tank.BlockInsulatedTank;
import com.falconerd.eitr.pipes.BlockPipe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static BlockOre oreCopper;
    public static BlockCounter counter;
    public static BlockAnalyzer analyzer;
    public static BlockHarvester harvester;
    public static BlockPipe pipe;
    public static BlockInsulatedTank insulatedTank;

    public static void init() {
        oreCopper = register(new BlockOre("ore_copper").setCreativeTab(CreativeTabs.MATERIALS));
        counter = register(new BlockCounter());
        analyzer = register(new BlockAnalyzer());
        harvester = register(new BlockHarvester());
        pipe = register(new BlockPipe(Material.ROCK, "pipe"));
        insulatedTank = register(new BlockInsulatedTank());
    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if (block instanceof BlockBase) {
            ((BlockBase)block).registerItemModel(itemBlock);
        }

        if (block instanceof BlockTileEntity) {
            GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
        }

        return block;
    }

    private static <T extends Block> T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }
}
