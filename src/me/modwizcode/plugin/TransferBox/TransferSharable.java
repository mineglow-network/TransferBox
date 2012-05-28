/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import com.onarandombox.multiverseinventories.api.profile.PlayerProfile;
import com.onarandombox.multiverseinventories.api.share.ProfileEntry;
import com.onarandombox.multiverseinventories.api.share.Sharable;
import com.onarandombox.multiverseinventories.api.share.SharableHandler;
import com.onarandombox.multiverseinventories.api.share.SharableSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferSharable  {

    public static final Sharable<Double> TB = new Sharable.Builder<Double>("transferbox", Double.class,
            new SharableHandler<Double>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(TB, 0.0d);
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Double money = profile.get(TB);
                    
                    if (money == null) {
                        money = 1.0d;
                        return false;
                    }
                    Bukkit.getLogger().info("hello 2.");
                    return true;
                }
            }).stringSerializer(new ProfileEntry(false, "transferbox")).altName("tb").build();
    

    
}
