package com.example.callbus.entity;


import com.example.callbus.web.response.boardlike.BoardLikeResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_user_id")
    private CommunityUser communityUser;

    @Builder
    public BoardLike(Long id, Board board, CommunityUser communityUser) {
        this.id = id;
        this.board = board;
        this.communityUser = communityUser;
    }

    //================================================================
    // DTO변환
    //================================================================

    public BoardLikeResDto toDTO() {
        return BoardLikeResDto.builder()
                .id(this.id)
                .board(this.board)
                .communityUser(this.communityUser)
                .build();
    }
}
