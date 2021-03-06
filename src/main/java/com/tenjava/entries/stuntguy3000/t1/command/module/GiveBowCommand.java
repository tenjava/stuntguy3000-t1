package com.tenjava.entries.stuntguy3000.t1.command.module;

import com.tenjava.entries.stuntguy3000.t1.FireFlight;
import com.tenjava.entries.stuntguy3000.t1.command.SubCommandModule;
import com.tenjava.entries.stuntguy3000.t1.object.Ability;
import com.tenjava.entries.stuntguy3000.t1.util.Message;
import com.tenjava.entries.stuntguy3000.t1.util.Perm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GiveBowCommand implements SubCommandModule {
    @Override
    public boolean execute(final CommandSender commandSender, final Command command, final String s, final String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (!p.hasPermission(Perm.COMMAND_GIVE)) {
                commandSender.sendMessage(Message.formulate(Message.ERROR_NO_PERMISSION));
                return true;
            }

            if (args.length < 1) {
                String abilityName = args[0];

                Ability ability = FireFlight.getInstance().getAbilityHandler().getAbilityType(abilityName);
                if (ability == null) {
                    commandSender.sendMessage(Message.formulate(Message.COMMAND_INFO_INVALID));
                } else {
                    ItemStack item = ability.buildBow();

                    if (args.length == 1) {
                        p.getInventory().addItem(item);
                    } else {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null) {
                            commandSender.sendMessage(Message.formulate(Message.ERROR_INVALID_PLAYER));
                        } else {
                            target.getInventory().addItem(item);
                        }
                    }
                }
            } else {
                p.sendMessage(Message.formulate(Message.ERROR_INVALID_SYNTAX, s, getName(), getUsage()));
            }
        } else {
            if (args.length < 2) {
                String abilityName = args[0];

                Ability ability = FireFlight.getInstance().getAbilityHandler().getAbilityType(abilityName);
                if (ability == null) {
                    commandSender.sendMessage(Message.formulateConsole(Message.COMMAND_INFO_INVALID));
                } else {
                    ItemStack item = ability.buildBow();

                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        commandSender.sendMessage(Message.formulateConsole(Message.ERROR_INVALID_PLAYER));
                    } else {
                        target.getInventory().addItem(item);
                    }
                }
            } else {
                commandSender.sendMessage(Message.formulateConsole(Message.ERROR_INVALID_SYNTAX, s, getName(), getUsage()));
            }
        }
        return true;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("g", "gb", "give", "giveb");
    }

    @Override
    public String getDescription() {
        return "Give yourself a Bow with a Ability attached to it";
    }

    @Override
    public String getName() {
        return "givebow";
    }

    @Override
    public String getUsage() {
        return "<name> [player]";
    }
}