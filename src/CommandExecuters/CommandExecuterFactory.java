package CommandExecuters;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CommandExecuterFactory {
    public static CommandExecuter GetExecuter(String command)
    {
        Reflections reflections = new Reflections("CommandExecuters");

        Set<Class<? extends CommandExecuter>> allClasses =
                reflections.getSubTypesOf(CommandExecuter.class);

        for (Class<? extends CommandExecuter> commandExecuterClass:
             allClasses ) {
            if (commandExecuterClass.getAnnotation(CommandName.class).value() == command) {
                try {
                    return commandExecuterClass.getConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return new HelpCommandExecuter();
    }
}
