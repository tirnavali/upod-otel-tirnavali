package ui;

import api.dao.model.Customer;
import api.service.contract.UserService;
import api.service.impl.CustomerServiceImpl;
import api.service.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var userService = new UserServiceImpl();
        var customerService = new CustomerServiceImpl();
        System.out.println("Otel yönetim sistemi v.01");
        System.out.println("E posta adresinizi giriniz: ");
        Scanner scanner = new Scanner(System.in);
        var mail = scanner.next();
        System.out.println("Şifrenizi giriniz: ");
        var pass = scanner.next();

        if(userService.checkSignIn(mail, pass)){
            System.out.println("Welcome to the application as a system user!");
        } else if(customerService.checkSignIn(mail, pass)){
            System.out.println("Welcome to the application dear visitor!");
        } else {
            System.out.println("You are not authorized user!");
        };



    }
}
