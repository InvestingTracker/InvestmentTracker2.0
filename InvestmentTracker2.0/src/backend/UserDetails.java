package backend;

import database.DBenv;

import java.sql.*;
import java.util.SortedMap;

public class UserDetails {
    int user_id;
    String username;
    Connection connection;
    public UserDetails(){

    }
    public void createUser(String fullname, int age, String username, String userpassword) throws SQLException {
        connection= DBenv.getConnection();
        this.username = username;
        String sqlInsert="INSERT INTO user_detail(full_name,age,username,userpassword) VALUES('"+fullname+"',"+age+",'"+username+"',"+"'"+userpassword+"')";
        Statement statement=connection.createStatement();
        statement.execute(sqlInsert);
        setUser_id(username,statement);
//        System.out.println(user_id);

    }

    public boolean checkUser(String username) throws SQLException{
        connection= DBenv.getConnection();
        String sqlSelect="SELECT username FROM user_detail WHERE username='"+username+"'";
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery(sqlSelect);
        return rs.next();
    }
    public boolean auth(String username, String password) throws SQLException{
        connection=DBenv.getConnection();
        String sqlSelect = "SELECT username,userpassword FROM user_detail WHERE username='"+username+"'";
        this.username = username;
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery(sqlSelect);
        while(rs.next()){
            if(rs.getString("userpassword").equals(password)){
                setUser_id(username,statement);
                return true;
            }
            return false;
        }
        return false;
    }
    public void setUser_id(String username, Statement statement) throws SQLException{

        String sqlSelect="SELECT user_id FROM user_detail WHERE username='"+username+"'";
        ResultSet rs =statement.executeQuery(sqlSelect);
        while(rs.next()) {
            System.out.println(rs.getInt("user_id")+ "is user id. Setting now");
            this.user_id = rs.getInt("user_id");
        }
    }
}
