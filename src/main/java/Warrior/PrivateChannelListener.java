package Warrior;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class PrivateChannelListener extends ListenerAdapter {
    String user = "";

    public void onMessageReceived(MessageReceivedEvent event) {
        ArrayList<String> modsIDs = new ArrayList<>();
        modsIDs.add("169122787099672577");
        if(event.getAuthor().isBot()){
            return;
        }

        if(!event.getChannelType().isGuild()){
            String idUser = event.getAuthor().getId();
            if(modsIDs.contains(idUser)){
                String s = event.getMessage().getContentRaw();
                event.getJDA().openPrivateChannelById(user).queue(v -> {
                    v.sendMessage(event.getAuthor().getName() + ": " + s).queue();
                });
            }
            else{
                String s = event.getMessage().getContentRaw();
                event.getJDA().openPrivateChannelById(modsIDs.get(0)).queue(v -> {
                    v.sendMessage(event.getAuthor().getName() + ": " + s).queue();
                });
                user = idUser;
            }
        }
    }
}