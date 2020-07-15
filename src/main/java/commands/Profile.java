package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Profile extends Command {
    public Profile() {
        this.name = "cats";
        this.aliases = new String[]{"profile"};
//        this.category = new Category("Informative");
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder em = new EmbedBuilder();
        em.setTitle(":cat: Active Cats");
        em.setDescription(event.getAuthor().getAsMention() + ", your cats.");
        em.setColor(Color.orange);

        event.getTextChannel().sendMessage(em.build()).queue(v -> {
            ScheduledExecutorService ex = new ScheduledThreadPoolExecutor(2);
            ex.schedule(() -> v.delete().queue(),10, TimeUnit.SECONDS);
        });

        event.getMessage().delete().queue();
    }
}
