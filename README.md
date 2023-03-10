## 사용한 기술 스택 및 
Spring Boot 2.7.8
내장 메모리 DB(H2)
Spring Data JPA
JDK 1.8

## spring.io에서 지원하지 않는 리스트
commons-lang3(아파치) - null체크를 위함
p6spy-spring-boot-starter:1.7.1 - JPA내 Parameter값 확인을 위한 외부 라이브러리

## 기능 요구사항
- 가입되어있는 공인중개사, 임대인, 임차인, 외부 사용자 모두 쓸 수 있도록 해야 합니다.
- 임대인, 임차인, 공인중개사는 커뮤니티에 글을 쓸 수 있고, 외부 사용자는 글을 쓸 수 없습니다.
- 글 목록에선 작성한 사용자가 어떤 계정 타입인지를 표시할 수 있어야 합니다. ex ) 김씨(공인중개사). 계정 타입은 한글로 표시되어야 합니다.
- 커뮤니티에 가입한 사용자라면 글 목록에 자신이 좋아요한 글인지 아닌지를 표시해줄 수 있어야 합니다.
- 글 목록에는 글에 달린 좋아요 수를 표시할 수 있어야 합니다.


## 해당 프로젝트 확인방법
- 내장되어있는 메모리 DB를 사용하였기 때문에 별도의 DB셋팅은 필요하지 않습니다.
- 해당프로젝트 JDK 1.8 셋팅 후 전체 테스트 코드 수행
- Spring boot 실행 후 API 서버 동작확인

## 대략적인 API 주소
- 회원 등록 post, /api/v1/regist
- 게시글 목록조회 get, /api/v1/board/list
- 게시글 조회 get, /api/v1/board/게시글번호
- 게시글 등록 post, /api/v1/board
- 게시글 수정 put, /api/v1/board/게시글번호
- 게시글 삭제 delete, /api/v1/board/게시글번호
- 게시글 좋아요 등록 post, /api/v1/like/게시글번호
- 게시글 좋아요 삭제 delete, /api/v1/like/게시글번호
