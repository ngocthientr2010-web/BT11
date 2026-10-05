package entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid")
    private Integer bookid;

    @Column(name = "isbn")
    private Integer isbn;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "cover_image", length = 100)
    private String coverImage;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    public Books() {}

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    // Getters & Setters
    public Integer getBookid() { return bookid; }
    public void setBookid(Integer bookid) { this.bookid = bookid; }
    public Integer getIsbn() { return isbn; }
    public void setIsbn(Integer isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }
}