package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
              new Car(2005, "Renault Kaptur")));

      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
              new Car(8, "Chery Tiggo")));

      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
              new Car(2020, "Toyota RAV4")));

      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
              new Car(200, "Mitsubishi L200")));

      List<User> users = userService.listUsers();
      for (User usr : users) {
         System.out.println("Id = "+ usr.getId());
         System.out.println("First Name = "+ usr.getFirstName());
         System.out.println("Last Name = "+ usr.getLastName());
         System.out.println("Email = "+ usr.getEmail());
         System.out.println(usr.getCar());
         System.out.println();
      }
      System.out.println("The user who owns the car Mitsubishi L200: \n"
              +userService.getUserByCarModelAndSeries("Mitsubishi L200", 200));

      context.close();
   }
}
