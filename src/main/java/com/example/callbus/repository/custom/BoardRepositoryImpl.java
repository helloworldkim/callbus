package com.example.callbus.repository.custom;

import com.example.callbus.entity.QCommunityUser;
import com.example.callbus.web.response.board.BoardResDto;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.callbus.entity.QBoard.board;
import static com.example.callbus.entity.QBoardLike.boardLike;
import static com.example.callbus.entity.QCommunityUser.communityUser;
import static com.querydsl.core.types.Projections.bean;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.jpa.JPAExpressions.select;


@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardCustomRepositroy {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardResDto> findBoardListWithQuerydsl(String accountId) {

        List<BoardResDto> result = queryFactory.select(constructor(BoardResDto.class
                , board
                    , select(boardLike.count())
                      .from(boardLike)
                      .where(boardLike.communityUser.accountId.eq(accountId))
                    )
                )
                .from(board)
                .join(board.communityUser, communityUser)
                .fetchJoin()
                .fetch();

        return result;
    }

}
