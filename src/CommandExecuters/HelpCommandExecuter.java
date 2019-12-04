package CommandExecuters;

@CommandName("/help")
public class HelpCommandExecuter implements CommandExecuter {

    @Override
    public String Execute(String[] args) {
        return "You can use these commands: \n\r" +
                "/accountId - get your Id; \n\r" +
                "/balance - get your balance; \n\r" +
                "/transfer Amount AccountId - transfer money to another user. AccoundId - accountId another user, Amount - amount for transfer";
    }
}
