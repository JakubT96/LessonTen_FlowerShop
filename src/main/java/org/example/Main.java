package org.example;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection(   // připojení k databázi
                "jdbc:mysql://localhost:3306/kvetinarstvi",
                "kvetinar",
                "Podhorkou234"
        );

        connection.setAutoCommit(true);
       Statement statement = connection.createStatement();

                                        //VLOŽENÍ
        insertNewFlower(statement, "bledule", " ", " ","ano" );
        insertNewFlower(statement, "kopretina", " "," ", "ne");


                                        // AKTULIZACE NEJAKÉ INFORMACE
        String sql1 = "UPDATE flowers " +
                "SET description = 'Pozor na cibulku - obsahuje největší koncentraci jedu!' " + "WHERE name = 'bledule'  ";
        statement.executeUpdate(sql1);


                                        // SMAZÁNÍ
        String sql2 = "DELETE FROM flowers " +
                "WHERE  toxic = 'ne'";
        statement.executeUpdate(sql2);

        printAllFlowers(statement);
    }
                                        //METODA PRO VLOŽENÍ KYTEK
    private static void insertNewFlower(Statement statement, String name , String color , String description, String toxic) throws SQLException {
        int numberOfAffectedRows  = statement.executeUpdate
                ("INSERT INTO flowers (name, color , description, toxic) VALUES ('"+name+"', '"+color+"', '"+description+"', '"+toxic+"')");
    }

                                        // VÝPIS KVETINY
    private static void printAllFlowers(Statement statement) throws SQLException {
        System.out.println("Výpis květin v tabulce: \n ");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM flowers ");

        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }
    }
}