package no.asmadsen.exam.service;

import no.asmadsen.exam.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RatingService {
    @Autowired
    private EntityManager em;

    public List<Rating> findByUser(UUID userId) {
        return em.createNamedQuery(Rating.FIND_ALL_BY_USER_ID, Rating.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Rating> findByMovie(UUID movieId) {
        return em.createNamedQuery(Rating.FIND_ALL_BY_MOVIE_ID, Rating.class)
                 .setParameter("movieId", movieId)
                 .getResultList();
    }

    public int countReviewsByUser(UUID userId) {
        return findByUser(userId).size();
    }
}
