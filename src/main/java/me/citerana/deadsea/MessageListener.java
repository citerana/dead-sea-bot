package me.citerana.deadsea;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.time.Duration;
import java.time.OffsetDateTime;

public class MessageListener extends ListenerAdapter {

    public static void main(String[] args) {
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken("token") //TODO: create config file to populate
                    .addEventListener(new MessageListener())
                    .buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            //Due to the fact that buildBlocking is a blocking method, one which waits until JDA is fully loaded,
            // the waiting can be interrupted. This is the exception that would fire in that situation.
            e.printStackTrace();
        }
    }

    /**
     * Overrides hook method in ListenerAdapter class.
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        long responseNumber = event.getResponseNumber();

        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();

        String msg = message.getContentDisplay();

        boolean isBot = author.isBot();

        if (event.isFromType(ChannelType.TEXT)) {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();

            String name;
            if (message.isWebhookMessage()) {
                name = author.getName();
            } else {
                name = member.getEffectiveName();
            }

            System.out.printf("(%s)[%s]<%s>: %s\n", guild.getName(), textChannel.getName(), name, msg);
        } else if (event.isFromType(ChannelType.PRIVATE)) {
            PrivateChannel privateChannel = event.getPrivateChannel();
        }

        if (msg.equals("!ping")) {
            OffsetDateTime creationTime = message.getCreationTime();
            OffsetDateTime receivedTime = OffsetDateTime.now();
            Duration d = Duration.between(creationTime, receivedTime);

            long timeElapsed = Math.abs(d.getSeconds()/1000);

            channel.sendMessage("Pong! (" + timeElapsed + "ms)").queue();
        } else if (msg.equals("!hi")) {
            channel.sendMessage("Hewwo World!").queue();
        }
    }
}
