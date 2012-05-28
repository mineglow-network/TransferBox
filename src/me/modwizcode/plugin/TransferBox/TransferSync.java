/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import com.onarandombox.multiverseinventories.MultiverseInventories;
import com.onarandombox.multiverseinventories.api.profile.WorldGroupProfile;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferSync {
    
    
    public static void onEnable() {
        if (hasMultiInv()) {
            
            TransferSharable.init();
            getMultiInv().reloadConfig();
        } else {
            
        }
    }
    public static void sync(String name, Inventory chest, TransferState state) {
        switch (state) {
            case OPEN:
                    chest.setContents(TransferStorage.chestInventories.get(name).getContents());
                    break;
            case CLOSE:
                    TransferStorage.chestInventories.put(name, chest);
                
        }
        
    }
    
    public static boolean hasMultiInv() {
        if (Bukkit.getPluginManager().isPluginEnabled("Multiverse-Inventories")) {
            return true;
        }
        return false;
    }
    
    public static MultiverseInventories getMultiInv() {
        return (MultiverseInventories)Bukkit.getPluginManager().getPlugin("Multiverse-Inventories");
    }
    
    private static boolean isSharing(World world,World secondWorld) {
        List<WorldGroupProfile> groups = getMultiInv().getGroupManager().getGroupsForWorld(world.getName());
        List<WorldGroupProfile> groups2 = getMultiInv().getGroupManager().getGroupsForWorld(secondWorld.getName());
        for (WorldGroupProfile i: groups) {
            for (WorldGroupProfile j: groups2) {
                if (i.equals(j) && i.isSharing(TransferSharable.TB)) {
                    return true;
                }
            }
           
        }
            
        return false;
    }
}
