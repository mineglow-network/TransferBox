/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferListener extends JavaPlugin implements Listener{
    
    @EventHandler
    public void OnInventoryOpen(InventoryOpenEvent event) {
       
        if (event.getView().getType() == InventoryType.CHEST) {
            
            if (TransferStorage.chestBlocks.get(event.getPlayer().getTargetBlock(null, 10))!=null) {
                
                String groupName = TransferStorage.chestBlocks.get(event.getPlayer().getTargetBlock(null, 10));
                TransferSync.sync(groupName, event.getView().getTopInventory(), TransferState.OPEN);
            }
        }
        
    }
    
    @EventHandler
    public void OnInventoryClose(InventoryCloseEvent event) {
        
        if (event.getView().getType() == InventoryType.CHEST) {
            
            if (TransferStorage.chestBlocks.get(event.getPlayer().getTargetBlock(null, 10))!=null) {
                
                String groupName = TransferStorage.chestBlocks.get(event.getPlayer().getTargetBlock(null, 10));
                TransferSync.sync(groupName, event.getView().getTopInventory(), TransferState.CLOSE);
                
            }
        }
    }
    
    @EventHandler
    public void OnBlockBreak(BlockBreakEvent event) {
        Block broken = event.getBlock();
        
        //is it a chest?
        if (broken.getType().equals(Material.CHEST)) {
            // if so check for presense in a sync group
            if (TransferStorage.chestBlocks.containsKey(broken)) {
                //if so remove from map.
                TransferStorage.chestBlocks.remove(broken);
                
            }
        }
    }
}
