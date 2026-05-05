import dao.BookDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library.odb");

        BookDAO dao = new BookDAO(emf);

        //dao.insertBook(new Book("2666", "Roberto Bolaño", "978-8420423920", 2004, "Novel", 33.15, 13, true));
        //dao.deleteBook(11);

        System.out.println(dao.getBookById(3));

        System.out.println(dao.getAllBooks());

        System.out.println(dao.getBooksByGenre("Novel"));

        System.out.println(dao.getBooksLowStock());

        System.out.println(dao.getBooksPublishedAfter2000());

        System.out.println(dao.getBooksByAuthorContent("ing"));

        System.out.println(dao.getExpensiveBooks());

        System.out.println(dao.getDistinctBooks());

        System.out.println(dao.getAvgPrice());




        emf.close();


    }
}