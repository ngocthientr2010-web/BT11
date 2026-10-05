package dao;

import entity.Author;
import entity.Books;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class BookDao {

    public List<Books> findByPage(int page, int pageSize) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Books> query = em.createQuery(
                    "SELECT b FROM Books b ORDER BY b.bookid", Books.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public long countAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Books b", Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public long countReviews(Integer bookid) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(r) FROM Rating r WHERE r.book.bookid = :bookid", Long.class);
            query.setParameter("bookid", bookid);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Books findById(Integer bookid) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Books.class, bookid);
        } finally {
            em.close();
        }
    }

    // Thêm mới / cập nhật sách
    public void save(Books book) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            if (book.getBookid() == null) {
                em.persist(book);
            } else {
                em.merge(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Xóa sách
    public void delete(Integer bookid) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Books book = em.find(Books.class, bookid);
            if (book != null) {
                em.remove(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Lấy toàn bộ tác giả (để hiển thị checkbox chọn trong form thêm/sửa sách)
    public List<Author> findAllAuthors() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a ORDER BY a.authorName", Author.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Lấy lại danh sách Author entity (đã managed) theo danh sách id được chọn từ form
    public List<Author> findAuthorsByIds(List<Integer> ids) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Author> query = em.createQuery(
                    "SELECT a FROM Author a WHERE a.authorId IN :ids", Author.class);
            query.setParameter("ids", ids);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}