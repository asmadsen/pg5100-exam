package no.asmadsen.exam.repository;

import no.asmadsen.exam.entity.Rating;
import no.asmadsen.exam.entity.RatingId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RatingRepository extends PagingAndSortingRepository<Rating, RatingId> {
}
