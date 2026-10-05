package service;

import dao.BookDao;
import entity.Author;
import entity.Books;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private static final int PAGE_SIZE = 6;

    private BookDao bookDAO = new BookDao();

    // Dùng cho trang /products (khách xem)
    public List<BookDisplayDTO> getBooksForHomePage(int page) {
        if (page < 1) page = 1;
        List<Books> books = bookDAO.findByPage(page, PAGE_SIZE);

        List<BookDisplayDTO> result = new ArrayList<>();
        for (Books b : books) {
            String authorNames = "";
            List<Author> author = b.getAuthors();
            if (author != null && !author.isEmpty()) {
                authorNames = author.stream()
                        .map(Author::getAuthorName)
                        .collect(Collectors.joining(", "));
            }
            long reviewCount = bookDAO.countReviews(b.getBookid());
            result.add(new BookDisplayDTO(b, authorNames, reviewCount));
        }
        return result;
    }

    // Dùng cho trang /admin/books (quản trị — CRUD)
    public List<Books> getBooksByPage(int page) {
        if (page < 1) page = 1;
        return bookDAO.findByPage(page, PAGE_SIZE);
    }

    public int getTotalPages() {
        long total = bookDAO.countAll();
        return (int) Math.ceil((double) total / PAGE_SIZE);
    }

    public Books getBookById(Integer id) {
        return bookDAO.findById(id);
    }

    public void save(Books book, List<Integer> authorIds) {
        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> author = bookDAO.findAuthorsByIds(authorIds);
            book.setAuthors(author);
        }
        bookDAO.save(book);
    }

    public void delete(Integer id) {
        bookDAO.delete(id);
    }

    public List<Author> getAllAuthors() {
        return bookDAO.findAllAuthors();
    }
}