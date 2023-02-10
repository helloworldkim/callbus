package com.example.callbus.web.response;

import com.example.callbus.entity.CommunityUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResDto {

    private Long id;
    private String title;
    private String content;
    private CommunityUser communityUser;

    @Builder
    public BoardResDto(Long id, String title, String content, CommunityUser communityUser) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.communityUser = communityUser;
    }
}
