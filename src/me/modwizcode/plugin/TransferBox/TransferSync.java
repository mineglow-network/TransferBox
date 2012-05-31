/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import com.onarandombox.multiverseinventories.MultiverseInventories;
import com.onarandombox.multiverseinventories.api.GroupManager;
import com.onarandombox.multiverseinventories.api.profile.WorldGroupProfile;
import java.util.*;
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
                    break;
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
    
    public static boolean isSharing(List<World> worlds) {
        // Setup a map to hold all the profiles for each provided world
        Map<World,List<WorldGroupProfile>> worldProfiles =  new HashMap<World,List<WorldGroupProfile>>();
        
        // Loop through the worlds to add them to the map.
        for (World i: worlds) {
            
            // Get profiles.
            List<WorldGroupProfile> profiles = getMultiInv().getGroupManager().getGroupsForWorld(i.getName());
            
            // Add them to the map.
            worldProfiles.put(i, profiles);
        }
        
        // Setup a map to hold the worlds in each profile group.
        Map<String,Set<String>> profiles = new HashMap<String,Set<String>>();
        
        // Setup a list to hold the world names.
        List<String> worldNames= new ArrayList<String>();
        
        //Loop through the list of worlds to retrive the profiles.
        for (World i: worlds) {
            List<WorldGroupProfile> temp = worldProfiles.get(i);

            // Loop through the profile's worlds to see if it should be added to the list of profiles and their worlds.
            for (WorldGroupProfile j: temp) {
                
                // Check if that profiles already been retrived.
                if (!profiles.containsKey(j.getName())) {
                    
                    // Make sure that this group shares the transferBox data.
                    if (j.isSharing(TransferSharable.TB)) {
                        
                        // Get all the worlds for that share.     
                        Set<String> worldList = j.getWorlds();
                        
                        // Add to the map of shares and worlds.
                        profiles.put(j.getName(), worldList);
                        
                    }
                }
            }
        }
        
        // Loop through all the worlds
        for (World i: worlds) {
            
            // Add string name to list for compare with list of worlds for each group.
            worldNames.add(i.getName());
        }
        
        // Loop through all the profiles.
        for (String i: profiles.keySet()) {
            
            // Check to make sure it contains all the worlds that we are testing.
            if (profiles.get(i).containsAll(worldNames)) {
                return true;
            }
        }
        if (!hasMultiInv()) {
            return true;
        }
       
        return false;
    }
}
