package CommandExecuters;

import Models.InMemoryData;
@CommandName("/balance")
public class GetBalanceCommandExecuter implements CommandExecuter {
    @Override
    public String Execute(String[] args) {
        return Double.toString(InMemoryData.Accounts.get(args[0]));
    }
}
