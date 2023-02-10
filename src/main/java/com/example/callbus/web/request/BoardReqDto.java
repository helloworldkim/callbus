package com.example.callbus.web.request;

import com.example.callbus.entity.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class BoardReqDto {

    private Long id;
    private String title;
    private String content;
    private String userId;


    public Board toEntity() {
        return Board.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .build();
    }


}
