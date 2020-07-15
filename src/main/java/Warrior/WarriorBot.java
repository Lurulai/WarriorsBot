package Warrior;

import cattos.SubClass;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.CreateCatto;
import commands.Profile;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.log4j.BasicConfigurator;
import utils.Constants;

import javax.security.auth.login.LoginException;

/**
 * WarriorBot class which is the main class, contains only initialization of main processes used
 * in the program and such.
 */
public class WarriorBot {
    // A global variable because we may need to use it in different methods or classes
    private static JDA jda;

    /**
     * Main method, initializes the jda process (the bot) using the token from Constant class
     * @param args - argument from the command line
     * @throws InterruptedException - exception thrown when the bot waiting for Connection is interrupted
     * @throws LoginException - exception thrown when the bot cannot login properly
     */
    public static void main(String[] args) throws InterruptedException, LoginException {
        // EventWaiter is used for an event to happen, like asking a question and waiting for a response
        EventWaiter waiter = new EventWaiter();

        // Initialize loggers
        BasicConfigurator.configure();

        // Initializing the bot, setting status, and adding commands
        jda = JDABuilder.createDefault(Constants.CLIENT_SECRET_CODE)
                .addEventListeners(commandClient(waiter).build(), waiter, new PrivateChannelListener())
                .setActivity(Activity.of(Activity.ActivityType.DEFAULT, "Booting up..."))
                .setStatus(OnlineStatus.ONLINE).build();

        // When bot disconnects for whatever reason, attempt to reconnect automatically
        jda.setAutoReconnect(true);

        // Wait for the bot to be connected entirely
        jda.awaitStatus(JDA.Status.CONNECTED);

        // Initialize subclasses
        SubClass.initializeAllSubclasses();
    }

    /**
     * Command builder to make all the commands. This is like a framework so that you can initialize
     * all of the commands and setup the bot, like it's prefix, it's owners/co-owners, it's invite link,
     * and initialize all the commands.
     * @param waiter - a EventWaiter class that is used to await for responses or events to occur
     * @return CommandClientBuilder
     */
    private static CommandClientBuilder commandClient(EventWaiter waiter) {
        CommandClientBuilder client = new CommandClientBuilder();
        client.setPrefix(Constants.D_PREFIX)
                .setOwnerId(Constants.BOT_OWNER_ID)
                .setCoOwnerIds(Constants.CO_OWNER_IDS)
                .setActivity(Activity.streaming("cats", "https://www.youtube.com/watch?v=0spYbvgklFc"))
                .setServerInvite(Constants.SERVER_INVITE)
                .setEmojis(Constants.SUCCESS, Constants.WARNING, Constants.ERROR)
                .useHelpBuilder(false)
                .addCommands(
                        // Add commands here
                        new CreateCatto(waiter),
                        new Profile()
                );
        System.out.println("Module loaded");

        return client;
    }

    /**
     * A getter method to get the jda instance (in-case we use it)
     * JDA is like the main-main boi for discord and has all the information that it receives from
     * discord.
     * @return JDA
     */
    public static JDA getJda() {
        return jda;
    }
}
