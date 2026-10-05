package dao;

import entity.Books;
import entity.Rating;
import entity.RatingId;
import entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class RatingDao {

    // Lấy tất cả review của 1 cuốn sách
    public List<Rating> findByBookId(Integer bookid) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Rating> query = em.createQuery(
                    "SELECT r FROM Rating r WHERE r.book.bookid = :bookid", Rating.class);
            query.setParameter("bookid", bookid);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Kiểm tra user này đã review sách này chưa (tránh review trùng, vì khóa chính kép)
    public Rating findByUserAndBook(Integer userId, Integer bookId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            RatingId id = new RatingId(userId, bookId);
            return em.find(Rating.class, id);
        } finally {
            em.close();
        }
    }

    // Thêm mới hoặc cập nhật review
    public void save(Integer userId, Integer bookId, Integer ratingValue, String reviewText) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            Users user = em.find(Users.class, userId);
            Books book = em.find(Books.class, bookId);

            RatingId ratingId = new RatingId(userId, bookId);
            Rating rating = em.find(Rating.class, ratingId);

            if (rating == null) {
                rating = new Rating();
                rating.setUser(user);
                rating.setBook(book);
                rating.setId(ratingId);
            }

            rating.setRating(ratingValue);
            rating.setReviewText(reviewText);

            em.merge(rating);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}