
package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where car.model=:m AND car.series=:s");
      query.setParameter("m", model);
      query.setParameter("s", series);
      return query.getSingleResult();
   }

   @Override
   public void cleanUsersTable() {
      sessionFactory.getCurrentSession().createQuery("delete  User").executeUpdate();

   }

   @Override
   public void cleanCarsTable() {
      sessionFactory.getCurrentSession().createQuery("delete Car").executeUpdate();

   }

}