package com.example.callbus.web.response.boardlike;

import com.example.callbus.web.response.board.BoardResDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardListResDto {

    List<BoardResDto> items;

    @Builder
    public BoardListResDto(List<BoardResDto> items) {
        this.items = items;
    }
}
