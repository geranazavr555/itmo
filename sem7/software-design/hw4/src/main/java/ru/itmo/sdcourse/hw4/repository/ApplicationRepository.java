package ru.itmo.sdcourse.hw4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ApplicationRepository<T> extends JpaRepository<T, Long> {
}
