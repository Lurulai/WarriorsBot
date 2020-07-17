package commands;

import cattos.Catto;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.ButtonMenu;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.json.JSONObject;
import readingwriting.JSONData;
import utils.Constants;
import utils.Send;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DeleteCatto extends Command {
    private EventWaiter waiter;
    /**
     * Initialize the .create command along with the event waiter
     * @param waiter event waiter class
     */
    public DeleteCatto(EventWaiter waiter) {
        this.name = "deletecat";
        this.aliases = new String[]{"delete"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
        this.waiter = waiter;
    }

    @Override
    protected void execute(CommandEvent event) {
        String catName = event.getArgs().trim();
        String guildID = event.getGuild().getId();
        String userID = event.getAuthor().getId();

        if(catName.trim().isEmpty()){
            Send.error(event, "Please specify the name of the character after the command.", "", 10, TimeUnit.SECONDS);
            return;
        }

        User u = null;
        if(JSONData.registeredUsers.containsKey(guildID)){
            u = JSONData.registeredUsers.get(guildID).stream().filter(v -> v.getUserID().equals(userID)).findFirst().orElse(null);
        }
        if(u == null || u.getCattos() == null || u.getCattos().isEmpty()){
            Send.error(event, "You don't have any cats. Create a cat by using this command:\n" +
                    "`"+ Constants.D_PREFIX+"create`", "", 10, TimeUnit.SECONDS);
            return;
        }

        ArrayList<Catto> cattos = u.getCattos();
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> index = new HashMap<>();
        int total = 0;
        for(int i = 0; i < cattos.size(); i++){
            if(cattos.get(i).getName().equalsIgnoreCase(catName)){
                total++;
                index.put(total, i);
                sb.append(CreateCatto.numberToEmoji(total)).append(" ").append(cattos.get(i).getName()).append(" ")
                        .append(" (").append(cattos.get(i).getAge()).append(") ")
                        .append(" [").append(cattos.get(i).getCattoClass()).append("]")
                        .append("\n");
            }
        }
        if(total == 0){
            Send.error(event, "You don't have any cats with the name `"+catName+"`. However, you can create one using:\n" +
                    "`"+ Constants.D_PREFIX+"create`", "", 10, TimeUnit.SECONDS);
        }
        else if(total > 1){
            new ButtonMenu.Builder()
                    .setDescription("Found multiple cats with same names. Pick one that you'd like to kill.\n"+sb.toString().trim())
                    .setChoices(CreateCatto.getEmojis(total))
                    .addChoice(EmojiManager.getForAlias("x").getUnicode())
                    .setEventWaiter(waiter)
                    .setTimeout(1, TimeUnit.MINUTES)
                    .setColor(Color.orange)
                    .setAction(v -> {
                        if (EmojiParser.parseToAliases(v.getEmoji()).equalsIgnoreCase(":x:")) {
                            Send.reply(event, event.getAuthor().getAsMention() + " Cancelled deleting cat.", "", 5, TimeUnit.SECONDS);
                        }
                        else{
                            int choice = CreateCatto.emojiToNumber(EmojiParser.parseToAliases(v.getEmoji()));
                            cattos.remove(index.get(choice).intValue());
                            Send.reply(event, "Your catto named `"+catName+"` has been deleted.", "", 10, TimeUnit.SECONDS);
                            User us = JSONData.registeredUsers.get(guildID).parallelStream().filter(uv -> uv.getUserID().equals(userID)).findFirst().orElse(null);
                            if(us != null) JSONData.updateUser(us, guildID);
                        }
                    })
                    .setFinalAction(me -> {
                        me.delete().queue();
                    }).build().display(event.getTextChannel());
        }
        else{
            int indexToRemove = -1;
            for(int j = 0; j < cattos.size(); j++){
                if(cattos.get(j).getName().equalsIgnoreCase(catName)){
                    indexToRemove = j;
                    break;
                }
            }
            if(indexToRemove != -1) cattos.remove(indexToRemove);
            Send.reply(event, "Your catto named `"+catName+"` has been deleted.", "", 10, TimeUnit.SECONDS);
        }
        event.getMessage().delete().queue();
    }
}
