import java.util.HashMap;
import java.util.Map;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleBot extends TelegramLongPollingBot {
    private Map<Long, Double> _accounts = new HashMap();

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String response = "";
            String[] command = message_text.split(" ");
            if (!_accounts.containsKey(chat_id)) {
                _accounts.put(chat_id, 100.0D);
            }

            switch (command[0]) {
                case "/help":
                    response = "You can use these commands: \n\r/accountId - get your Id; \n\r/balance - get your balance; \n\r/transfer Amount AccountId - transfer money to another user. AccoundId - accountId another user, Amount - amount for transfer";
                    break;
                case "/accountId":
                    response = Long.toString(chat_id);
                    break;
                case "/balance":
                    response = Double.toString(_accounts.get(chat_id));
                    break;
                case "/transfer":
                    if (command.length != 3) {
                        response = "Wrong input command";
                    } else {
                        double amount;
                        try {
                            amount = Double.parseDouble(command[1]);
                        } catch (NumberFormatException e) {
                            response = "Wrong amount format";
                            break;
                        }

                        long other_id;
                        try {
                            other_id = Long.parseLong(command[2]);
                        } catch (NumberFormatException e) {
                            response = "Wrong accountId format";
                            break;
                        }

                        if (amount > _accounts.get(chat_id)) {
                            response = "No money, no honey";
                        } else if (!_accounts.containsKey(other_id)) {
                            response = "Unknown accountId";
                        } else {
                            _accounts.put(chat_id, _accounts.get(chat_id) - amount);
                            _accounts.put(other_id, _accounts.get(other_id) + amount);
                            response = "Done!";
                        }
                    }
            }

            SendMessage message = (new SendMessage()).setChatId(chat_id).setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

}


    public String getBotUsername() {
        return "STBot";
    }

    public String getBotToken() {
        return "894165359:AAEfkD_nosyskcto6hmo9F0Dt_M78JFCz0s";
    }
}
