package com.example.callbus.repository;

import com.example.callbus.entity.Board;
import com.example.callbus.repository.custom.BoardCustomRepositroy;
import com.example.callbus.repository.custom.BoardRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepositroy {

    @Query(" select b from Board b join fetch b.communityUser where b.id = :boardId and b.deleteYn ='N'")
    public Optional<Board> findBoardAndUser(@Param("boardId") Long boardId);

    @Query("select b from Board b join fetch b.communityUser where b.deleteYn ='N'")
    List<Board> findBoardList();

    Optional<Board> findBoardByIdAndDeleteYn(Long boardId, String deleteYn);
}
