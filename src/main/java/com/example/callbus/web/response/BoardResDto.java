package com.example.callbus.web.response;

import com.example.callbus.entity.CommunityUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardResDto {

    private Long id;
    private String title;
    private String content;
    private CommunityUser communityUser;
    private int cnt;

    @Builder
    public BoardResDto(Long id, String title, String content, CommunityUser communityUser, int cnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cnt = cnt;
        this.communityUser = communityUser;
    }
}
