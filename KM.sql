-- DROP TABLE
DROP TABLE KM_BOARD;
DROP TABLE KM_MEMBER;

-- DROP VIEW TABLE
DROP VIEW KM_BMLIST;
DROP VIEW BLIST;
DROP VIEW BLIST2;

-- KM_MEMBER TABLE (회원)
CREATE TABLE KM_MEMBER(
    KMID       NVARCHAR2(12) PRIMARY KEY,   -- 회원 아이디
    KMPW       NVARCHAR2(12),               -- 회원 비밀번호
    KMNAME     NVARCHAR2(17),               -- 회원 이름
    KMBIRTH    DATE,                        -- 회원 생년월일
    KMGENDER   NVARCHAR2(2),                -- 회원 성별
    KMEMAIL    NVARCHAR2(30),               -- 회원 이메일
    KMFILE     NVARCHAR2(50),               -- 파일이름
    KMPOINT    NUMBER DEFAULT 0             -- 회원 KM POINT
);

-- KM_BOARD TABLE (게시판)
CREATE TABLE KM_BOARD(
    KMBNUM        NUMBER,                   -- 글 번호(PS)
    KMBID       NVARCHAR2(12) NOT NULL,     -- 작성자
    KMBCATEGORY   NVARCHAR2(4),             -- 카테고리(노하우, 업계현황, 서식)
    KMBTITLE      NVARCHAR2(50) NOT NULL,   -- 제목
    KMBCONTENTS   NVARCHAR2(1000) NOT NULL, -- 내용
    KMBDATE       DATE,                     -- 등록시간
    KMBHITS       NUMBER,                   -- 조회수
    KMBFILE       NVARCHAR2(50),            -- 파일이름
    KMBPOINT      NUMBER,                   -- 게시판 KM POINT
    KMBPROBLEM    NUMBER DEFAULT 0,         -- 신고 여부
    CONSTRAINT KMB_PK PRIMARY KEY(KMBNUM),
    FOREIGN KEY(KMBID) REFERENCES KM_MEMBER(KMID)
);

-- 뷰(회원 목록)
CREATE VIEW KM_BMLIST AS
SELECT KM_MEMBER.*, ROW_NUMBER() OVER(ORDER BY KMNAME ASC)AS RN
FROM KM_MEMBER;

-- 뷰(게시판 리스트)
CREATE VIEW BLIST AS 
SELECT KM_BOARD.*, ROW_NUMBER() OVER(ORDER BY KMBNUM DESC) AS RN
FROM KM_BOARD;

-- 뷰(신고 게시물)
CREATE VIEW BLIST2 AS
SELECT KM_BOARD.*, ROW_NUMBER() OVER(ORDER BY KMBNUM DESC) AS RNS
FROM KM_BOARD WHERE KMBPROBLEM='1';

