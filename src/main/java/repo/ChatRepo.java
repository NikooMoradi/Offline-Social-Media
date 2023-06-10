package repo;

import model.User;
import model.chat.Chat;
import model.chat.Members;
import model.chat.Message;
import model.chat.MessageReceiver;
import org.hibernate.Session;
import util.ChatType;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChatRepo {

    private EntityManager entity;

    public Chat getById(Long chatId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();
            return entity.find(Chat.class, chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean createChat(User owner, List<User> members, ChatType type, String groupName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();
            Chat chat = new Chat();
            chat.setCreateDate(new Date());
            chat.setOwner(entity.find(User.class, owner.getId()));
            chat.setType(type);
            if (type == ChatType.Group)
                chat.setChatName(groupName);
            else
                chat.setChatName(owner.getUsername() + " / " + members.get(1).getUsername());
            entity.persist(chat);

            for (User user : members) {
                Members member = new Members();
                member.setChat(chat);
                member.setMember(entity.find(User.class, user.getId()));
                entity.persist(member);
            }

            entity.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Chat updateChat(Long chatId, List<User> members, String groupName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();
            Chat chat = entity.find(Chat.class, chatId);
            chat.setChatName(groupName);
            entity.persist(chat);

            entity.flush();

            List<Members> membersList = getAllMembers(chat);

            for (Members member : membersList) {
                entity.remove(entity.find(Members.class, member.getId()));
            }

            for (User user : members) {
                Members member = new Members();
                member.setChat(chat);
                member.setMember(entity.find(User.class, user.getId()));
                entity.persist(member);
            }
            entity.getTransaction().commit();

            chat = entity.find(Chat.class , chatId);

            return chat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Chat> getAllUserChat(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            return entity.createQuery("select m.chat from Members m where m.Member =: user", Chat.class)
                    .setParameter("user", entity.find(User.class, user.getId())).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Chat> getAllUserChatByType(User user, ChatType type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            return entity.createQuery("select m.chat from Members m where m.Member =: user and m.chat.type =: type", Chat.class)
                    .setParameter("user", entity.find(User.class, user.getId()))
                    .setParameter("type", type)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean addMembers(Chat chat, List<User> members) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            for (User user : members) {
                Members member = new Members();
                member.setChat(entity.find(Chat.class, chat.getId()));
                member.setMember(entity.find(User.class, user.getId()));
                entity.persist(member);
            }

            entity.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Members> getAllMembers(Chat chat) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();
            return entity.createQuery("select m from Members m where m.chat =: chat", Members.class)
                    .setParameter("chat", entity.find(Chat.class, chat.getId()))
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getAllMessages(Chat chat) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            List<Message> messages = entity.createQuery("select m from Message m where m.chat =: chat order by m.sendDate asc")
                    .setParameter("chat", entity.find(Chat.class, chat.getId()))
                    .getResultList();

            for (Message message : messages) {
                MessageReceiver receiver = null;
                try {
                    receiver = entity.createQuery("select r from MessageReceiver r where r.message =: message and r.receiver =:user and r.received =: received", MessageReceiver.class)
                            .setParameter("message", message)
                            .setParameter("received", false)
                            .setParameter("user", entity.find(User.class, Repository.currentUser.getId())).getSingleResult();

                } catch (Exception e) {
                    if (Objects.isNull(receiver)) {
                        receiver = new MessageReceiver();
                    }
                }
                if (!Objects.isNull(receiver)) {
                    receiver.setReceived(true);
                    receiver.setReceiverDate(new Date());

                }
            }

            entity.getTransaction().commit();

            return messages;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Boolean sendMessage(Chat chat, String msg) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            Message message = new Message();
            message.setMessage(msg);
            message.setChat(entity.find(Chat.class, chat.getId()));
            message.setSendDate(new Date());
            message.setSender(entity.find(User.class, Repository.currentUser.getId()));
            entity.persist(message);

            List<User> members = entity.createQuery("select m.Member from Members m where m.chat =: chat")
                    .setParameter("chat", entity.find(Chat.class, chat.getId()))
                    .getResultList();

            for (User user : members) {
                MessageReceiver receiver = new MessageReceiver();
                receiver.setReceiver(user);
                receiver.setMessage(message);
                receiver.setReceived(false);
                entity.persist(receiver);
            }

            entity.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Chat> findSingleChatByMemberAndMember(User firstUser, User secondUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();
            List<String> names = new ArrayList<>();
            names.add(firstUser.getUsername() + " / " + secondUser.getUsername());
            names.add(secondUser.getUsername() + " / " + firstUser.getUsername());
            return entity.createQuery("select c from Chat c where c.chatName in : names", Chat.class)
                    .setParameter("names", names)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUnreadMessagesCount(Chat chat, User user) {

        int count = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            List<Message> messages = entity.createQuery("select m from Message m where m.chat =: chat", Message.class)
                    .setParameter("chat", entity.find(Chat.class, chat.getId()))
                    .getResultList();

            for (Message message : messages){
                count = count + entity.createQuery("select mr from MessageReceiver mr where mr.message =: message and mr.receiver =: user and mr.received =: received" , MessageReceiver.class)
                        .setParameter("message" , message)
                        .setParameter("user" , entity.find(User.class , user.getId()))
                        .setParameter("received" , false).getResultList().size();
            }

            return count;
        } catch(NullPointerException e){
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        }
    }

    public String getNameOfSingleChat(Chat chat) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();
            User user = entity.createQuery("select m.Member from Members m where m.chat =: chat and m.Member not in : member" , User.class)
                    .setParameter("chat", entity.find(Chat.class, chat.getId()))
                    .setParameter("member", entity.find(User.class, Repository.currentUser.getId())).getSingleResult();

            return user.getUsername();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean leavingChat(User user, Chat chat) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            Members member = entity.createQuery("select m from Members m where m.Member =:user and m.chat =: chat", Members.class)
                    .setParameter("user", entity.find(User.class, user.getId()))
                    .setParameter("chat", entity.find(Chat.class, chat.getId())).getSingleResult();

            entity.remove(member);

            entity.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deleteChat(Long chatId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            List<Members> membersList = getAllMembers(entity.find(Chat.class , chatId));

            for (Members members : membersList)
                entity.remove(members);

            List<Message> messages = entity.createQuery("select m from Message m where m.chat =: chat", Message.class)
                    .setParameter("chat", entity.find(Chat.class , chatId)).getResultList();

            for (Message message : messages) {
                List<MessageReceiver> receivers = entity.createQuery("select r from MessageReceiver r where r.message =: message", MessageReceiver.class)
                        .setParameter("message", message).getResultList();

                for (MessageReceiver receiver : receivers)
                    entity.remove(receiver);

                entity.remove(message);
            }

            entity.flush();

            entity.remove(entity.find(Chat.class , chatId));

            entity.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateMessage(Message message , String text){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.getEntityManagerFactory().createEntityManager();
            entity.getTransaction().begin();

            Message changed = entity.find(Message.class , message.getId());
            changed.setMessage(text);

            entity.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
