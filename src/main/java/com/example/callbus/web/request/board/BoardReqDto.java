package com.example.callbus.web.request.board;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class BoardReqDto {

    private Long id;
    private String title;
    private String content;
    private CommunityUser communityUser;
    private String deleteYn = "N";

    public Board toEntity() {
        return Board.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .communityUser(this.communityUser)
                .deleteYn(this.deleteYn)
                .build();
    }


}
