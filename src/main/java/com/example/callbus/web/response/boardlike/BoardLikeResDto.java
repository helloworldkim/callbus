package com.example.callbus.web.response.boardlike;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import lombok.*;

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
