package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_author")
public class BookAuthor {
    @EmbeddedId
    private BookAuthorId id;

    @ManyToOne
    @MapsId("bookid")
    @JoinColumn(name = "bookid")
    private Books book;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    public BookAuthor() {}
    public BookAuthor(Books book, Author author) {
        this.book = book;
        this.author = author;
        this.id = new BookAuthorId(book.getBookid(), author.getAuthorId());
    }

    // Getters & Setters
    public BookAuthorId getId() { return id; }
    public void setId(BookAuthorId id) { this.id = id; }
    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}