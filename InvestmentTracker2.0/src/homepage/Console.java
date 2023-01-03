package homepage;
import backend.Portfolio;
import backend.UserDetails;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console {
    UserDetails user = new UserDetails();

    Portfolio portfolio=new Portfolio(user);;
    public void choose() throws Exception {
        boolean flag=true;
        while (flag) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("---------WELCOME TO INVESTMENT TRACKER APP--------");
            System.out.println("1.Login\n2.Signup\n3.Exit");
            int inputSelection = Integer.parseInt(br.readLine());
            switch (inputSelection) {
                case 1:
                    Login l = new Login(user);
                    l.login();

                    portfolio.addInvestment();
                    break;
                case 2:
                    Signup s = new Signup(user);
                    s.signup();

                    portfolio.addInvestment();
                    break;
                case 3:
                    flag=false;
                    System.out.println("------THANK YOU-----");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again noob!");
                    break;
            }

        }
    }

}

