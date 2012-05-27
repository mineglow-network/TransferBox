/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferStorage {
    public static Map<String, Inventory> chestInventories = new HashMap<String, Inventory>();
    public static Map<Block, String> chestBlocks = new HashMap<Block,String>();
}
