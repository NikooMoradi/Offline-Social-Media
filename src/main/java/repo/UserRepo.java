package repo;

import model.Follow;
import model.Post;
import model.User;
import model.chat.Chat;
import org.hibernate.Session;
import util.ChatType;
import util.HibernateUtil;
import util.Role;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepo {

    private final PostRepo postRepo = new PostRepo();
    private final FollowRepo followRepo = new FollowRepo();
    private final ChatRepo chatRepo = new ChatRepo();

    public List<User> list() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createSQLQuery("select * from User").addEntity(User.class).list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public User get(long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public User save(long id , String username , String password , String secAnswer , Role role , byte[] userImage) {
        EntityManager entityManager = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            if (Objects.isNull(user))
                user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setSecurityAnswer(secAnswer);
            user.setRole(role);
            user.setUserImage(userImage);

            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User save(long id , String username , String password , String secAnswer , Role role) {
        EntityManager entityManager = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            if (Objects.isNull(user))
                user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setSecurityAnswer(secAnswer);
            user.setRole(role);

            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User auth(String username, String pass){
        EntityManager entityManager = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select u from User u where u.username = :username and u.password = :pass";

            return entityManager.createQuery(jpql, User.class)
                    .setParameter("username", username)
                    .setParameter("pass",pass).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public User checkSecAnswer(String username, String secAns){
        EntityManager entityManager = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select u from User u where u.username = :username and u.securityAnswer = :secAns";

            return entityManager.createQuery(jpql, User.class)
                    .setParameter("username", username)
                    .setParameter("secAns",secAns).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public List<User> findByUsernameLike(String username){
        EntityManager entityManager = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select u from User u where u.username like :username";

            return entityManager.createQuery(jpql, User.class)
                    .setParameter("username", "%" + username + "%").getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteUser(Long userId){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            //---- Delete All Posts
            List<Post> posts = entityManager.createQuery("select p from Post p where p.user =: user" , Post.class)
                    .setParameter("user" , entityManager.find(User.class , userId)).getResultList();

            for (Post post : posts)
                postRepo.delete(post.getId());

            //---- Delete All Following And Followers
            List<Follow> following = entityManager.createQuery("select f from Follow f where f.following =: user" , Follow.class)
                    .setParameter("user" , entityManager.find(User.class , userId)).getResultList();

            for (Follow follow : following)
                followRepo.deleteById(follow.getId());

            List<Follow> followers = entityManager.createQuery("select f from Follow f where f.follower =: user" , Follow.class)
                    .setParameter("user" , entityManager.find(User.class , userId)).getResultList();

            for (Follow follow : followers)
                followRepo.deleteById(follow.getId());


            //---- Delete All Chats
            List<Chat> chats = entityManager.createQuery("select m.chat from Members m where m.Member =: user" , Chat.class)
                    .setParameter("user" , entityManager.find(User.class , userId)).getResultList();

            for (Chat chat : chats){
                if(chat.getType() == ChatType.Single || chat.getOwner().equals(entityManager.find(User.class , userId)))
                    chatRepo.deleteChat(chat.getId());
                else
                    chatRepo.leavingChat(entityManager.find(User.class , userId) , chat);
            }

            entityManager.flush();

            //----- Delete User
            entityManager.remove(entityManager.find(User.class , userId));
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
