package CommandExecuters;

@CommandName("/accountId")
public class GetAccountIdCommandExecuter implements CommandExecuter {

    @Override
    public String Execute(String[] args) {
        return args[0];
    }

}
