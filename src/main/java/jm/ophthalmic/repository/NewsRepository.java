package jm.ophthalmic.repository;

import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.News;

public interface NewsRepository {

    News save(News news);
    Optional<News> findById(Long id);
    List<News> findAll();
    News modify(Long id, News news);
    Long delete(Long id);
    Long storageSize();
}
