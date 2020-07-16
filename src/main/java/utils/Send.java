package utils;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Send {
    public static Message reply(CommandEvent event, String message, String footer){
        EmbedBuilder em = new EmbedBuilder();
        em.setColor(Color.orange);
        em.setDescription(message);
        if(!footer.isEmpty()) em.setFooter(footer);
        return event.getTextChannel().sendMessage(em.build()).complete();
    }

    public static void reply(CommandEvent event, String message, String footer, int timeToDeletion, TimeUnit timeUnit){
        EmbedBuilder em = new EmbedBuilder();
        em.setColor(Color.orange);
        em.setDescription(message);
        if(!footer.isEmpty()) em.setFooter(footer);
        event.getTextChannel().sendMessage(em.build()).queue(v -> {
            ScheduledExecutorService ex = new ScheduledThreadPoolExecutor(15);
            ex.schedule(() -> v.delete().queue(),timeToDeletion, timeUnit);
        });
    }

    public static void error(CommandEvent event, String message, String footer) {
        EmbedBuilder em = new EmbedBuilder();
        em.setColor(Color.red);
        em.setDescription(message);
        if(!footer.isEmpty()) em.setFooter(footer);
        event.getTextChannel().sendMessage(em.build()).queue();
    }

    public static void error(CommandEvent event, String message, String footer, int timeToDeletion, TimeUnit timeUnit){
        EmbedBuilder em = new EmbedBuilder();
        em.setColor(Color.red);
        em.setDescription(message);
        if(!footer.isEmpty()) em.setFooter(footer);
        event.getTextChannel().sendMessage(em.build()).queue(v -> {
            ScheduledExecutorService ex = new ScheduledThreadPoolExecutor(15);
            ex.schedule(() -> v.delete().queue(),timeToDeletion, timeUnit);
        });
    }
}
