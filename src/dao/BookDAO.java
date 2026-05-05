package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import models.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Book> getBooksByGenre(String genre) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.genre = :genreParam", Book.class);
        query.setParameter("genreParam", genre);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<Book> getBooksLowStock() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.availableCopies < 3 ", Book.class);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<Book> getBooksPublishedAfter2000() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.publicationYear >= 2000", Book.class);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<Book> getBooksByAuthorContent(String text) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author LIKE :textParam", Book.class);
        query.setParameter("textParam", "%" + text + "%");
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<Book> getExpensiveBooks() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.price DESC", Book.class);
        query.setMaxResults(5);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public long getDistinctBooks() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        long totalBooks = query.getSingleResult();
        em.close();
        return totalBooks;
    }

    public Double getAvgPrice() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Double> query = em.createQuery("SELECT AVG(b.price) FROM Book b", Double.class);
        double avgPrice = query.getSingleResult();
        em.close();
        return avgPrice;
    }

    public Book getOldestBook() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.publicationYear ASC", Book.class);
        query.setMaxResults(1);
        Book oldestBook = query.getSingleResult();
        em.close();
        return oldestBook;
    }

    public Map<String, Long> getCopiesByGenre() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("SELECT b.genre, COUNT(b) FROM Book b GROUP BY b.genre", Object[].class);

        List<Object[]> results = query.getResultList();
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : results) {
            String genre = (String) row[0];
            Long count = (Long) row[1];
            map.put(genre, count);
        }
        em.close();
        return map;
    }

    public Map<String, Double> getAvgPriceByGenre() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("SELECT b.genre, AVG(b.price) FROM Book b GROUP BY b.genre", Object[].class);

        List<Object[]> results = query.getResultList();
        Map<String, Double> map = new HashMap<>();
        for (Object[] row : results) {
            String genre = (String) row[0];
            Double avgPrice = (Double) row[1];
            map.put(genre, avgPrice);
        }
        em.close();
        return map;
    }

    public List<String> getGenresWithMoreThan100Copies() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<String> query = em.createQuery("SELECT b.genre FROM Book b GROUP BY b.genre HAVING SUM(b.availableCopies) > 100",
                String.class);

        List<String> books = query.getResultList();
        em.close();
        return books;
    }


}