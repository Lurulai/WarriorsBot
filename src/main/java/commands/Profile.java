package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import utils.Constants;
import utils.EmbedPaginator;

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
                .setFinalAction(m -> m.delete().queue())
                .waitOnSinglePage(false)
                .wrapPageEnds(true)
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES)
                .allowTextInput(false);
    }

    @Override
    protected void execute(CommandEvent event) {
        User u = CreateCatto.registeredUsers.stream().filter(v -> v.getUserID().equals(event.getAuthor().getId())).findFirst().orElse(null);
        if(u == null){
            event.getTextChannel().sendMessage("Ha sucker, you don't have cattos").queue();
            return;
        }

        MessageEmbed[] embeds = new MessageEmbed[u.getCattos().size()];
        int i = 0;
        for(Catto c : u.getCattos()){
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle(event.getMember().getEffectiveName() + "'s Cats");
            if(!c.getDescription().isEmpty()) em.setDescription(c.getDescription());
            em.setColor(Color.orange);
            em.addField("Name", c.getName(), true);
            em.addField("Age", ""+c.getAge()+" moons", true);
            em.addField("Age Frozen", (c.getAgeFrozen() ? "Yes" : "No"), true);
            if(!c.getImageUrl().isEmpty()) em.setThumbnail(c.getImageUrl());
            switch(c.getCattoClass().toLowerCase()){
                case "warrior":
                    Warrior w = (Warrior) c;
                    em.addField("Health", ""+w.getHealth(), true);
                    em.addField("Class", w.getCattoClass(), true);
                    if(!w.getSubClass().isEmpty()) em.addField("Subclass", w.getSubClass(), true);
                    break;
                case "apprentice":
                    Apprentice a = (Apprentice) c;
                    em.addField("Health", ""+a.getHealth(), true);
                    em.addField("Class", a.getCattoClass(), true);
                    if(!a.getSubClass().isEmpty()) em.addField("Subclass", a.getSubClass(), true);
                    break;
                case "misc":
                    Misc m = (Misc) c;
                    em.addField("Health", ""+m.getHealth(), true);
                    em.addField("Class", m.getCattoClass(), true);
                    if(!m.getSubClass().isEmpty()) em.addField("Subclass", m.getSubClass(), true);
                    break;
                case "medicine":
                    Medicine med = (Medicine) c;
                    em.addField("Health", ""+med.getHealth(), true);
                    em.addField("Class", med.getCattoClass(), true);
                    if(!med.getSubClass().isEmpty()) em.addField("Subclass", med.getSubClass(), true);
                    break;
                case "kit":
                    Kit k = (Kit) c;
                    em.addField("Health", ""+k.getHealth(), true);
                    em.addField("Class", k.getCattoClass(), true);
                    break;
            }
            embeds[i] = em.build();
            i++;
        }

        builder.addItems(embeds);
        builder.setUsers(event.getAuthor(), event.getGuild().getMemberById(Constants.BOT_OWNER_ID).getUser(), event.getGuild().getMemberById(Constants.CO_OWNER_IDS[0]).getUser());
        builder.setText(event.getMember().getAsMention() + ", your cats.");
        builder.build().display(event.getTextChannel());

        event.getMessage().delete().queue();
    }
}
