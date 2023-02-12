package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.BoardReqDto;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.BoardListResDto;
import com.example.callbus.web.response.BoardResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    private final CommunityUserRepository communityUserRepository;

    /**
     * 게시글 저장
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public BoardResDto saveBoard(BoardReqDto dto, String accountId) {

        Optional<CommunityUser> user = communityUserRepository.findByAccountId(accountId);

        if (user.isPresent()) {
            CommunityUser communityUser = user.get();
            dto.setCommunityUser(communityUser);

            Board savedBoard = boardRepository.save(dto.toEntity());
            return savedBoard.toDTO();
        } else {
            throw new RuntimeException("대상 회원이 없습니다.");
        }

    }

    /**
     * 게시글 단건 찾기
     * @param boardId
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public BoardResDto findBoard(Long boardId) {

        Optional<Board> findedBoard = boardRepository.findById(boardId);

        if (findedBoard.isPresent()) {
            Board board = findedBoard.get();
            CommunityUser communityUser = board.getCommunityUser();
            return board.toDTO();
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

    }

    /**
     * 게시글 목록 조회
     * @return
     */
    public BoardListResDto findBoardList() {

        List<Board> list = boardRepository.findAll();

        if (list.isEmpty()) {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }

        List<BoardResDto> boardResDtoList = list.stream()
                                        .map((board) -> {
                                            return board.toNoUserDTO();
                                        })
                                        .collect(Collectors.toList());
        BoardListResDto boardListResDto = BoardListResDto.builder().items(boardResDtoList).build();


        return boardListResDto;


    }

    /**
     * 게시글 수정
     * @param boardId
     * @param dto
     * @return
     */

    @Transactional(rollbackFor = RuntimeException.class)
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
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBoard(Long boardId) {

        Optional<Board> findedBoard = boardRepository.findById(boardId);

        if (findedBoard.isPresent()) {
            boardRepository.deleteById(boardId);
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

    }

}
