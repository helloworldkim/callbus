package com.example.callbus.repository;

import com.example.callbus.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {

    @Query("select l from BoardLike l where l.board.id = :boardId and l.communityUser.id = (select c.id from CommunityUser c where c.accountId = :accountId)")
    Optional<BoardLike> findBoardLike(@Param("boardId") Long boardId, @Param("accountId") String accountId);
}
