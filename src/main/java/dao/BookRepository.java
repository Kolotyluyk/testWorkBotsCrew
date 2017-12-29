package dao;

import entity.Book;
import org.hibernate.*;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookRepository {

    public void save(Book book){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
        Transaction t=session.beginTransaction();
           book.setId((Long) session.save(book));
        t.commit();
        System.out.println("add book:v"+book);
}

    public List<Book> delete(Book book) {
       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
          if(book!=null){
            session.delete(book);
            System.out.println("Book is deleted:" +book);
        }
        transaction.commit();

        return null;
    }

    public Book update(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
     if(book!=null){
            session.update(book);
            System.out.println("Book is edit to"+ book);
        }
        transaction.commit();

        return null;
    }

    public List<Book> getBooksByName(String book_name) {
        List<Book> books = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root).where(builder.equal(root.get("book_name"),book_name));
            Query<Book> q=session.createQuery(query);
            books=q.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();

            }

        }


        return books;
    }

    @SuppressWarnings("unchecked")
    public List<Book>  getBooks() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
       List<Book> list=(List<Book>) session.createQuery("from "+Book.class.getName()).list();
        session.getTransaction().commit();
        list.forEach(System.out::println);
      return list;
    }
}
