package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.board.BoardReqDto;
import com.example.callbus.web.response.boardlike.BoardListResDto;
import com.example.callbus.web.response.board.BoardResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public BoardResDto saveBoard(BoardReqDto dto, String accountId) {

        CommunityUser communityUser = communityUserRepository.findCommunityUserByAccountId(accountId).orElseThrow(() -> new RuntimeException("대상 회원이 없습니다."));
        dto.setCommunityUser(communityUser);
        Board savedBoard = boardRepository.save(dto.toEntity());

        return savedBoard.toDTO();

    }

    /**
     * 게시글 단건 찾기
     * @param boardId
     * @return
     */
    @Transactional
    public BoardResDto findBoard(Long boardId, String accountId) {

        communityUserRepository.findCommunityUserByAccountId(accountId).orElseThrow(() -> new RuntimeException("대상 회원이 없습니다."));
        Board board = boardRepository.findBoardAndUser(boardId).orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));

        return board.toDTO();

    }

    /**
     * 게시글 목록 조회
     * @return
     */
    @Transactional
    public BoardListResDto findBoardList(String accountId) {

        List<Board> list = boardRepository.findBoardList();
        if (list.isEmpty()) {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }

        List<BoardResDto> boardResDtoList = list.stream().map((board) -> {
                                                            return board.toDTO();
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

    @Transactional
    public BoardResDto updateBoard(Long boardId, BoardReqDto dto) {

        Board board = boardRepository.findBoardByIdAndDeleteYn(boardId, dto.getDeleteYn()).orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));
        board.update(dto);
        return board.toDTO();

    }

    /**
     * 게시글 삭제
     * @param boardId
     */
    @Transactional
    public void deleteBoard(Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));
        board.delete();

    }

}
