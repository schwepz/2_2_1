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

      userService.cleanUsersTable();
      userService.cleanCarsTable();


      User user;

      user = new User("User1", "Lastname1", "user1@mail.ru");
      user.setCar(new Car(2005, "Renault Kaptur"));
      userService.add(user);

      user = new User("User2", "Lastname2", "user2@mail.ru");
      user.setCar(new Car(8, "Chery Tiggo"));
      userService.add(user);

      user = new User("User3", "Lastname3", "user3@mail.ru");
      user.setCar(new Car(2020, "Toyota RAV4"));
      userService.add(user);

      user = new User("User4", "Lastname4", "user4@mail.ru");
      user.setCar(new Car(200, "Mitsubishi L200"));
      userService.add(user);


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
