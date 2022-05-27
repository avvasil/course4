package ru.hogwarts.school.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Avatar;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long>, JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

//    List<Avatar> findAll(PageRequest pageRequest);
}
