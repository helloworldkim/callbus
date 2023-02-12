package com.example.callbus.web.response;

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
