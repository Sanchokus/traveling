package ru.aolisov.traveling.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Alex on 3/19/2016.
 */
@NoRepositoryBean
@Transactional
public interface EntityRepository<T extends Serializable>  extends PagingAndSortingRepository<T, Long> {
    T findByName(String name);
    void deleteByName(String name);
}
