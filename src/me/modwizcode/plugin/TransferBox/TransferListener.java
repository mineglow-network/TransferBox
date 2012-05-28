/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
                event.getView().getTopInventory().setContents(TransferStorage.chestInventories.get(groupName).getContents());
            }
        }
        
    }
    
    @EventHandler
    public void OnInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getType() == InventoryType.CHEST) {
            if (TransferStorage.chestBlocks.get(event.getPlayer().getTargetBlock(null, 10))!=null) {
                String groupName = TransferStorage.chestBlocks.get(event.getPlayer().getTargetBlock(null, 10));
                TransferStorage.chestInventories.put(groupName,event.getView().getTopInventory());
            }
        }
    }
}
