package CommandExecuters;

import Models.InMemoryData;

public class TransferCommandExecuter implements ICommandExecuter {
    @Override
    public String Execute(String[] args) {

        Long chat_id = Long.parseLong(args[0]);

        Double leftAmount = Double.parseDouble(args[1]);

        Long other_id = Long.parseLong(args[2]);

        Double amount = InMemoryData.Accounts.get(chat_id);

        if (leftAmount > amount) {
            return "No money, no honey";
        } else if (!InMemoryData.Accounts.containsKey(other_id)) {
            return  "Unknown accountId";
        } else {
            InMemoryData.Accounts.put(chat_id, InMemoryData.Accounts.get(chat_id) - amount);
            InMemoryData.Accounts.put(other_id, InMemoryData.Accounts.get(other_id) + amount);
            return "Done!";
        }
    }
}
