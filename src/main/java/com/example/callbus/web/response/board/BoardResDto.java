package com.example.callbus.web.response.board;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResDto {

    private Long id;
    private String title;
    private String content;
    private CommunityUserResDTO communityUser;
    private int likeCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDatetime;
    private String deleteYn;
    private String likeYn;

    @Builder
    public BoardResDto(Long id, String title, String content, CommunityUserResDTO communityUser, int likeCount, LocalDateTime createdDatetime, LocalDateTime lastModifiedDatetime, String deleteYn, String likeYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.communityUser = communityUser;
        this.likeCount = likeCount;
        this.createdDatetime = createdDatetime;
        this.lastModifiedDatetime = lastModifiedDatetime;
        this.deleteYn = deleteYn;
        this.likeYn = likeYn;
    }
}
