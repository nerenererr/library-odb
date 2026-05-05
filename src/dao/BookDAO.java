package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import models.Book;

import java.util.List;

public class BookDAO {
    private EntityManagerFactory emf;

    public BookDAO(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void insertBook(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();

    }

    public void deleteBook(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Book bk = em.find(Book.class, id);
        if (bk != null) {
            em.remove(bk);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Book getBookById(int id) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = query.getResultList();
        em.close();
        return books;

    }



}