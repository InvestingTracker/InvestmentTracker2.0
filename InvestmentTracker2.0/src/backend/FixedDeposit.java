package backend;

import database.DBenv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FixedDeposit {
    Connection connection;
    UserDetails user;
    public FixedDeposit(UserDetails user){
        this.user = user;
    }

    public void start() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1.Add FD Investment\n2.Remove FD Investment\n3.Go Back");
        int inputSelection = Integer.parseInt(br.readLine());
        switch (inputSelection){
            case 1:
                System.out.println("How much amount do you want to add ?");
                double amount = Double.parseDouble(br.readLine());
                addFDAmount(amount);
                break;
            case 2:
                System.out.println("How much amount do you want to remove?");
                double amount1 =Double.parseDouble(br.readLine());
                removeFDAmount(amount1);
                break;
        }
    }
    public void addFDAmount(double amount) throws SQLException {
        connection= DBenv.getConnection();
        Statement statement=connection.createStatement();
        String updateQuery = "UPDATE portfolio SET fixed_deposit_amount=fixed_deposit_amount +"+amount;
        statement.execute(updateQuery);
        System.out.println("Amount of Rs."+ amount + " has been added to your FD portfolio");

    }
    public void removeFDAmount(double amount) throws SQLException
    {
        connection = DBenv.getConnection();
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT fixed_deposit_amount FROM portfolio WHERE user_id=" + user.user_id;
        ResultSet rs = statement.executeQuery(selectQuery);
        double currentBalance = 0;
        while (rs.next()) currentBalance = rs.getDouble("fixed_deposit_amount");
        if (currentBalance > amount) {
            String updateQuery = "UPDATE portfolio SET fixed_deposit_amount=fixed_deposit_amount -" + amount;
            statement.execute(updateQuery);
            System.out.println("Amount of Rs." + amount + " has been removed from your FD portfolio. Updated Amount: "+(currentBalance-amount));
        }
        else {
            String updateQuery = "UPDATE portfolio SET fixed_deposit_amount=0.00" ;
            statement.execute(updateQuery);
            System.out.println("Amount has been updated. Updated Amount: 0");
        }
    }
}
