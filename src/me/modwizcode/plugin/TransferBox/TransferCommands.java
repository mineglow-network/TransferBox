/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferCommands {
    private TransferBox plug;
    private Map<Player,Inventory> inv;
    
    public TransferCommands(TransferBox plug) {
        this.plug = plug;
    }

    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) sender;
        int type = 0;
        if (!args[0].isEmpty()) {
            if (args[0].equalsIgnoreCase("select")) {
                type = 1;
            }
            if (args[0].equalsIgnoreCase("create")) {
                type = 2;
            }
            if (args[0].equalsIgnoreCase("add")) {
                type = 3;
            }
        }
        switch (type) {
            case 1:
                return doSelect(player,command,label,args);
                
            case 2:
                return doCreate(player,command,label,args);
                
            case 3:
                return doCreate(player,command,label,args);
                
            default:
                return false;
        }
                       
        
    }
    
    private boolean doSelect(Player sender, Command command, String label, String[] args) {
        Block selectedChest = sender.getTargetBlock(null, 10);
        if (selectedChest.getType().equals(Material.CHEST)) {
            Location chestLocation = selectedChest.getLocation();
            Location left, right, front, back;
            
            boolean doubleChest = false;
            left = chestLocation.add(-1.0d, 0.0d, 0.0d);
            right = chestLocation.add(1.0d, 0.0d, 0.0d);
            front = chestLocation.add(0.0d, 0.0d, 1.0d);
            back = chestLocation.add(0.0d, 0.0d, -1.0d);
            if (left.getBlock().getType().equals(Material.CHEST) ){
                doubleChest = true;
            }
            
            if (right.getBlock().getType().equals(Material.CHEST) ){
                doubleChest = true;
            }
            
            if (front.getBlock().getType().equals(Material.CHEST) ){
                doubleChest = true;
            }
            
            if (back.getBlock().getType().equals(Material.CHEST) ){
                doubleChest = true;
            }
            if (doubleChest == true) {
                DoubleChest itemChest = (DoubleChest) selectedChest.getState();
                
                inv.put(sender, itemChest.getInventory());
            }
            
            if (doubleChest == false) {
                Chest itemChest = (Chest) selectedChest.getState();
                
                inv.put(sender, itemChest.getInventory());
            }
            
        }
        return true;
    }
    
    private boolean doCreate(Player sender, Command command, String label, String[] args) {
        return true;
    }
    
    private boolean doAdd(Player sender, Command command, String label, String[] args) {
        return true;
    }
    
    
            
}
