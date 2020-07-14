package Warrior;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import utils.Constants;

import javax.security.auth.login.LoginException;

public class WarriorBot {
    private static JDA jda;

    /**
     * Main method, initializes the jda process (the bot) using the token from Constant class
     * @param args
     * @throws InterruptedException
     * @throws LoginException
     */
    public static void main(String[] args) throws InterruptedException, LoginException {
        // EventWaiter is used for an event to happen, like asking a question and waiting for a response
        EventWaiter waiter = new EventWaiter();

        // Initializing the bot, setting status, and adding commands
        jda = new JDABuilder(AccountType.BOT)
                .setToken(Constants.CLIENT_SECRET_CODE)
                .addEventListeners(commandClient(waiter).build(), waiter)
                .setStatus(OnlineStatus.DO_NOT_DISTURB).build();

        // When bot disconnects for whatever reason, attempt to reconnect automatically
        jda.setAutoReconnect(true);

        // Set the status text
        jda.getPresence().setActivity(Activity.watching("TYPE WHAT YOU WANT THE STATUS TO BE"));

        // Wait for the bot to be connected entirely
        jda.awaitStatus(JDA.Status.CONNECTED);
    }

    /**
     * Command builder to make all the commands. This is like a framework so that you can initialize
     * all of the commands and setup the bot, like it's prefix, it's owners/co-owners, it's invite link,
     * and initialize all the commands.
     * @param waiter
     * @return
     */
    private static CommandClientBuilder commandClient(EventWaiter waiter) {
        CommandClientBuilder client = new CommandClientBuilder();
        client.setPrefix(Constants.D_PREFIX)
                .setOwnerId(Constants.BOT_OWNER_ID)
                .setCoOwnerIds(Constants.CO_OWNER_IDS)
                .setServerInvite(Constants.SERVER_INVITE)
                .setEmojis(Constants.SUCCESS, Constants.WARNING, Constants.ERROR)
                .useHelpBuilder(false)
                .addCommands(
                        // Add commands here
                );
        System.out.println("Module loaded");

        return client;
    }

    /**
     * A getter method to get the jda instance (in-case we use it)
     * JDA is like the main-main boi for discord and has all the information that it receives from
     * discord.
     * @return
     */
    public static JDA getJda() {
        return jda;
    }
}
