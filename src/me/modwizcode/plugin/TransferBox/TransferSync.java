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
        Map<World,List<WorldGroupProfile>> worldProfiles =  new HashMap<World,List<WorldGroupProfile>>();
        for (World i: worlds) {
            List<WorldGroupProfile> profiles = getMultiInv().getGroupManager().getGroupsForWorld(i.getName());
            worldProfiles.put(i, profiles);
        }
        
        Map<String,Set<String>> profiles = new HashMap<String,Set<String>>();
        List<String> worldNames= new ArrayList<String>();
        for (World i: worlds) {
            List<WorldGroupProfile> temp = worldProfiles.get(i);
            for (WorldGroupProfile j: temp) {
                
                if (!profiles.containsKey(j.getName())) {
                    
                    if (j.isSharing(TransferSharable.TB)) {
                        profiles.put(j.getName(), worldList);
                        Set<String> worldList = j.getWorlds();
                    }
                }
            }
        }
        for (World i: worlds) {
            worldNames.add(i.getName());
        }
        for (String i: profiles.keySet()) {
            
            if (profiles.get(i).containsAll(worldNames)) {
                return true;
            }
        }
        
       
        return false;
    }
}
