import java.util.*;

import CommandExecuters.CommandExecuterFactory;
import CommandExecuters.ICommandExecuter;
import Models.InMemoryData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String response = "";

            List<String> command = Arrays.asList(message_text.split(" "));
            if (!InMemoryData.Accounts.containsKey(chat_id)) {
                InMemoryData.Accounts.put(chat_id, 100d);
            }

            ICommandExecuter executer = CommandExecuterFactory.GetExecuter(command.get(0));

            //command.remove(0);
            //command.add(0, Long.toString(chat_id));

            executer.Execute((String[]) command.toArray());

            SendMessage message = (new SendMessage()).setChatId(chat_id).setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

}


    public String getBotUsername() {
        return "Bot Buterbrod";
    }

    public String getBotToken() {
        return "979329461:AAHt7Ii2unaGXsDLTPjBZFXhjrbxXYbiNYA";
    }
}
