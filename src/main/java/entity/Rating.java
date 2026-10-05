package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
    @EmbeddedId
    private RatingId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userid")
    @JoinColumn(name = "userid", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("bookid")
    @JoinColumn(name = "bookid", nullable = false)
    private Books book;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review_text", columnDefinition = "text")
    private String reviewText;

    public Rating() {}

    public RatingId getId() { return id; }
    public void setId(RatingId id) { this.id = id; }
    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
}