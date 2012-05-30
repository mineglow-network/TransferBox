/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import com.onarandombox.multiverseinventories.api.GroupManager;
import com.onarandombox.multiverseinventories.api.profile.WorldGroupProfile;
import java.util.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferCommands {
    private TransferBox plug;
    private Map<Player,Inventory> inv = new HashMap<Player,Inventory>();
    private Map<Player,Block> invb = new HashMap<Player,Block>();
    
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
        Block selectedChest = getPlayerBlock(sender);
        if (selectedChest.getType().equals(Material.CHEST)) {

                
                try {
                    DoubleChest itemChest = (DoubleChest) selectedChest.getState();
                    inv.put(sender, itemChest.getInventory());
                invb.put(sender,selectedChest);
                } catch(ClassCastException e) {
                    Chest itemChest = (Chest) selectedChest.getState();
                    inv.put(sender, itemChest.getInventory());
                invb.put(sender,selectedChest);
                }
            
        }
        return true;
    }
    
    // Initialize and create a new sync group.
    private boolean doCreate(Player sender, Command command, String label, String[] args) {
        if (inv.containsKey(sender)) {
            if (!args[1].isEmpty()) {
                // Save the sync group name.
                String groupName = args[1];
                
                // Initialize the new sync groups.
                TransferStorage.chestInventories.put(groupName, null);
                
                // Initialize the chest block group.
                TransferStorage.chestBlocks.put(invb.get(sender),null);
                
                // Store the inventory for the sync group
                TransferStorage.chestInventories.put(groupName, inv.get(sender));
                
                // Save the chest block with the corrisponding group for syncing.
                TransferStorage.chestBlocks.put(invb.get(sender),groupName);
                
                // Create a new list for the worlds that will be in this group.
                // These are used to make sure that all the blocks are in worlds which can sync
                // This is not a concern without using multiverse inventories.
                List<World> tempWorlds = new ArrayList<World>();
                
                // Get the world this block is in.
                World blockWorld = invb.get(sender).getWorld();
                
                // Add the world to the list of worlds for this sync group.
                tempWorlds.add(blockWorld);
                
                // Save the list into the storage class.
                TransferStorage.groupWorlds.put(groupName, tempWorlds);
                
            }
        }
        return true;
    }
    
    // Add a new chest to a group.
    private boolean doAdd(Player sender, Command command, String label, String[] args) {
        
        // Make sure the player is still there.
        if (inv.get(sender) != null) {
            
            // Make sure the group name is present.
            if (!args[1].isEmpty()) {
                
                // Store the group name.
                String groupName = args[1];
                    
                    
                    
                    // Get the worlds already in this group.
                    List<World> tempWorlds = TransferStorage.groupWorlds.get(groupName);
                    
                    // Get the world for this chest.
                    World blockWorld = invb.get(sender).getWorld();
                    
                    // Add the world to the list of worlds.
                    tempWorlds.add(blockWorld);
                    
                    if (TransferSync.hasMultiInv()) {
                        if (TransferSync.isSharing(tempWorlds)){
                            
                            // Store this chest and its group for syncing.
                            TransferStorage.chestBlocks.put(invb.get(sender),groupName);
                            
                            // Store the world in the list of synced worlds for this group.
                            TransferStorage.groupWorlds.put(groupName,tempWorlds);
                            
                        }
                    } else {
                        // The server does not have multiverse inventories.
                        
                        // Store this chest and its group for syncing.
                        TransferStorage.chestBlocks.put(invb.get(sender),groupName);
                        
                        // Store the world in the list of synced worlds for this group.
                        TransferStorage.groupWorlds.put(groupName,tempWorlds);
                    }
                    
            }
        }
        return true;
    }
    
    // Get the block that a player is looking at.
    private Block getPlayerBlock(Player player) {
        
        HashSet<Byte> transparent = new HashSet<Byte>();
        
        // Snow covered chests should be accessable without removing the snow.
        transparent.add((byte)Material.SNOW.getId());
        
        // Air is transparent.
        transparent.add((byte)Material.AIR.getId());
        
        // Glass is also transparent.
        transparent.add((byte)Material.GLASS.getId());
        
        // Return the block the player is looking at. Glass, Air, and snow that covers blocks will be ignored.
        return player.getTargetBlock(transparent, 10);
        
    }
    
    
            
}
