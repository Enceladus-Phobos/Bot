package CommandExecuters;

import org.reflections.Reflections;
import java.util.Set;

public class CommandExecuterFactory {
    public static ICommandExecuter GetExecuter(String command)
    {
        Reflections reflections = new Reflections("CommandExecuters");

        Set<Class<? extends ICommandExecuter>> allClasses =
                reflections.getSubTypesOf(ICommandExecuter.class);


        switch (command) {
            case "/accountId":
                return new GetAccountIdCommandExecuter();
            case "/balance":
                return new GetBalanceCommandExecuter();
            case "/transfer":
                return new TransferCommandExecuter();
            case "/help":
            default:
                return new HelpCommandExecuter();

        }
    }
}
