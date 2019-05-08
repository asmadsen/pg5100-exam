package no.asmadsen.exam.repository;

import no.asmadsen.exam.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import rx.Observable;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends PagingAndSortingRepository<Movie, UUID> {
}
