package backend;

import database.DBenv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stock {
    UserDetails user;
    Connection connection;
    public Stock(UserDetails user) {
        this.user=user;
    }

    public void start() throws IOException,SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1.Add Stock Investment\n2.Remove Stock Investment\n3.Go Back");
        int inputSelection = Integer.parseInt(br.readLine());
        switch (inputSelection){
            case 1:
                System.out.println("How much amount do you want to add ?");
                double amount = Double.parseDouble(br.readLine());
                addStockAmount(amount);
                break;
            case 2:
                System.out.println("How much amount do you want to remove? ");
                double amount1 =Double.parseDouble(br.readLine());
                removeStock(amount1);
                break;
        }
    }

    private void removeStock(double amount) throws SQLException{
        connection = DBenv.getConnection();
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT stock_amount FROM portfolio WHERE user_id=" + user.user_id;
        ResultSet rs = statement.executeQuery(selectQuery);
        double currentBalance = 0;
        while (rs.next()) currentBalance = rs.getDouble("stock_amount");
        if (currentBalance > amount) {
            String updateQuery = "UPDATE portfolio SET stock_amount=stock_amount -" + amount;
            statement.execute(updateQuery);
            System.out.println("Amount of Rs." + amount + " has been removed from your stock portfolio. Updated Amount: "+(currentBalance-amount));
        }
        else {
            String updateQuery = "UPDATE portfolio SET stock_amount=0.00" ;
            statement.execute(updateQuery);
            System.out.println("Amount has been updated. Updated Amount: 0");
        }
    }


    public void addStockAmount(double amount) throws SQLException {
        connection= DBenv.getConnection();
        Statement statement=connection.createStatement();
        String updateQuery = "UPDATE portfolio SET stock_amount=stock_amount +"+amount;
        statement.execute(updateQuery);
        System.out.println("Amount of Rs."+ amount + " has been added to your stock portfolio");

    }

}
