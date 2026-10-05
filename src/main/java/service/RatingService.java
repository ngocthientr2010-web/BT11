package service;

import dao.RatingDao;
import entity.Rating;

import java.util.List;

public class RatingService {
    private final RatingDao ratingDAO = new RatingDao();

    public List<Rating> getReviewsByBook(Integer bookId) {
        return ratingDAO.findByBookId(bookId);
    }

    public void addReview(Integer userId, Integer bookId, Integer ratingValue, String reviewText) {
        ratingDAO.save(userId, bookId, ratingValue, reviewText);
    }
}