-- 예시용 회원
INSERT INTO KM_MEMBER VALUES('admin', '1234', '관리자', SYSDATE, '남자', 'ADMIN@ADMIN.COM', '', '800000');
INSERT INTO KM_MEMBER VALUES('wusjustdoit', '1234', '우의수', SYSDATE, '남자', 'wusjustdoit@gmail.com', '', '9000');
INSERT INTO KM_MEMBER VALUES('hanjungmin', '1234', '한정민', SYSDATE, '남자', 'hanjungmin@gmail.COM', '', '5000');
INSERT INTO KM_MEMBER VALUES('k0139391', '1234', 'k0139391', SYSDATE, '남자', 'k0139391@gmail.com', '', '3000');
INSERT INTO KM_MEMBER VALUES('km399', '1234', '김재원', SYSDATE, '남자', 'km399@gmail.com', '', '9000');
INSERT INTO KM_MEMBER VALUES('lee31232', '1234', '이한승', SYSDATE, '남자', 'lee31232@gmail.com', 'tomato.jpg', '3000');
INSERT INTO KM_MEMBER VALUES('kwon932', '3124!1132', '권승리', SYSDATE, '남자', 'kwon932@gmail.com', 'tomato.jpg', '3000');
INSERT INTO KM_MEMBER VALUES('domino19', '1234', '이상훈', SYSDATE, '남자', 'domino19@gmail.com', '', '100');
INSERT INTO KM_MEMBER VALUES('ke9999', '1234', '김상철', SYSDATE, '남자', 'ke9999@gmail.com', '', '500');
INSERT INTO KM_MEMBER VALUES('wjdals19', '1234', '김정민', SYSDATE, '남자', 'wjdals19@gmail.com', '', '2000');
INSERT INTO KM_MEMBER VALUES('blackatt', '1234', '허상주', SYSDATE, '여자', 'gjtkdwn12@gmail.com', '', '1000');
INSERT INTO KM_MEMBER VALUES('taewon', '1234', '김태원', SYSDATE, '남자', 'tawoend@gmail.com', '', '3000');
INSERT INTO KM_MEMBER VALUES('otheronee', '1234', '윤지후', SYSDATE, '여자', 'wlgn9301@gmail.com', '', '1000');
INSERT INTO KM_MEMBER VALUES('zero', '1234', '김삼순', SYSDATE, '여자', 'zero01@gmail.com', '', '0');

