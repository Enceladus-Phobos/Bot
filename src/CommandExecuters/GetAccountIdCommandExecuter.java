package CommandExecuters;

@CommandName("/accountId")
public class GetAccountIdCommandExecuter implements ICommandExecuter {

    @Override
    public String Execute(String[] args) {
        return args[0];
    }

}
