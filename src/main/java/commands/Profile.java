package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.Paginator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Profile extends Command {
    private final Paginator.Builder builder;

    public Profile(EventWaiter waiter) {
        this.name = "cats";
        this.aliases = new String[]{"profile"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
        builder = new Paginator.Builder()
                .setColor(Color.orange)
                .setColumns(1)
                .setFinalAction(m -> m.delete().queue())
                .setItemsPerPage(1)
                .waitOnSinglePage(false)
                .useNumberedItems(false)
                .showPageNumbers(true)
                .wrapPageEnds(true)
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES);
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder em = new EmbedBuilder();
        em.setTitle(":cat: Active Cats");
        em.setDescription(event.getAuthor().getAsMention() + ", your cats.");
        em.setColor(Color.orange);

        StringBuilder cattoNames = new StringBuilder();
        StringBuilder cattoHealth = new StringBuilder();

        User u = CreateCatto.registeredUsers.stream().filter(v -> v.getUserID().equals(event.getAuthor().getId())).findFirst().orElse(null);
        if(u == null){
            event.getTextChannel().sendMessage("Ha sucker, you don't don't have cattos").queue();
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