-- 예시용 게시물
INSERT INTO KM_BOARD VALUES('1', 'blackatt','서식', '발표 PPT 서식', '<img src="https://media.slidesgo.com/storage/2939210/responsive-images/0-chalkboard-background___media_library_original_1600_900.jpg" width="600px">

발표하기에 좋은 PPT 서식입니다.

', TO_DATE('2018-11-13','YYYY-MM-DD') , '52', 'Chalkboard Background by Slidesgo.pptx', '100', '0');
INSERT INTO KM_BOARD VALUES('2', 'taewon','업계현황', '2018년 중국 디스플레이 기업 보고서', '2018년도 중국 디스플레이 기업에 대한 프로파일입니다.
BOE, CSOT, EDO, 로열 등의 기본 정보, 재무 현황 및 생산라인 현황,
투자 계획, 주요 이슈 사항 등 정리된 프로파일입니다. ', TO_DATE('2018-12-19','YYYY-MM-DD') , '340', '중국 디스플레이 패널기업 Profile 2018.pdf', '10000', '0');
INSERT INTO KM_BOARD VALUES('3', 'blackatt','노하우', '월급 재테크', '제가 직장을 다니면서 얻을 재테크 노하우를 담았습니다.
많은 분들이 참고하셨으면 좋겠습니다.', TO_DATE('2019-01-13','YYYY-MM-DD') , '55', '재테크 노하우(blackatt).hwp', '1000', '0');
INSERT INTO KM_BOARD VALUES('4', 'lee31232','업계현황', '2019년도 그래픽카드 시장 상황', '2019년도 그래픽카드 시장 상황', TO_DATE('2019-11-14','YYYY-MM-DD') , '123', '2019년도 그래픽카드 시장 상황.pdf', '1000', '0');
INSERT INTO KM_BOARD VALUES('5', 'blackatt','노하우', '퇴근 후 간단한 소일거리 소개', '퇴근 후에는 다들 뭐하시나요?

저는 퇴근 후에 간단한 소일거리로 돈을 벌고 있어요.

많으면 많아질수록 좋은 돈!

간단한 방법으로 돈을 벌 수 있는 방법을 소개해드려요!', TO_DATE('2019-11-13','YYYY-MM-DD') , '15', '투잡비법.hpw', '1000', '0');
INSERT INTO KM_BOARD VALUES('6', 'hanjungmin','노하우', '사내정치 필승법', '여러분 많이 힘드시죠?
저는 승진이 몇 번이나 누락되었습니다.
승진에 떨어진 후, 알게 된 것들..
생각보다 씁니다.
밤에 자다가 깨고, 잠이 안옵니다. (분노, 불안, 그리고 모를 감정에)

사내 정치라는 단어로 좀 불편하게 받아들이기보다는
회사의 방향, 경영진이 중요하게 생각하는 것,
본인이 속한 조직의 의사결정자들의 마음을 읽는 것이 필요합니다.
승진은 결국 , 결과에 대한 보상입니다. 결과는 회사가 집중하고 중요하다고 생각하는 곳에서 나옵니다.
따라서, 같은 일을 하더라도 회사가 생각하기에 중요한 일을 하는 사람이 좋은 평가와 승진을 합니다.
그 진가를 알아주는 사람을 만나기 전까지는 빛을 보기 힘듭니다. 안타깝지만 현실입니다.
그렇기 때문에, 다시 한번 힘을 내서 주변을 둘러보고 돌아가는 상황을 이해하는 것이 필요합니다.', TO_DATE('2019-11-28','YYYY-MM-DD') , '1', '쉬운 사내정치.hwp', '10000', '0');
INSERT INTO KM_BOARD VALUES('7', 'km399','서식', '사회인을 위한 연구보고서 양식', '연구보고서 양식입니다.', TO_DATE('2020-01-12','YYYY-MM-DD') , '612', '연구보고서 양식.hwp', '1000', '0');
INSERT INTO KM_BOARD VALUES('8', 'hanjungmin','노하우', 'EndUser에 대한 효율적인 방법', '내용', TO_DATE('2020-03-13','YYYY-MM-DD') , '51', '파일이름', '1000', '0');
INSERT INTO KM_BOARD VALUES('9', 'blackatt','노하우', '알아두면 좋을 생활법률', '
일반인들을 위한 생활법률 교양서. 

일상생활과 사회생활에서 유용하게 쓸 수 있는 생활법률을 

누구나 알기 쉽게 정리하였습니다.

 - 민사편
 - 형사편
 - 행정편
 - 부동산 및 등기편
 - 재산상속 및 가족관계편


', TO_DATE('2020-05-23','YYYY-MM-DD') , '4', '알아두면 좋을 생활 법률.docx', '1000', '0');
INSERT INTO KM_BOARD VALUES('10', 'blackatt','서식', '', '내용', TO_DATE('2020-06-11','YYYY-MM-DD') , '1', '파일이름', '100', '0');
INSERT INTO KM_BOARD VALUES('11', 'km399','노하우', '업무의 잔기술', '
직장생활을 하면서 터득한 기술들을 정리해보았습니다.

업무에 필요한 것은 고도의 기술이 아니라 소소한 기술입니다.

이 소소한 비법, 잔기술, 숨겨진 노하우만 알면 수월하게 처리할 수 있는 업무가 많을 것이라고 생각해

만들어보았습니다.

많은 분들께 도움이 되길 기원합니다.


', TO_DATE('2020-11-21','YYYY-MM-DD') , '132', null, '1000', '0');
INSERT INTO KM_BOARD VALUES('12', 'hanjungmin','업계현황', '코로나로 인한 디스플레이산업 영향', '
코로나로 인한 디스플레이산업 영향에 대한 해외경제연구소 보고서입니다.

상세 내용은 아래와 같습니다.

 - 공급 영향

 - 수요산업 영향
 
 - LCD 가격 전망
 
 - 한국기업 영향 및 시사점
 
많은 도움이 되시길 바랍니다.

', TO_DATE('2020-12-26','YYYY-MM-DD') , '134', '코로나19의_디스플레이산업_영향.pdf', '1000', '0');


-- 현재 게시글 번호 +1로 START
-- 현재 게시글이 3번까지 있어서 4로 시작
DROP SEQUENCE KMBNUM_SEQ;
CREATE SEQUENCE KMBNUM_SEQ START WITH 13 INCREMENT BY 1;

COMMIT;










