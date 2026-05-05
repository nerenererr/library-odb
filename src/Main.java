import dao.BookDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library.odb");

        BookDAO dao = new BookDAO(emf);

        //dao.insertBook(new Book("2666", "Roberto Bolaño", "978-8420423920", 2004, "Novel", 33.15, 13, true));
        //dao.deleteBook(11);

        int idToSearch = 3;
        System.out.println("\n🔍 SEARCHING FOR BOOK ID: [" + idToSearch + "]");
        Book b = dao.getBookById(idToSearch);
        if (b != null) {
            System.out.println(b);
        } else {
            System.out.println("❌ Error: Book not found (╯°□°）╯︵ ┻━┻");
        }

        System.out.println("\n(づ｡◕‿‿◕｡)づ  ✨ FULL LIBRARY CATALOGUE ✨");
        dao.getAllBooks().forEach(System.out::println);

        String g = "Fantasy";
        System.out.println("\n🔮 EXPLORING GENRE: " + g.toUpperCase());
        dao.getBooksByGenre(g).forEach(book ->
                System.out.println("  • " + book.getTitle() + " by " + book.getAuthor() + " ✧ﾟ")
        );

        System.out.println("\n⚠️  WAREHOUSE ALERT: CRITICAL STOCK");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        dao.getBooksLowStock().forEach(book ->
                System.out.println("  🚨 ONLY " + book.getAvailableCopies() + " COPIES LEFT: " + book.getTitle())
        );

        System.out.println("\n🆕 MODERN ERA COLLECTION (Post-2000)");
        dao.getBooksPublishedAfter2000().forEach(book ->
                System.out.println("  [Year " + book.getPublicationYear() + "] → " + book.getTitle())
        );

        String query = "García";
        System.out.println("\n✍️  WORKS BY AUTHOR MATCHING: '" + query + "'");
        dao.getBooksByAuthorContent(query).forEach(book ->
                System.out.println("  Found: " + book.getTitle() + " (Author: " + book.getAuthor() + ")")
        );

        System.out.println("\n💎 THE COLLECTOR'S SHELF (Top 5 Premium)");
        dao.getExpensiveBooks().forEach(book ->
                System.out.println("  💰 " + book.getPrice() + "€ - " + book.getTitle())
        );

        long total = dao.getDistinctBooks();
        System.out.println("\n📊 DATABASE SUMMARY");
        System.out.println("Total unique titles in collection: " + total + " books 📚");

        System.out.printf("💰 Precio medio: %.2f €\n", dao.getAvgPrice());

        System.out.println("\n📜 HISTORICAL TREASURE (Oldest Book)");
        Book oldest = dao.getOldestBook();
        System.out.println("  The classic: " + oldest.getTitle() + " [" + oldest.getPublicationYear() + "] ﾟ･: *");

        System.out.println("\n📊 BOOKS PER CATEGORY");
        dao.getCopiesByGenre().forEach((genre, count) ->
                System.out.println("  - " + genre + ": " + count + " different titles 📚")
        );

        System.out.println("\n💸 MARKET ANALYSIS: Average Price by Genre");
        dao.getAvgPriceByGenre().forEach((genre, avg) ->
                System.out.printf("  %-12s : %.2f €\n", genre, avg)
        );

        System.out.println("\n📦 MASSIVE STOCK GENRES (>100 Total Copies)");
        List<String> bigGenres = dao.getGenresWithMoreThan100Copies();
        if (bigGenres.isEmpty()) {
            System.out.println("  No massive stocks found. (・_・;)");
        } else {
            bigGenres.forEach(genre -> System.out.println("  ⭐ " + genre.toUpperCase()));
        }




        emf.close();


    }
}