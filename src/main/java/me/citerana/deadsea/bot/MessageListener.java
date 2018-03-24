package me.citerana.deadsea.bot;

import me.citerana.deadsea.bot.games.RockPaperScissors;
import me.citerana.deadsea.bot.games.TicTacToe;
import me.citerana.deadsea.tools.Configuration;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public class MessageListener extends ListenerAdapter {

    String botPrefix = "d!";

    public static void main(String[] args) {
        Configuration config = new Configuration();

        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(config.getToken())
                    .addEventListener(new MessageListener())
                    .buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // Due to the fact that buildBlocking is a blocking method, one which waits until JDA is fully loaded,
            // the waiting can be interrupted. This is the exception that would fire in that situation.
            e.printStackTrace();
        }
    }

    /**
     * Overrides hook method in ListenerAdapter class.
     */
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    JDA jda = event.getJDA();

        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();

        if (msg.startsWith(botPrefix)) {
            OffsetDateTime receivedTime = OffsetDateTime.now();
            msg = msg.substring(botPrefix.length());

            String command = msg;
            int i = msg.indexOf(' ');
            if (i >= 0) {
                command = msg.substring(0,i);
            }

            System.out.println(command);

            MessageChannel channel = event.getChannel();

            boolean isBot = author.isBot();

            if (event.isFromType(ChannelType.TEXT)) {
                // Wrapping around Text channel attributes
                Guild guild = event.getGuild();
                Member member = event.getMember();

                String name;
                if (message.isWebhookMessage()) {
                    name = author.getName();
                } else {
                    name = member.getEffectiveName();
                }

                // Content handling
                if (command.equals("ping")) {
                    OffsetDateTime creationTime = message.getCreationTime();
                    Duration d = Duration.between(creationTime, receivedTime);

                    long timeElapsed = d.toMillis();

                    channel.sendMessage("Pong! (" + timeElapsed + "ms)").queue();
                } else if (command.equals("hi")) {
                    channel.sendMessage("Hewwo World!").queue();
                } else if (command.equals("ttt")) {
                    User p1 = author;
                    List<User> users = message.getMentionedUsers();
                    System.out.println(users.size());
                    if (users.isEmpty()) {
                        channel.sendMessage("`!ttt @name` to play with someone!").queue();
                    } else {
                        TicTacToe ttt = new TicTacToe(p1, users.get(0), channel);
                        ttt.sendRules();
                        channel.sendMessage("Play in messages. Board display active.");
                    }
                } else if (command.equals("rps")) {
                    RockPaperScissors rps = new RockPaperScissors(3);
                }

                System.out.printf("(%s)[%s]<%s>: %s\n", guild.getName(), channel.getName(), name, msg);
            }
        }
    }
}
