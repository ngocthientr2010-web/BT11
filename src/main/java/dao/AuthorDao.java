package dao;

import entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class AuthorDao {

    // Lấy danh sách có phân trang
    public List<Author> findByPage(int page, int pageSize) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Author> query = em.createQuery(
                    "SELECT a FROM Author a ORDER BY a.authorId", Author.class);
            query.setFirstResult((page-1) * pageSize); // vị trí bắt đầu
            query.setMaxResults(pageSize);                // số lượng lấy ra
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Đếm tổng số author (để tính tổng số trang)
    public long countAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(a) FROM Author a", Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Author findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Author.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Author author24110336) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            if (author24110336.getAuthorId() == null) {
                em.persist(author24110336);   // thêm mới
            } else {
                em.merge(author24110336);     // cập nhật
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Author author24110336 = em.find(Author.class, id);
            if (author24110336 != null) {
                em.remove(author24110336);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}