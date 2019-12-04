package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Person {
    private static final String TabelName = "persons";
    
    private int Id;
    private String Name;
    private String History;
    private String Atributes;
    private String Ip;


    public static Person getPerson(int id){
        Person person = null;
        try (Connection conn = Tools.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(Tools.getQuery(TabelName, "Id", Integer.toString(id)));
            if(resultSet.next()){
                var name = resultSet.getString("Name");
                var history = resultSet.getString("History");
                var atributes = resultSet.getString("Atributes");
                var ip = resultSet.getString("Ip");

                person = new Person(id, name, history, atributes, ip);
            }

        } catch (SQLException e) {
            //TODO Сделать логирование ошибок соединения с базой
        }finally {
            return person;
        }
    }
    public static Person getPersonByUser(int userId){
        User user = User.getUser(userId);
        if (user != null && user.getPersId() != 0)
            return getPerson(user.getPersId());
        return null;
    }

    public Person(int id, String name, String history, String atribute, String ip) {
        Id = id;
        Name = name;
        History = history;
        Atributes = atribute;
        Ip = ip;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getHistory() {
        return History;
    }

    public String getAtributes() {
        return Atributes;
    }

    public String getIp() {
        return Ip;
    }
}
