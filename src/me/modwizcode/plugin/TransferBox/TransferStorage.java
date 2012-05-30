/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferStorage {
    
    // Group Inventory that is to be shared
    public static Map<String, Inventory> chestInventories = new HashMap<String, Inventory>();
    
    // Get the group for a peticular block.
    public static Map<Block, String> chestBlocks = new HashMap<Block,String>();
    
    // Get the worlds that a group contains.
    public static Map<String, List<World>> groupWorlds = new HashMap<String,List<World>>();
    
}
