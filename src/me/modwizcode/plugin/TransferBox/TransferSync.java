/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import com.onarandombox.multiverseinventories.MultiverseInventories;
import com.onarandombox.multiverseinventories.api.profile.PlayerProfile;
import com.onarandombox.multiverseinventories.api.profile.WorldGroupProfile;
import com.onarandombox.multiverseinventories.api.share.ProfileEntry;
import com.onarandombox.multiverseinventories.api.share.Sharable;
import com.onarandombox.multiverseinventories.api.share.SharableHandler;
import com.onarandombox.multiverseinventories.api.share.Sharables;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferSync {
    
    
    public static void onEnable() {
        if (hasMultiInv()) {
            Bukkit.getLogger().info("hello 3");
            TransferSharable.init();
            if (isSharing(Bukkit.getWorlds().get(0))) {
                Bukkit.getLogger().info("hello");
            }
        } else {
            
        }
    }
    public void sync(String name, Inventory inventory) {
        if (hasMultiInv()) {
            
        } else {
            
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
    
    private static boolean isSharing(World world) {
        List<WorldGroupProfile> groups = getMultiInv().getGroupManager().getGroupsForWorld(world.getName());
        for (WorldGroupProfile i: groups) {
            if (i.isSharing(TransferSharable.TB)) {
                return true;
            }
        }
            
        return false;
    }
}
