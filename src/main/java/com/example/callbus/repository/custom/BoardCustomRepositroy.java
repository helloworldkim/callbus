package com.example.callbus.repository.custom;

import com.example.callbus.web.response.board.BoardResDto;
import java.util.List;



public interface BoardCustomRepositroy {
    List<BoardResDto> findBoardListWithQuerydsl(String accountId);

}
