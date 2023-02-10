package com.example.callbus.entity;


import com.example.callbus.web.request.BoardReqDto;
import com.example.callbus.web.response.BoardResDto;
import com.example.callbus.web.response.CommuityUserResDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_user_id")
    private CommunityUser communityUser;

    @Builder
    public Board(Long id, String title, String content, CommunityUser communityUser) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.communityUser = communityUser;
    }

    //================================================================
    // DTO변환
    //================================================================

    public BoardResDto toDTO() {
        return BoardResDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .communityUser(this.communityUser)
                .build();
    }

    //================================================================
    // UPDATE
    //================================================================
    public void update(BoardReqDto dto) {

        this.title = dto.getTitle();
        this.content = dto.getContent();

    }
}
