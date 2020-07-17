package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import readingwriting.JSONData;
import utils.Constants;
import utils.Send;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Health extends Command {
    public Health() {
        this.name = "health";
        this.aliases = new String[]{"callhealth", "hp"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        String userID;
        if(event.getMessage().getMentionedMembers().size() > 0){
            userID = event.getMessage().getMentionedMembers().get(0).getId();
        }
        else{
            userID = event.getAuthor().getId();
        }

        EmbedBuilder em = new EmbedBuilder();
        em.setTitle(":cat: Active Cats");
        em.setDescription(event.getAuthor().getAsMention() + ", your cats.");
        em.setColor(Color.orange);

        StringBuilder cattoNames = new StringBuilder();
        StringBuilder cattoHealth = new StringBuilder();

        User u = null;
        if(JSONData.registeredUsers.containsKey(event.getGuild().getId())){
            u = JSONData.registeredUsers.get(event.getGuild().getId()).stream().filter(v -> v.getUserID().equals(userID)).findFirst().orElse(null);
        }
        if(u == null){
            Send.error(event, "You don't have any cats. Create a cat by using this command:\n" +
                    "`"+ Constants.D_PREFIX+"create`", "", 10, TimeUnit.SECONDS);
            return;
        }

        for(Catto c : u.getCattos()){
            cattoNames.append(c.getName()).append("\n");
            switch(c.getCattoClass().toLowerCase()){
                case "warrior":
                    Warrior w = (Warrior) c;
                    cattoHealth.append(w.getHealth()).append("\n");
                    break;
                case "apprentice":
                    Apprentice a = (Apprentice) c;
                    cattoHealth.append(a.getHealth()).append("\n");
                    break;
                case "misc":
                    Misc m = (Misc) c;
                    cattoHealth.append(m.getHealth()).append("\n");
                    break;
                case "medicine":
                    Medicine med = (Medicine) c;
                    cattoHealth.append(med.getHealth()).append("\n");
                    break;
                case "kit":
                    Kit k = (Kit) c;
                    cattoHealth.append(k.getHealth()).append("\n");
                    break;
            }
        }

        em.addField("Names", cattoNames.toString(), true);
        em.addField("Health", cattoHealth.toString(), true);

        event.getTextChannel().sendMessage(em.build()).queue(v -> {
            ScheduledExecutorService ex = new ScheduledThreadPoolExecutor(2);
            ex.schedule(() -> v.delete().queue(),1, TimeUnit.MINUTES    );
        });

        event.getMessage().delete().queue();
    }
}
