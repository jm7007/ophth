package jm.ophthalmic.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.News;

public interface NewsRepository {
    News save(News news);
    Optional<News> findById(int id);
    List<News> findAll() throws ClassNotFoundException, SQLException;
    void clearStore();
}
