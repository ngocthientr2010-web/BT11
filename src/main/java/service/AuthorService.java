package service;

import dao.AuthorDao;
import entity.Author;

import java.util.List;

public class AuthorService {
    private static final int PAGE_SIZE = 6; // yêu cầu: 6 tác giả/trang

    private AuthorDao authorDAO = new AuthorDao();

    public List<Author> getAuthorsByPage(int page) {
        if (page < 1) page = 1;
        return authorDAO.findByPage(page, PAGE_SIZE);
    }

    public int getTotalPages() {
        long total = authorDAO.countAll();
        return (int) Math.ceil((double) total / PAGE_SIZE);
    }

    public Author getById(Integer id) {
        return authorDAO.findById(id);
    }

    public void save(Author author24110336) {
        authorDAO.save(author24110336);
    }

    public void delete(Integer id) {
        authorDAO.delete(id);
    }
}