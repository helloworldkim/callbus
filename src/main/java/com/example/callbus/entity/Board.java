package com.example.callbus.entity;


import com.example.callbus.entity.common.BaseEntity;
import com.example.callbus.web.request.board.BoardReqDto;
import com.example.callbus.web.response.board.BoardResDto;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOARD")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_user_id")
    private CommunityUser communityUser;
    @Formula("(select count(*) from board_like bl where bl.board_id = board_id)")
    private int likeCount;
    @Column(name = "delete_yn")
    private String deleteYn;
    @Transient
    private String likeYn;

    @OneToMany(mappedBy = "board")
    List<BoardLike> boardLikeList = new ArrayList<>();

    @Builder
    public Board(Long id, String title, String content, CommunityUser communityUser, int likeCount, String deleteYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.communityUser = communityUser;
        this.likeCount = likeCount;
        this.deleteYn = deleteYn;
    }

    //================================================================
    // DTO변환
    //================================================================

    public BoardResDto toDTO() {
        return BoardResDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .communityUser(this.communityUser.toDTO())
                .likeCount(this.likeCount)
                .createdDatetime(this.getCreatedDatetime())
                .lastModifiedDatetime(this.getLastModifiedDatetime())
                .deleteYn(this.deleteYn)
                .build();
    }



    //================================================================
    // UPDATE
    //================================================================
    public void update(BoardReqDto dto) {

        this.title = dto.getTitle();
        this.content = dto.getContent();

    }

    //================================================================
    // DELETE
    //================================================================
    public void delete() {
        this.deleteYn = "Y";
    }

}
