package controller;

import entity.Author;
import entity.Books;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/books")
public class BookController extends HttpServlet {
    private BookService bookService = new BookService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showForm(req, resp, null);
                break;
            case "edit":
                Integer id = Integer.parseInt(req.getParameter("id"));
                Books book = bookService.getBookById(id);
                showForm(req, resp, book);
                break;
            case "delete":
                Integer delId = Integer.parseInt(req.getParameter("id"));
                bookService.delete(delId);
                resp.sendRedirect(req.getContextPath() + "/admin/books");
                break;
            default:
                listBooks(req, resp);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("bookid");

        Books book = new Books();
        if (idParam != null && !idParam.isEmpty()) {
            book.setBookid(Integer.parseInt(idParam));
        }

        book.setTitle(req.getParameter("title"));
        book.setPublisher(req.getParameter("publisher"));
        book.setDescription(req.getParameter("description"));
        book.setCoverImage(req.getParameter("coverImage"));

        String isbnStr = req.getParameter("isbn");
        if (isbnStr != null && !isbnStr.isEmpty()) {
            book.setIsbn(Integer.parseInt(isbnStr));
        }

        String priceStr = req.getParameter("price");
        if (priceStr != null && !priceStr.isEmpty()) {
            book.setPrice(new BigDecimal(priceStr));
        }

        String quantityStr = req.getParameter("quantity");
        if (quantityStr != null && !quantityStr.isEmpty()) {
            book.setQuantity(Integer.parseInt(quantityStr));
        }

        String publishDateStr = req.getParameter("publishDate");
        if (publishDateStr != null && !publishDateStr.isEmpty()) {
            book.setPublishDate(LocalDate.parse(publishDateStr));
        }

        // Lấy danh sách authorId được tick chọn (checkbox nhiều giá trị cùng name)
        String[] authorIdParams = req.getParameterValues("authorIds");
        List<Integer> authorIds = new ArrayList<>();
        if (authorIdParams != null) {
            for (String s : authorIdParams) {
                authorIds.add(Integer.parseInt(s));
            }
        }

        bookService.save(book, authorIds);
        resp.sendRedirect(req.getContextPath() + "/admin/books");
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int page = 1;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try { page = Integer.parseInt(pageParam); } catch (NumberFormatException ignored) {}
        }

        List<Books> books = bookService.getBooksByPage(page);
        int totalPages = bookService.getTotalPages();

        req.setAttribute("books", books);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/admin/book-list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Books book)
            throws ServletException, IOException {
        List<Author> allAuthor24110336s = bookService.getAllAuthors();

        req.setAttribute("book", book);
        req.setAttribute("allAuthors", allAuthor24110336s);
        req.getRequestDispatcher("/admin/book-form.jsp").forward(req, resp);
    }
}
