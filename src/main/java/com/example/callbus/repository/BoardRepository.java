package com.example.callbus.repository;

import com.example.callbus.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(" select b from Board b join fetch b.communityUser where b.id = :boardId")
    public Optional<Board> findBoardAndUser(@Param("boardId") Long boardId);

    @Query("select b, (select count(l) from BoardLike l where l.board.id in (b.id) group by b.id) as cnt from Board b join fetch b.communityUser")
    List<Board> findBoardList();
}
