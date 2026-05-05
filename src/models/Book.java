package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    private String ISBN;
    private int publicationYear;
    private String genre;
    private double price;
    private int availableCopies;
    private boolean isBestSeller;


    public Book(int id, String title, String author, String ISBN, int publicationYear, String genre, double price, int availableCopies, boolean isBestSeller) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.price = price;
        this.availableCopies = availableCopies;
        this.isBestSeller = isBestSeller;
    }

    public Book(String title, String author, String ISBN, int publicationYear, String genre, double price, int availableCopies, boolean isBestSeller) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.price = price;
        this.availableCopies = availableCopies;
        this.isBestSeller = isBestSeller;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public boolean isBestSeller() {
        return isBestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    @Override
    public String toString() {
        return String.format(
                "\n  (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧  【 BOOK INFO 】  ✧ﾟ･: * \n" +
                        "  ID:               %d\n" +
                        "  Título:           %s\n" +
                        "  Autor:            %s\n" +
                        "  ISBN:             %s\n" +
                        "  Año:              %d\n" +
                        "  Género:           %s\n" +
                        "  Precio:           %.2f €\n" +
                        "  Stock:            %d unidades\n" +
                        "  Best Seller:      %s\n" +
                        "  ───────────────────────────────────",
                id, title, author, ISBN, publicationYear, genre, price, availableCopies, (isBestSeller ? "Sí ★" : "No")
        );
    }
}