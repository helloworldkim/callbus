drop table if exists community_user CASCADE;
drop table if exists board_like CASCADE;
drop table if exists board CASCADE;
create table board (
                       board_id bigint generated by default as identity,
                       content varchar(255),
                       created_date_time timestamp,
                       delete_yn varchar(255),
                       last_modified_date_time timestamp,
                       title varchar(255),
                       community_user_id bigint,
                       primary key (board_id)
);
create table community_user (
                                community_user_id bigint generated by default as identity,
                                account_id varchar(255),
                                account_type varchar(255),
                                nickname varchar(255),
                                quit varchar(255),
                                primary key (community_user_id)
);
create table board_like (
                            like_id bigint generated by default as identity,
                            board_id bigint,
                            community_user_id bigint,
                            primary key (like_id)
);