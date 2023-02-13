package com.example.callbus.web.response.boardlike;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardLikeResDto {

    private Long id;

    private Board board;

    private CommunityUserResDTO communityUser;

}
