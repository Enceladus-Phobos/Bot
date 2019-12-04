package Models;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private int Id;
    private String Login;
    private String Name;
    private int Role;

    private User(int id, String login, String name, int role, int persId) {
        Id = id;
        Login = login;
        Name = name;
        Role = role;
        PersId = persId;
    }

    @Nullable
    private int PersId;

    public static User getUser(String login, String password) {
        User user = null;
            try (Connection conn = Tools.getConnection()){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM kernel.users WHERE Login = '" + login + "'");
                if(resultSet.next()){
                    var pass = resultSet.getString("password");
                    if(!password.equals(pass))
                        return null;
                    var id = resultSet.getInt(1);
                    var name = resultSet.getString("Name");
                    var role = resultSet.getInt("Role");
                    var persId = resultSet.getInt("PersId");

                    user = new User(id, login, name, role, persId);
                }

            } catch (SQLException e) {
                //TODO Сделать логирование ошибок соединения с базой
            }finally {
                return user;
            }
    }

    public int getId() {
        return Id;
    }

    public String getLogin() {
        return Login;
    }

    public String getName() {
        return Name;
    }

    public int getRole() {
        return Role;
    }

    public int getPersId() {
        return PersId;
    }
}
