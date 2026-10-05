package controller;

import entity.Books;
import entity.Rating;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BookService;
import service.RatingService;

import java.io.IOException;
import java.util.List;

@WebServlet("/book-detail")
public class BookDetailController extends HttpServlet {
    private final BookService bookService = new BookService();
    private final RatingService ratingService = new RatingService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));

        Books book = bookService.getBookById(id);
        List<Rating> reviews = ratingService.getReviewsByBook(id);

        req.setAttribute("book", book);
        req.setAttribute("reviews", reviews);

        req.getRequestDispatcher("/book-detail.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Integer bookId = Integer.parseInt(req.getParameter("bookid"));
        String reviewText = req.getParameter("reviewText");

        Integer ratingValue = null;
        String ratingStr = req.getParameter("rating");
        if (ratingStr != null && !ratingStr.isEmpty()) {
            ratingValue = Integer.parseInt(ratingStr);
        }

        ratingService.addReview(user.getId(), bookId, ratingValue, reviewText);

        resp.sendRedirect(req.getContextPath() + "/book-detail?id=" + bookId);
    }
}
