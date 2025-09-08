package com.example.SmartTaskManagement.repo;

import com.example.SmartTaskManagement.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    Optional<Team> findTeamByName(String name);

    void deleteByName(String name);

    @Query("SELECT DISTINCT t FROM Team t " +
            "LEFT JOIN FETCH t.users " +
            "LEFT JOIN FETCH t.tasks")
    Page<Team> findAllWithUsersAndTasks(Pageable pageable);

    @Query("SELECT t FROM Team t " +
            "LEFT JOIN FETCH t.users " +
            "LEFT JOIN FETCH t.tasks " +
            "WHERE t.name = :name")
    Optional<Team> findByNameWithUsersAndTasks(@Param("name") String name);
}
