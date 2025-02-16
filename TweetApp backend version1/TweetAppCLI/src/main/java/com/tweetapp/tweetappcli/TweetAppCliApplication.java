package com.tweetapp.tweetappcli;

import com.tweetapp.tweetappcli.model.BaseUserTo;
import com.tweetapp.tweetappcli.model.ChangePasswordTo;
import com.tweetapp.tweetappcli.model.CreateUserTo;
import com.tweetapp.tweetappcli.model.TweetTo;
import com.tweetapp.tweetappcli.restclient.TweetAppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class TweetAppCliApplication {

    @Autowired
    TweetAppClient tweetAppClient;

    public static void main(String[] args) {
        SpringApplication.run(TweetAppCliApplication.class, args);
    }

    @PostConstruct
    public void terminal()throws IOException {
        String command;
        BaseUserTo user = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("~~~Tweet App CLI~~~");
        do {
            System.out.println("Choose one option -");
            if(user != null) {
                System.out.print("1. Post a tweet\n2. View my tweets\n3. View all tweets\n4. View all users" +
                        "\n5. Reset password\n6. Logout\n> ");
                command = br.readLine();
                switch (command) {
                    case "1" :
                        //Tweet to
                        TweetTo tweetTo = new TweetTo();
                        //input description
                        tweetTo.setUserName(user.getName());
                        System.out.print("Tweet Description : ");
                        command = br.readLine();
                        tweetTo.setDescription(command);
                        System.out.println(tweetAppClient.postTweet(tweetTo));
                        break;
                    case "2" :
                        List<TweetTo> list = tweetAppClient.getUserTweets(user.getName());
                        list.forEach(System.out::println);
                        break;
                    case "3" :
                        List<TweetTo> list1 = tweetAppClient.listAllTweets(user.getName());
                        list1.forEach(System.out::println);
                        break;
                    case "4" :
                        List<BaseUserTo> list2 = tweetAppClient.listAllUsers();
                        list2.forEach(System.out::println);
                        break;
                    case "5" :
                        //change password
                        ChangePasswordTo changePasswordTo = new ChangePasswordTo();
                        System.out.print("Old Password : ");
                        command = br.readLine();
                        if(command.length() < 5) {
                            System.out.println("Minimum password length is 5");
                            break;
                        }
                        if(!command.equals(user.getPassword())) {
                            System.out.print("Invalid credentials");
                            break;
                        }
                        System.out.print("New Password : ");
                        command = br.readLine();
                        if(command.length() < 5) {
                            System.out.println("Minimum password length is 5");
                            break;
                        }
                        changePasswordTo.setNewPassword(command);
                        changePasswordTo.setConfirmNewPassword(command);
                        changePasswordTo.setPassword(user.getPassword());
                        changePasswordTo.setName(user.getName());
                        System.out.println(tweetAppClient.changePassword(changePasswordTo));
                        break;
                    case "6" :
                        user = null;
                        System.out.println("Logged out!");
                }
            }
            else {
                System.out.print("1. Register\n2. Log In\n> ");
                command = br.readLine();
                switch (command) {
                    case "1" :
                        //create user
                        CreateUserTo createUserTo = new CreateUserTo();
                        System.out.print("User Name : ");
                        command = br.readLine();
                        createUserTo.setName(command);
                        System.out.print("Password : ");
                        command = br.readLine();
                        if(command.length() < 5) {
                            System.out.println("Minimum password length is 5");
                            break;
                        }
                        createUserTo.setPassword(command);
                        System.out.print("Confirm Password : ");
                        command = br.readLine();
                        if(command.length() < 5) {
                            System.out.println("Minimum password length is 5");
                            break;
                        }
                        if(!command.equals(createUserTo.getPassword())) {
                            System.out.println("Invalid Password. Please try again!");
                            break;
                        }
                        createUserTo.setConfirmPassword(command);
                        System.out.print("Email : ");
                        command = br.readLine();
                        createUserTo.setEmail(command);
                        System.out.print("Gender : ");
                        command = br.readLine();
                        createUserTo.setGender(command);
                        user = tweetAppClient.signUp(createUserTo);
                        if(user != null)
                            System.out.println("User signed up!");
                        else
                            System.out.println("Problem in signing up!");
                        break;
                    case "2" :
                        BaseUserTo baseUserTo = new BaseUserTo();
                        System.out.print("User Name : ");
                        command = br.readLine();
                        baseUserTo.setName(command);
                        System.out.print("Password : ");
                        command = br.readLine();
                        if(command.length() < 5) {
                            System.out.println("Minimum password length is 5");
                            break;
                        }
                        baseUserTo.setPassword(command);
                        baseUserTo.setEmail("null");
                        baseUserTo.setGender("null");
                        user = tweetAppClient.login(baseUserTo);
                        if(user != null)
                            System.out.println("User logged in!");
                        else
                            System.out.println("Invalid Credentials");
                        break;
                    case "3" : //forgot password
                }
            }
        }while(!command.equals("exit"));
    }

}
