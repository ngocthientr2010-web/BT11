package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {
    @Column(name = "bookid")
    private Integer bookid;

    @Column(name = "author_id")
    private Integer authorId;

    public BookAuthorId() {}
    public BookAuthorId(Integer bookid, Integer authorId) {
        this.bookid = bookid;
        this.authorId = authorId;
    }

    public Integer getBookid() { return bookid; }
    public void setBookid(Integer bookid) { this.bookid = bookid; }
    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAuthorId)) return false;
        BookAuthorId that = (BookAuthorId) o;
        return Objects.equals(bookid, that.bookid) && Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() { return Objects.hash(bookid, authorId); }
}