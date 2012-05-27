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
public class TransferBox extends JavaPlugin implements Listener{
    
    TransferCommands tc;
    @Override
    public void onEnable() {
        tc = new TransferCommands(this);
        this.getPluginLoader().createRegisteredListeners(this, new TransferListener());
    }
    
    public void onDiable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return tc.executeCommand(sender,command,label,args);
    }
}
