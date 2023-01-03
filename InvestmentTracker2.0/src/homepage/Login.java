package homepage;

import backend.Portfolio;
import backend.UserDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Login  {
    UserDetails user;
    public Login(UserDetails user){
        this.user=user;
    }
    public void login() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("Enter your username");
            String username = br.readLine();

            if (user.checkUser(username)) {

            } else {
                System.out.println("Username does not exist. If you want to signup, select signup");
                Console console = new Console();
                console.choose();
                System.exit(-1);
            }

            System.out.println("Enter your password");
            String pass = br.readLine();
            if(user.auth(username,pass)){

                System.out.println("LOGIN SUCCESS!");
                break;
            }else{
                System.out.println("Wrong password. Try again!");
            }
        }


    }
}
