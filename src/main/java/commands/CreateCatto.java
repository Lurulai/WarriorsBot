package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.ButtonMenu;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import utils.Constants;
import utils.Send;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A class that is invoked when user wants to create a cat
 */
public class CreateCatto extends Command {
    // All the unicodes of emojis needed
    private String ONE = EmojiManager.getForAlias("one").getUnicode();
    private String TWO = EmojiManager.getForAlias("two").getUnicode();
    private String THREE = EmojiManager.getForAlias("three").getUnicode();
    private String FOUR = EmojiManager.getForAlias("four").getUnicode();
    private String FIVE = EmojiManager.getForAlias("five").getUnicode();
    private String CANCEL = EmojiManager.getForAlias("x").getUnicode();
    // All the registered users (temporary until stored as a json)
    static ArrayList<User> registeredUsers = new ArrayList<>();
    // An event waiter
    private EventWaiter waiter;
    // User when created
    private User user = null;

    /**
     * Initialize the .create command along with the event waiter
     * @param event event waiter class
     */
    public CreateCatto(EventWaiter event) {
        this.name = "createcat";
        this.aliases = new String[]{"create"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
        this.waiter = event;
    }

    /**
     * Executes the command to create cat if the condition of the command is satisfied
     * @param event a command event class which wraps the messagereceivedevent
     */
    @Override
    protected void execute(CommandEvent event) {
        boolean userFound = false;
        for (User u : registeredUsers) {
            if (u.getUserID().equals(event.getAuthor().getId())) {
                user = u;
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            user = new User(event.getAuthor().getId());
        }

        if (user.getCattos().size() >= 4) {
            registeredUsers.add(user);
            event.getTextChannel().sendMessage("Hahahahaahahahahahahahahahahahahahahahahaha no").queue();
            return;
        }

        event.getMessage().delete().queue();
        Message mess = Send.reply(event,"What do you want the name to be?", "Or type \"cancel\"");
        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getAuthor().isBot(),
                e -> {
                    mess.delete().queue();
                    String name = e.getMessage().getContentRaw();
                    e.getMessage().delete().queue();
                    if(name.equalsIgnoreCase("cancel")){
                        Send.reply(event, "Cancelled creating cat.", "", 5, TimeUnit.SECONDS);
                        return;
                    }
                    Message m = Send.reply(event, "What's your cat's age?", "Or type \"cancel\"");
                    waitForAge(event, m, user, name);
                });
    }

    private void waitForAge(CommandEvent event, Message m, User user, String name) {
        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getAuthor().isBot(),
                e -> {
                    m.delete().queue();
                    int age;
                    String message = e.getMessage().getContentRaw().trim();
                    e.getMessage().delete().queue();
                    if(message.equalsIgnoreCase("cancel")){
                        Send.reply(event, "Cancelled creating cat.", "", 5, TimeUnit.SECONDS);
                        return;
                    }
                    try{
                        age = Integer.parseInt(message);
                    }catch (NumberFormatException ex){
                        Send.error(event, "Age entered isn't a valid number. Try running the command again.", "", 10, TimeUnit.SECONDS);
                        return;
                    }
                    Message me = Send.reply(event, "Type a description of your cat. (OPTIONAL, type `none` if you don't have any.)", "Or type \"cancel\"");
                    waitForCattoDescription(event, me, user, name, age);
                });
    }

