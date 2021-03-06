package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import readingwriting.JSONData;
import utils.Constants;
import utils.EmbedPaginator;
import utils.Send;
import warrior.WarriorBot;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Profile extends Command {
    private final EmbedPaginator.Builder builder;

    public Profile(EventWaiter waiter) {
        this.name = "cats";
        this.aliases = new String[]{"profile"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
        builder = new EmbedPaginator.Builder()
                .waitOnSinglePage(true)
                .wrapPageEnds(false)
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES)
                .allowTextInput(false)
                .setFinalAction(m -> m.delete().queue());
    }

    @Override
    protected void execute(CommandEvent event) {
        User u = null;
        if(JSONData.registeredUsers.containsKey(event.getGuild().getId())){
            u = JSONData.registeredUsers.get(event.getGuild().getId()).stream().filter(v -> v.getUserID().equals(event.getAuthor().getId())).findFirst().orElse(null);
        }
        if(u == null){
            Send.error(event, "You don't have any cats. Create a cat by using this command:\n" +
                    "`"+Constants.D_PREFIX+"create`", "", 10, TimeUnit.SECONDS);
            return;
        }

        MessageEmbed[] embeds = new MessageEmbed[u.getCattos().size()];
        int i = 0;
        for(Catto c : u.getCattos()){
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle(event.getMember().getEffectiveName() + "'s Cats");
            if(!c.getDescription().isEmpty()) em.setDescription(c.getDescription());
            em.setColor(event.getMember().getColor() != null ? event.getMember().getColor() : Color.orange);
            em.addField("Name", c.getName(), true);
            em.addField("Age", ""+c.getAge()+" moons", true);
            em.addField("Age Frozen", (c.getAgeFrozen() ? "Yes" : "No"), true);
            if(!c.getImageUrl().isEmpty()) em.setThumbnail(c.getImageUrl());
            switch(c.getCattoClass().toLowerCase()){
                case "warrior":
                    Warrior w = (Warrior) c;
                    em.addField("Health", ""+w.getHealth(), true);
                    em.addField("Job", w.getCattoClass(), true);
                    if (w.getSubClass().isEmpty()) {
                        em.addBlankField(true);
                    } else {
                        em.addField("Position", w.getSubClass(), true);
                    }
                    break;
                case "apprentice":
                    Apprentice a = (Apprentice) c;
                    em.addField("Health", ""+a.getHealth(), true);
                    em.addField("Job", a.getCattoClass(), true);
                    if (a.getSubClass().isEmpty()) {
                        em.addBlankField(true);
                    } else {
                        em.addField("Position", a.getSubClass(), true);
                    }
                    break;
                case "misc":
                    Misc m = (Misc) c;
                    em.addField("Health", ""+m.getHealth(), true);
                    em.addField("Job", m.getCattoClass(), true);
                    if (m.getSubClass().isEmpty()) {
                        em.addBlankField(true);
                    } else {
                        em.addField("Position", m.getSubClass(), true);
                    }
                    break;
                case "medicine":
                    Medicine med = (Medicine) c;
                    em.addField("Health", ""+med.getHealth(), true);
                    em.addField("Job", med.getCattoClass(), true);
                    if (med.getSubClass().isEmpty()) {
                        em.addBlankField(true);
                    } else {
                        em.addField("Position", med.getSubClass(), true);
                    }
                    break;
                case "kit":
                    Kit k = (Kit) c;
                    em.addField("Health", ""+k.getHealth(), true);
                    em.addField("Job", k.getCattoClass(), true);
                    em.addBlankField(true);
                    break;
            }
            embeds[i] = em.build();
            i++;
        }

        builder.addItems(embeds)
                .setUsers(event.getAuthor(), WarriorBot.getJda().retrieveUserById(Constants.BOT_OWNER_ID).complete(), WarriorBot.getJda().retrieveUserById(Constants.CO_OWNER_IDS[0]).complete())
                .setText(event.getMember().getAsMention() + ", your cats.")
                .build().display(event.getTextChannel());

        event.getMessage().delete().queue();
    }
}
