
-- 회원
-- 아이디 : foo@foo.com
-- 비번 : foo
INSERT INTO MEMBER (CREATED_BY, UPDATED_BY, EMAIL, PASSWORD, NAME, AUTH)
VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'foo@foo.com', '$2a$10$8XTTV0lfr4NnjpVJH9f4D.vGh0lxrI0o.7AszhGBYI73d/vtzVueq', 'foo', 'ROLE_USER');

-- 아이디 : jpa@jpa.com
-- 비번 : foo
INSERT INTO MEMBER (CREATED_BY, UPDATED_BY, EMAIL, PASSWORD, NAME, AUTH)
VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),'jpa@jpa.com', '$2a$10$8XTTV0lfr4NnjpVJH9f4D.vGh0lxrI0o.7AszhGBYI73d/vtzVueq', 'jpa', 'ROLE_USER');

-- 게시판
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목1','내용1','foo@foo.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목2','내용2','foo@foo.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목3','내용3','foo@foo.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목4','내용4','foo@foo.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목5','내용5','foo@foo.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- --
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목6','내용6','jpa@jpa.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목7','내용7','jpa@jpa.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목8','내용8','jpa@jpa.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목9','내용9','jpa@jpa.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목10','내용10','jpa@jpa.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- --
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목11','내용11','foo@foo.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- INSERT INTO BOARD (TITLE, CONTENTS, WRITER, HIT, CREATED_BY, UPDATED_BY)
-- VALUES('제목12','내용12','jpa@jpa.com',0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());