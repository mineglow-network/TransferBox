/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.modwizcode.plugin.TransferBox;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Starbuck Johnson
 */
public class TransferBox extends JavaPlugin {
    
    TransferCommands tc;
    
    @Override
    public void onEnable() {
        tc = new TransferCommands(this);
       
        TransferSync.onEnable();
        getServer().getPluginManager().registerEvents(new TransferListener(), this);
    }
    
    public void onDiable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return tc.executeCommand(sender,command,label,args);
    }
}
