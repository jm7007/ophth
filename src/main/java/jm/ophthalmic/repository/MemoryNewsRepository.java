package jm.ophthalmic.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jm.ophthalmic.domain.News;

public class MemoryNewsRepository implements NewsRepository{

    private Map<Long, News> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public News save(News news) {
        news.setId(++sequence);
        store.put(news.getId(),news);
        return news;
    }

    @Override
    public Optional<News> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<News> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public News modify(Long id, News news) {
        news.setId(id);
        store.put(id,news);
        return news;
    }

    @Override
    public Long delete(Long id) {
        store.remove(id);
        return id;
    }
    @Override
    public Long storageSize() {
        return sequence;
    }
    
}
