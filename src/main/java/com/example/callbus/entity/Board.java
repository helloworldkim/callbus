package com.example.callbus.entity;


import com.example.callbus.web.request.board.BoardReqDto;
import com.example.callbus.web.response.board.BoardResDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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
    @Formula("(select count(*) from board_like bl where bl.board_id = board_id)")
    private int likeCount;
    @CreatedDate
    private LocalDateTime createdDateTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;
    private String deleteYn;
    @Transient
    private String likeYn;

    @OneToMany(mappedBy = "board")
    List<BoardLike> boardLikeList = new ArrayList<>();

    @Builder
    public Board(Long id, String title, String content, CommunityUser communityUser, LocalDateTime createdDateTime, LocalDateTime lastModifiedDateTime, String deleteYn, String likeYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.communityUser = communityUser;
        this.createdDateTime = createdDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
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
                .communityUser(this.communityUser)
                .likeCount(this.likeCount)
                .createdDatetime(this.createdDateTime)
                .lastModifiedDatetime(this.lastModifiedDateTime)
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
