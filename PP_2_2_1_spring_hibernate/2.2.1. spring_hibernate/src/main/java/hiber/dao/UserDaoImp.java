package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   //@SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   public void deleteAllUsers() {
      List<User> users = listUsers();
      for (User user: users) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   public User findOwner(String carName, String carSeries) {
      EntityGraph<User> entityGraph = entityManager.createEntityGraph(User.class);
      entityGraph.addAttributeNodes("car");

      TypedQuery<User> query = entityManager.createQuery(
              "SELECT u FROM User u JOIN u.car c WHERE c.name = :carName AND c.series = :carSeries", User.class);
      query.setParameter("carName", carName);
      query.setParameter("carSeries", carSeries);
      query.setHint("javax.persistence.fetchgraph", entityGraph);

      return query.getSingleResult();
      /*String hql = "from Car where name = :carName and series = :carSeries";
      String hql2 = "SELECT u FROM User u JOIN FETCH u.car c WHERE c.name = :carName AND c.series = :carSeries";
      String hql3 = "SELECT c FROM User u left JOIN u.car c WHERE c.name = :carName AND c.series = :carSeries";

      Query findCarQuery = sessionFactory.getCurrentSession().createQuery(hql2)
              .setParameter("carName", carName)
              .setParameter("carSeries", carSeries);
      List<User> findCarList = findCarQuery.getResultList();
      if (!findCarList.isEmpty()) {
         User findUser1 = findCarList.get(0);
         Car findCar = findUser1.getCar();
         List<User> ListUser = listUsers();
         User findUser = ListUser.stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
         return findUser;
      }
      return null;*/
   }
}
