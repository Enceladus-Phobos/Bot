package CommandExecuters;

import Models.InMemoryData;

public class GetBalanceCommandExecuter implements ICommandExecuter {
    @Override
    public String Execute(String[] args) {
        return Double.toString(InMemoryData.Accounts.get(args[0]));
    }
}
