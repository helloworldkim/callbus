package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.web.request.BoardReqDto;
import com.example.callbus.web.response.BoardResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    /**
     * 게시글 저장
     * @param dto
     * @return
     */
    public BoardResDto saveBoard(BoardReqDto dto) {

        Board savedBoard = boardRepository.save(dto.toEntity());
        return savedBoard.toDTO();

    }

    /**
     * 게시글 단건 찾기
     * @param dto
     * @return
     */
    public BoardResDto findBoard(BoardReqDto dto) {

        Optional<Board> findedBoard = boardRepository.findById(dto.getId());

        if (findedBoard.isPresent()) {
            Board board = findedBoard.get();
            return board.toDTO();
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

    }

    /**
     * 게시글 수정
     * @param boardId
     * @param dto
     * @return
     */

    public BoardResDto updateBoard(Long boardId, BoardReqDto dto) {

        Optional<Board> findedBoard = boardRepository.findById(boardId);

        if (findedBoard.isPresent()) {
            Board board = findedBoard.get();
            board.update(dto);
            return board.toDTO();

        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

    }

    /**
     * 게시글 삭제
     * @param boardId
     */
    public void deleteBoard(Long boardId) {

        Optional<Board> findedBoard = boardRepository.findById(boardId);

        if (findedBoard.isPresent()) {
            boardRepository.deleteById(boardId);
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

    }

}
