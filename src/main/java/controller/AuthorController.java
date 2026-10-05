package controller;

import entity.Author;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AuthorService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/admin/authors")
public class AuthorController extends HttpServlet {
    private AuthorService authorService = new AuthorService();

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
                Author author24110336 = authorService.getById(id);
                showForm(req, resp, author24110336);
                break;
            case "delete":
                Integer delId = Integer.parseInt(req.getParameter("id"));
                authorService.delete(delId);
                resp.sendRedirect(req.getContextPath() + "/admin/authors");
                break;
            default:
                listAuthors(req, resp);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("authorId");
        String name = req.getParameter("authorName");
        String dobParam = req.getParameter("dateOfBirth");

        Author author24110336 = new Author();
        if (idParam != null && !idParam.isEmpty()) {
            author24110336.setAuthorId(Integer.parseInt(idParam)); // có id → update
        }
        author24110336.setAuthorName(name);

        if (dobParam != null && !dobParam.isEmpty()) {
            author24110336.setDateOfBirth(LocalDate.parse(dobParam)); // format yyyy-MM-dd từ input type="date"
        }

        authorService.save(author24110336);
        resp.sendRedirect(req.getContextPath() + "/admin/authors");
    }

    private void listAuthors(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int page = 1;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignored) {}
        }

        List<Author> author24110336s = authorService.getAuthorsByPage(page);
        int totalPages = authorService.getTotalPages();

        req.setAttribute("authors", author24110336s);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/admin/author-list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Author author24110336)
            throws ServletException, IOException {
        req.setAttribute("author", author24110336);
        req.getRequestDispatcher("/admin/author-form.jsp").forward(req, resp);
    }
}
