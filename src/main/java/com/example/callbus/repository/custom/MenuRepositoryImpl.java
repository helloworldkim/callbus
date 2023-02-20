package com.example.callbus.repository.custom;

import com.example.callbus.entity.Menu;
import com.example.callbus.entity.QMenu;
import com.example.callbus.web.response.board.BoardResDto;
import com.example.callbus.web.response.menu.MenuResDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.callbus.entity.QBoard.board;
import static com.example.callbus.entity.QBoardLike.boardLike;
import static com.example.callbus.entity.QCommunityUser.communityUser;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.jpa.JPAExpressions.select;


@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuCustomRepositroy {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findMenuListWithQuerydsl() {
        QMenu parent = new QMenu("parent");
        QMenu child = new QMenu("child");

        return queryFactory.selectFrom(parent)
//                .distinct()
                .leftJoin(parent.children, child)
                .fetchJoin()
                .where(
                        parent.parent.isNull()
                )
                .orderBy(parent.listOrder.asc(), child.listOrder.asc())
                .fetch();

    }

}
