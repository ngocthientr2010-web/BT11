package service;

import entity.Books;

// Gói thông tin sách + tác giả + số lượng review để hiển thị ở trang home
public class BookDisplayDTO {
    private Books book;
    private String authorNames;
    private long reviewCount;

    public BookDisplayDTO(Books book, String authorNames, long reviewCount) {
        this.book = book;
        this.authorNames = authorNames;
        this.reviewCount = reviewCount;
    }

    public Books getBook() { return book; }
    public String getAuthorNames() { return authorNames; }
    public long getReviewCount() { return reviewCount; }
}