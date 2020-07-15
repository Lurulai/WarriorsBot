package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.ButtonMenu;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreateCatto extends Command {
    String ONE = EmojiManager.getForAlias("one").getUnicode();
    String TWO = EmojiManager.getForAlias("two").getUnicode();
    String THREE = EmojiManager.getForAlias("three").getUnicode();
    String FOUR = EmojiManager.getForAlias("four").getUnicode();
    String FIVE = EmojiManager.getForAlias("five").getUnicode();
    public static ArrayList<User> registeredUsers = new ArrayList<>();
    private EventWaiter waiter;
    User user = null;
    String cattoClass = "", subClass = "";

    public CreateCatto(EventWaiter event) {
        this.name = "createcat";
        this.aliases = new String[]{"create"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
        this.waiter = event;
    }

    // .createcat name age class subclass

    @Override
    protected void execute(CommandEvent event) {
//        String[] args = event.getArgs().split(" ");
//        if(args.length < 4){
//            event.getTextChannel().sendMessage("Not enough arguments.").queue();
//            return;
//        }

        boolean userFound = false;
        for (User u : registeredUsers) {
            if (u.getUserID().equals(event.getAuthor().getId())) {
                user = new User(u);
                registeredUsers.remove(u);
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            user = new User(event.getAuthor().getId());
        }

        System.out.println(user.getCattos().size());
        if (user.getCattos().size() >= 4) {
            registeredUsers.add(user);
            event.getTextChannel().sendMessage("Hahahahaahahahahahahahahahahahahahahahahaha no").queue();
            return;
        }

        event.getTextChannel().sendMessage("What do you want the name to be?").queue();
        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getAuthor().isBot(),
                e -> {
                    String name = e.getMessage().getContentRaw();
                    event.getTextChannel().sendMessage("What's your cat's age?").queue();
                    waitForAge(event, user, name);
                });
    }

    private void waitForAge(CommandEvent event, User user, String name) {
        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getAuthor().isBot(),
                e -> {
                    String age = e.getMessage().getContentRaw();
                    waitForClass(event, user, name, age);
                });
    }

    private void waitForClass(CommandEvent event, User user, String name, String age) {
        new ButtonMenu.Builder()
                .setChoices(ONE, TWO, THREE, FOUR, FIVE)
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
                            waitForSubClass(event, user, name, age, classType, subclasses, 4);
                            break;
                        }
                        case ":two:": {
                            String classType = "apprentice";
                            String subclasses = ":one: Senior\n" +
                                    ":two: Regular\n" +
                                    ":three: Young";
                            waitForSubClass(event, user, name, age, classType, subclasses, 3);
                            break;
                        }
                        case ":three:": {
                            String classType = "medicine";
                            String subclasses = ":one: Full\n" +
                                    ":two: Apprentice";
                            waitForSubClass(event, user, name, age, classType, subclasses, 2);
                            break;
                        }
                        case ":four:": {
                            String classType = "misc";
                            String subclasses = ":one: Elder\n" +
                                    ":two: Queen";
                            waitForSubClass(event, user, name, age, classType, subclasses, 2);
                            break;
                        }
                        case ":five:": {
                            Kit kit = new Kit(name);
                            kit.setAge(Integer.parseInt(age));
                            boolean isValid = kit.setSubClass(1);
                            if (isValid) user.addCatto(kit);
                            CreateCatto.registeredUsers.add(user);
                            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + ", your Kit class catto with name " + name + " and age " + age + " moons has been materialized.").queue();
                            break;
                        }
                    }
                })
                .setFinalAction(u -> {
                    u.delete().queue();
                }).build().display(event.getTextChannel());
    }

    private void waitForSubClass(CommandEvent event, User user, String name, String age, String classType, String subclasses, int numClasses) {
        new ButtonMenu.Builder()
                .setDescription("Pick smol fiter\n" + subclasses)
                .addChoices(getEmojis(numClasses))
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES)
                .setColor(Color.orange)
                .setAction(v -> {
                    switch (classType.toLowerCase()) {
                        case "warrior": {
                            Warrior warrior = new Warrior(name);
                            warrior.setAge(Integer.parseInt(age));
                            cattoClass = "Warrior";
                            boolean isValid = warrior.setSubClass(emojiToNumber(v.getEmoji()));
                            if (isValid) user.addCatto(warrior);
                            break;
                        }
                        case "apprentice": {
                            Apprentice apprentice = new Apprentice(name);
                            apprentice.setAge(Integer.parseInt(age));
                            cattoClass = "Apprentice";
                            boolean isValid = apprentice.setSubClass(emojiToNumber(v.getEmoji()));
                            if (isValid) user.addCatto(apprentice);
                            break;
                        }
                        case "medicine": {
                            Medicine medicine = new Medicine(name);
                            medicine.setAge(Integer.parseInt(age));
                            cattoClass = "Medicine";
                            boolean isValid = medicine.setSubClass(emojiToNumber(v.getEmoji()));
                            if (isValid) user.addCatto(medicine);
                            break;
                        }
                        case "misc": {
                            Misc misc = new Misc(name);
                            misc.setAge(Integer.parseInt(age));
                            cattoClass = "Misc";
                            boolean isValid = misc.setSubClass(emojiToNumber(v.getEmoji()));
                            if (isValid) user.addCatto(misc);
                            break;
                        }
                    }
                })
                .setFinalAction(u -> {
                    CreateCatto.registeredUsers.add(user);
                    event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + ", your " + ((cattoClass.isEmpty()) ? "" : cattoClass +  " class ") + "catto with name " + name + " and age " + age + " moons has been materialized." ).queue();
                    u.delete().queue();
                }).build().display(event.getTextChannel());
    }

    private String[] getEmojis(int numClasses) {
        ArrayList<String> emojis = new ArrayList<>();
        emojis.add(":one:");
        emojis.add(":two:");
        emojis.add(":three:");
        emojis.add(":four:");
        emojis.add(":five:");
        String[] emoji = new String[numClasses];
        for (int i = 0; i < numClasses; i++) {
            emoji[i] = EmojiManager.getForAlias(emojis.get(i)).getUnicode();
        }
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
