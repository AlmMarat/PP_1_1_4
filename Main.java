package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        
        usi.createUsersTable();
        usi.saveUser("Андрей", "Иванов", (byte) 27);
        usi.saveUser("Ольга", "Краснова", (byte) 22);
        usi.saveUser("Вадим", "Николаев", (byte) 32);
        usi.saveUser("Ирина", "Зимина", (byte) 25);
        usi.getAllUsers();
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
