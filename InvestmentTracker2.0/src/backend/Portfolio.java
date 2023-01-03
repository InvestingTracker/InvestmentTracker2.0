package backend;

import database.DBenv;
import homepage.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Portfolio {
    Connection connection;
    UserDetails user;
    public Portfolio(UserDetails user){
        this.user=user;
    }


    public void addInvestment() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag=true;
        while(flag) {
            System.out.println("What do you want to do today?");
            System.out.println("1.Crypto Investment\n2.Stock Investment\n3.Fixed Deposit Investment\n4.View Portfolio\n5.Signout ");
            int inputSelection = Integer.parseInt(br.readLine());
            switch (inputSelection) {
                case 1:
                    Crypto crypto = new Crypto(user);
                    crypto.start();
                    break;
                case 2:
                    Stock stock = new Stock(user);
                    stock.start();
                    break;
                case 3:
                    FixedDeposit fixedDeposit = new FixedDeposit(user);
                    fixedDeposit.start();
                    break;
                case 4:
                    displayPortfolio();
                    break;
                case 5:
                    flag=false;
                    break;
                default:
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }

    public void displayPortfolio() throws SQLException{
        connection = DBenv.getConnection();
        System.out.println(user.user_id + " portfolio user id");
        String sqlSelect = "SELECT crypto_amount,stock_amount,fixed_deposit_amount FROM portfolio WHERE user_id="+user.user_id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlSelect);
        while (rs.next()){
            System.out.println();
            System.out.println("Hello "+user.username+"!");
            System.out.println();
            System.out.println("-----PORTFOLIO----");
            System.out.println("Crypto Investments          ---> Rs."+rs.getDouble("crypto_amount"));
            System.out.println("Stock Investments           ---> Rs."+rs.getDouble("stock_amount"));
            System.out.println("Fixed Deposit Investments   ---> Rs."+ rs.getDouble("fixed_deposit_amount"));
            System.out.println("Total Investments           ---> Rs."+(rs.getDouble("crypto_amount")+rs.getDouble("stock_amount")+rs.getDouble("fixed_deposit_amount")));
            System.out.println("------------------");
            System.out.println();
        }
    }
}
