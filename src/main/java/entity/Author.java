package entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "author_name", length = 100)
    private String authorName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    // CHỈ giữ đúng dòng này — không còn @OneToMany tới BookAuthor
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Books> books;

    public Author() {}

    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public List<Books> getBooks() { return books; }
    public void setBooks(List<Books> books) { this.books = books; }

    public String getFormattedDob() {
        if (dateOfBirth == null) return "";
        return dateOfBirth.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}