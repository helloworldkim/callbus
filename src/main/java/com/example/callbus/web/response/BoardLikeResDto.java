package com.example.callbus.web.response;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardLikeResDto {

    private Long id;

    private Board board;

    private CommunityUser communityUser;

}