    private void waitForCattoDescription(CommandEvent event, Message me, User user, String name, int age) {
        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getAuthor().isBot(),
                e -> {
                    me.delete().queue();
                    String message = e.getMessage().getContentRaw().trim();
                    e.getMessage().delete().queue();
                    if(message.equalsIgnoreCase("cancel")){
                        Send.reply(event, "Cancelled creating cat.", "", 5, TimeUnit.SECONDS);
                        return;
                    }
                    else if(message.equalsIgnoreCase("none")){
                        Message mess = Send.reply(event, "If you have a picture of your cat, please send a link. Or an attachment. (OPTIONAL, type `none` if you don't have any.)", "Or type \"cancel\"");
                        waitForCattoImage(event, mess, user, name, age, "");
                    }
                    else{
                        Message mess = Send.reply(event, "If you have a picture of your cat, please send a link. Or an attachment. (OPTIONAL, type `none` if you don't have any.)", "Or type \"cancel\"");
                        waitForCattoImage(event, mess, user, name, age, message);
                    }
                });
    }

    private void waitForCattoImage(CommandEvent event, Message m, User user, String name, int age, String description) {
        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getAuthor().isBot(),
                e -> {
                    m.delete().queue();
                    String imageLink = "";
                    String message = e.getMessage().getContentRaw().trim();
                    if(message.equalsIgnoreCase("cancel")){
                        Send.reply(event, "Cancelled creating cat.", "", 5, TimeUnit.SECONDS);
                        e.getMessage().delete().queue();
                        return;
                    }
                    try{
                        new URL(message);
                        imageLink = message;
                    } catch (MalformedURLException ex) {
                        if(!e.getMessage().getAttachments().isEmpty()){
                            Message.Attachment a = e.getMessage().getAttachments().get(0);
                            try {
                                Message attach = Objects.requireNonNull(event.getGuild().getTextChannelById("733018738479202474")).sendFile(a.downloadToFile().get(), a.getFileName()).complete();
                                imageLink = attach.getAttachments().get(0).getUrl();
                            } catch (InterruptedException | ExecutionException exc) {
                                exc.printStackTrace();
                            }
                        }
                    }

                    e.getMessage().delete().queue();
                    waitForClass(event, user, name, age, description, imageLink);
                });
    }

    private void waitForClass(CommandEvent event, User user, String name, int age, String description, String imageLink) {
        new ButtonMenu.Builder()
                .setChoices(ONE, TWO, THREE, FOUR, FIVE, CANCEL)
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES)
                .setColor(Color.orange)
                .setDescription("Pick your fighter!\n" +
                        ":one: Warrior\n" +
                        ":two: Apprentice\n" +
                        ":three: Medicine\n" +
                        ":four: Misc\n" +
                        ":five: Kit")
                .setAction(v -> {
                    String emoji = EmojiParser.parseToAliases(v.getEmoji());
                    switch (emoji.toLowerCase()) {
                        case ":one:": {
                            String classType = "warrior";
                            String subclasses = ":one: Leader\n" +
                                    ":two: Senior\n" +
                                    ":three: Regular\n" +
                                    ":four: Young";
                            waitForSubClass(event, user, name, age, description, imageLink, classType, subclasses, 4);
                            break;
                        }
                        case ":two:": {
                            String classType = "apprentice";
                            String subclasses = ":one: Senior\n" +
                                    ":two: Regular\n" +
                                    ":three: Young";
                            waitForSubClass(event, user, name, age, description, imageLink, classType, subclasses, 3);
                            break;
                        }
                        case ":three:": {
                            String classType = "medicine";
                            String subclasses = ":one: Full\n" +
                                    ":two: Apprentice";
                            waitForSubClass(event, user, name, age, description, imageLink, classType, subclasses, 2);
                            break;
                        }
                        case ":four:": {
                            String classType = "misc";
                            String subclasses = ":one: Elder\n" +
                                    ":two: Queen";
                            waitForSubClass(event, user, name, age, description, imageLink, classType, subclasses, 2);
                            break;
                        }
                        case ":five:": {
                            Kit kit = new Kit(name, description);
                            kit.setAge(age);
                            kit.setImageUrl(imageLink);
                            boolean isValid = kit.setSubClass("1");
                            if (isValid) user.addCatto(kit);
                            CreateCatto.registeredUsers.add(user);
                            printCattoProfile(event, "Kit", "", name, age, imageLink, description);
                            break;
                        }
                        case ":x:": {
                            Send.reply(event, "Cancelled creating cat.", "", 5, TimeUnit.SECONDS);
                            break;
                        }
                    }
                })
                .setFinalAction(u -> {
                    u.delete().queue();
                }).build().display(event.getTextChannel());
    }

    private void waitForSubClass(CommandEvent event, User user, String name, int age, String description, String imageLink, String classType, String subclasses, int numClasses) {
        new ButtonMenu.Builder()
                .setDescription("Pick smol fiter\n" + subclasses)
                .addChoices(getEmojis(numClasses))
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES)
                .setColor(Color.orange)
                .setAction(v -> {
                    if(EmojiParser.parseToAliases(v.getEmoji()).equalsIgnoreCase(":x:"))
                    {
                        Send.reply(event, "Cancelled creating cat.", "", 5, TimeUnit.SECONDS);
                        return;
                    }
                    switch (classType.toLowerCase()) {
                        case "warrior": {
                            Warrior warrior = new Warrior(name, description);
                            warrior.setAge(age);
                            warrior.setImageUrl(imageLink);
                            boolean isValid = warrior.setSubClass(""+emojiToNumber(EmojiParser.parseToAliases(v.getEmoji())));
                            if (isValid) user.addCatto(warrior);
                            printCattoProfile(event, "warrior", warrior.getSubClass(), name, age, imageLink, description);
                            break;
                        }
                        case "apprentice": {
                            Apprentice apprentice = new Apprentice(name, description);
                            apprentice.setAge(age);
                            apprentice.setImageUrl(imageLink);
                            boolean isValid = apprentice.setSubClass(""+emojiToNumber(EmojiParser.parseToAliases(v.getEmoji())));
                            if (isValid) user.addCatto(apprentice);
                            printCattoProfile(event, "Apprentice", apprentice.getSubClass(), name, age, imageLink, description);
                            break;
                        }
                        case "medicine": {
                            Medicine medicine = new Medicine(name, description);
                            medicine.setAge(age);
                            medicine.setImageUrl(imageLink);
                            boolean isValid = medicine.setSubClass(""+emojiToNumber(EmojiParser.parseToAliases(v.getEmoji())));
                            if (isValid) user.addCatto(medicine);
                            printCattoProfile(event, "Medicine", medicine.getSubClass(), name, age, imageLink, description);
                            break;
                        }
                        case "misc": {
                            Misc misc = new Misc(name, description);
                            misc.setAge(age);
                            misc.setImageUrl(imageLink);
                            boolean isValid = misc.setSubClass(""+emojiToNumber(EmojiParser.parseToAliases(v.getEmoji())));
                            if (isValid) user.addCatto(misc);
                            printCattoProfile(event, "Misc", misc.getSubClass(), name, age, imageLink, description);
                            break;
                        }
                    }
                })
                .setFinalAction(u -> {
                    if(user.getCattos().size() != 0 && !registeredUsers.contains(user)) {
                        CreateCatto.registeredUsers.add(user);
                    }
                    u.delete().queue();
                }).build().display(event.getTextChannel());
    }

    private void printCattoProfile(CommandEvent event, String classType, String subClass, String name, int age, String imageLink, String description) {
        EmbedBuilder em = new EmbedBuilder();
        em.setTitle(event.getMember().getEffectiveName()+"'s Cat Created");
        if(!description.isEmpty()) em.setDescription(description);
        em.setColor(Color.orange);
        em.addField("Name", name, true);
        em.addBlankField(true);
        em.addField("Age", ""+age+" moons", true);
        em.addField("Job", classType, true);
        em.addBlankField(true);
        if(!subClass.isEmpty()) em.addField("Position", subClass, true);
        if(!imageLink.isEmpty()) em.setThumbnail(imageLink);
        em.setFooter("Use "+ Constants.D_PREFIX+"cats command to check your cats.");
        event.getTextChannel().sendMessage(em.build()).queue(v -> {
            ScheduledExecutorService ex = new ScheduledThreadPoolExecutor(2);
            ex.schedule(() -> v.delete().queue(),30, TimeUnit.SECONDS);
        });
    }

    private String[] getEmojis(int numClasses) {
        ArrayList<String> emojis = new ArrayList<>();
        emojis.add(":one:");
        emojis.add(":two:");
        emojis.add(":three:");
        emojis.add(":four:");
        emojis.add(":five:");
        String[] emoji = new String[numClasses+1];
        for (int i = 0; i < numClasses; i++) {
            emoji[i] = EmojiManager.getForAlias(emojis.get(i)).getUnicode();
        }
        emoji[emoji.length-1] = CANCEL;
        return emoji;
    }

    private int emojiToNumber(String emoji) {
        switch (emoji) {
            case ":one:":
                return 1;
            case ":two:":
                return 2;
            case ":three:":
                return 3;
            case ":four:":
                return 4;
            case ":five:":
                return 5;
        }
        return 0;
    }
}
