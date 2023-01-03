package homepage;

import backend.UserDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Signup{
    UserDetails user;
    public Signup(UserDetails user){
        this.user = user;
    }
    public void signup() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your fullname");
        String fullname = br.readLine();
        System.out.println("Enter your age");
        int age = Integer.parseInt(br.readLine());
        String username;
        while (true) {
            System.out.println("Enter your unique username");
            username = br.readLine();//check if username is unique
            if(user.checkUser(username)){
                System.out.println("Username exists! Try another one");
            }
            else break;
        }
        System.out.println("Enter your password");
        String userpass = br.readLine();

        user.createUser(fullname,age,username,userpass);

    }
}
