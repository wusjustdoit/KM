-- DROP TABLE
DROP TABLE KM_BOARD;
DROP TABLE KM_MEMBER;

-- DROP VIEW TABLE
DROP VIEW KM_BMLIST;
DROP VIEW BLIST;
DROP VIEW BLIST2;

-- KM_MEMBER TABLE (ȸ��)
CREATE TABLE KM_MEMBER(
    KMID       NVARCHAR2(12) PRIMARY KEY,   -- ȸ�� ���̵�
    KMPW       NVARCHAR2(12),               -- ȸ�� ��й�ȣ
    KMNAME     NVARCHAR2(17),               -- ȸ�� �̸�
    KMBIRTH    DATE,                        -- ȸ�� �������
    KMGENDER   NVARCHAR2(2),                -- ȸ�� ����
    KMEMAIL    NVARCHAR2(30),               -- ȸ�� �̸���
    KMFILE     NVARCHAR2(50),               -- �����̸�
    KMPOINT    NUMBER DEFAULT 0             -- ȸ�� KM POINT
);

-- KM_BOARD TABLE (�Խ���)
CREATE TABLE KM_BOARD(
    KMBNUM        NUMBER,                   -- �� ��ȣ(PS)
    KMBID       NVARCHAR2(12) NOT NULL,     -- �ۼ���
    KMBCATEGORY   NVARCHAR2(4),             -- ī�װ�(���Ͽ�, ������Ȳ, ����)
    KMBTITLE      NVARCHAR2(50) NOT NULL,   -- ����
    KMBCONTENTS   NVARCHAR2(1000) NOT NULL, -- ����
    KMBDATE       DATE,                     -- ��Ͻð�
    KMBHITS       NUMBER,                   -- ��ȸ��
    KMBFILE       NVARCHAR2(50),            -- �����̸�
    KMBPOINT      NUMBER,                   -- �Խ��� KM POINT
    KMBPROBLEM    NUMBER DEFAULT 0,         -- �Ű� ����
    CONSTRAINT KMB_PK PRIMARY KEY(KMBNUM),
    FOREIGN KEY(KMBID) REFERENCES KM_MEMBER(KMID)
);

-- ��(ȸ�� ���)
CREATE VIEW KM_BMLIST AS
SELECT KM_MEMBER.*, ROW_NUMBER() OVER(ORDER BY KMNAME ASC)AS RN
FROM KM_MEMBER;

-- ��(�Խ��� ����Ʈ)
CREATE VIEW BLIST AS 
SELECT KM_BOARD.*, ROW_NUMBER() OVER(ORDER BY KMBNUM DESC) AS RN
FROM KM_BOARD;

-- ��(�Ű� �Խù�)
CREATE VIEW BLIST2 AS
SELECT KM_BOARD.*, ROW_NUMBER() OVER(ORDER BY KMBNUM DESC) AS RNS
FROM KM_BOARD WHERE KMBPROBLEM='1';

-- ���ÿ� ȸ��
INSERT INTO KM_MEMBER VALUES('admin', '1234', '������', SYSDATE, '����', 'ADMIN@ADMIN.COM', '', '800000');
INSERT INTO KM_MEMBER VALUES('wusjustdoit', '1234', '���Ǽ�', SYSDATE, '����', 'wusjustdoit@gmail.com', '', '9000');
INSERT INTO KM_MEMBER VALUES('hanjungmin', '1234', '������', SYSDATE, '����', 'hanjungmin@gmail.COM', '', '5000');
INSERT INTO KM_MEMBER VALUES('k0139391', '1234', 'k0139391', SYSDATE, '����', 'k0139391@gmail.com', '', '3000');
INSERT INTO KM_MEMBER VALUES('km399', '1234', '�����', SYSDATE, '����', 'km399@gmail.com', '', '9000');
INSERT INTO KM_MEMBER VALUES('lee31232', '1234', '���ѽ�', SYSDATE, '����', 'lee31232@gmail.com', 'tomato.jpg', '3000');
INSERT INTO KM_MEMBER VALUES('kwon932', '3124!1132', '�ǽ¸�', SYSDATE, '����', 'kwon932@gmail.com', 'tomato.jpg', '3000');
INSERT INTO KM_MEMBER VALUES('domino19', '1234', '�̻���', SYSDATE, '����', 'domino19@gmail.com', '', '100');
INSERT INTO KM_MEMBER VALUES('ke9999', '1234', '���ö', SYSDATE, '����', 'ke9999@gmail.com', '', '500');
INSERT INTO KM_MEMBER VALUES('wjdals19', '1234', '������', SYSDATE, '����', 'wjdals19@gmail.com', '', '2000');
INSERT INTO KM_MEMBER VALUES('blackatt', '1234', '�����', SYSDATE, '����', 'gjtkdwn12@gmail.com', '', '1000');
INSERT INTO KM_MEMBER VALUES('taewon', '1234', '���¿�', SYSDATE, '����', 'tawoend@gmail.com', '', '3000');
INSERT INTO KM_MEMBER VALUES('otheronee', '1234', '������', SYSDATE, '����', 'wlgn9301@gmail.com', '', '1000');
INSERT INTO KM_MEMBER VALUES('zero', '1234', '����', SYSDATE, '����', 'zero01@gmail.com', '', '0');

-- ���ÿ� �Խù�
INSERT INTO KM_BOARD VALUES('1', 'blackatt','����', '��ǥ PPT ����', '<img src="https://media.slidesgo.com/storage/2939210/responsive-images/0-chalkboard-background___media_library_original_1600_900.jpg" width="600px">

��ǥ�ϱ⿡ ���� PPT �����Դϴ�.

', TO_DATE('2018-11-13','YYYY-MM-DD') , '52', 'Chalkboard Background by Slidesgo.pptx', '100', '0');
INSERT INTO KM_BOARD VALUES('2', 'taewon','������Ȳ', '2018�� �߱� ���÷��� ��� ����', '2018�⵵ �߱� ���÷��� ����� ���� ���������Դϴ�.
BOE, CSOT, EDO, �ο� ���� �⺻ ����, �繫 ��Ȳ �� ������� ��Ȳ,
���� ��ȹ, �ֿ� �̽� ���� �� ������ ���������Դϴ�. ', TO_DATE('2018-12-19','YYYY-MM-DD') , '340', '�߱� ���÷��� �гα�� Profile 2018.pdf', '10000', '0');
INSERT INTO KM_BOARD VALUES('3', 'blackatt','���Ͽ�', '���� ����ũ', '���� ������ �ٴϸ鼭 ���� ����ũ ���Ͽ츦 ��ҽ��ϴ�.
���� �е��� �����ϼ����� ���ڽ��ϴ�.', TO_DATE('2019-01-13','YYYY-MM-DD') , '55', '����ũ ���Ͽ�(blackatt).hwp', '1000', '0');
INSERT INTO KM_BOARD VALUES('4', 'lee31232','������Ȳ', '2019�⵵ �׷���ī�� ���� ��Ȳ', '2019�⵵ �׷���ī�� ���� ��Ȳ', TO_DATE('2019-11-14','YYYY-MM-DD') , '123', '2019�⵵ �׷���ī�� ���� ��Ȳ.pdf', '1000', '0');
INSERT INTO KM_BOARD VALUES('5', 'blackatt','���Ͽ�', '��� �� ������ ���ϰŸ� �Ұ�', '��� �Ŀ��� �ٵ� ���Ͻó���?

���� ��� �Ŀ� ������ ���ϰŸ��� ���� ���� �־��.

������ ���������� ���� ��!

������ ������� ���� �� �� �ִ� ����� �Ұ��ص����!', TO_DATE('2019-11-13','YYYY-MM-DD') , '15', '������.hpw', '1000', '0');
INSERT INTO KM_BOARD VALUES('6', 'hanjungmin','���Ͽ�', '�系��ġ �ʽ¹�', '������ ���� �������?
���� ������ �� ���̳� �����Ǿ����ϴ�.
������ ������ ��, �˰� �� �͵�..
�������� ���ϴ�.
�㿡 �ڴٰ� ����, ���� �ȿɴϴ�. (�г�, �Ҿ�, �׸��� �� ������)

�系 ��ġ��� �ܾ�� �� �����ϰ� �޾Ƶ��̱⺸�ٴ�
ȸ���� ����, �濵���� �߿��ϰ� �����ϴ� ��,
������ ���� ������ �ǻ�����ڵ��� ������ �д� ���� �ʿ��մϴ�.
������ �ᱹ , ����� ���� �����Դϴ�. ����� ȸ�簡 �����ϰ� �߿��ϴٰ� �����ϴ� ������ ���ɴϴ�.
����, ���� ���� �ϴ��� ȸ�簡 �����ϱ⿡ �߿��� ���� �ϴ� ����� ���� �򰡿� ������ �մϴ�.
�� ������ �˾��ִ� ����� ������ �������� ���� ���� ����ϴ�. ��Ÿ������ �����Դϴ�.
�׷��� ������, �ٽ� �ѹ� ���� ���� �ֺ��� �ѷ����� ���ư��� ��Ȳ�� �����ϴ� ���� �ʿ��մϴ�.', TO_DATE('2019-11-28','YYYY-MM-DD') , '1', '���� �系��ġ.hwp', '10000', '0');
INSERT INTO KM_BOARD VALUES('7', 'km399','����', '��ȸ���� ���� �������� ���', '�������� ����Դϴ�.', TO_DATE('2020-01-12','YYYY-MM-DD') , '612', '�������� ���.hwp', '1000', '0');
INSERT INTO KM_BOARD VALUES('8', 'hanjungmin','���Ͽ�', 'EndUser�� ���� ȿ������ ���', '����', TO_DATE('2020-03-13','YYYY-MM-DD') , '51', '�����̸�', '1000', '0');
INSERT INTO KM_BOARD VALUES('9', 'blackatt','���Ͽ�', '�˾Ƶθ� ���� ��Ȱ����', '
�Ϲ��ε��� ���� ��Ȱ���� ���缭. 

�ϻ��Ȱ�� ��ȸ��Ȱ���� �����ϰ� �� �� �ִ� ��Ȱ������ 

������ �˱� ���� �����Ͽ����ϴ�.

 - �λ���
 - ������
 - ������
 - �ε��� �� �����
 - ����� �� ����������


', TO_DATE('2020-05-23','YYYY-MM-DD') , '4', '�˾Ƶθ� ���� ��Ȱ ����.docx', '1000', '0');
INSERT INTO KM_BOARD VALUES('10', 'blackatt','����', '', '����', TO_DATE('2020-06-11','YYYY-MM-DD') , '1', '�����̸�', '100', '0');
INSERT INTO KM_BOARD VALUES('11', 'km399','���Ͽ�', '������ �ܱ��', '
�����Ȱ�� �ϸ鼭 �͵��� ������� �����غ��ҽ��ϴ�.

������ �ʿ��� ���� ���� ����� �ƴ϶� �Ҽ��� ����Դϴ�.

�� �Ҽ��� ���, �ܱ��, ������ ���Ͽ츸 �˸� �����ϰ� ó���� �� �ִ� ������ ���� ���̶�� ������

�����ҽ��ϴ�.

���� �е鲲 ������ �Ǳ� ����մϴ�.


', TO_DATE('2020-11-21','YYYY-MM-DD') , '132', null, '1000', '0');
INSERT INTO KM_BOARD VALUES('12', 'hanjungmin','������Ȳ', '�ڷγ��� ���� ���÷��̻�� ����', '
�ڷγ��� ���� ���÷��̻�� ���⿡ ���� �ؿܰ��������� �����Դϴ�.

�� ������ �Ʒ��� �����ϴ�.

 - ���� ����

 - ������ ����
 
 - LCD ���� ����
 
 - �ѱ���� ���� �� �û���
 
���� ������ �ǽñ� �ٶ��ϴ�.

', TO_DATE('2020-12-26','YYYY-MM-DD') , '134', '�ڷγ�19��_���÷��̻��_����.pdf', '1000', '0');


-- ���� �Խñ� ��ȣ +1�� START
-- ���� �Խñ��� 3������ �־ 4�� ����
DROP SEQUENCE KMBNUM_SEQ;
CREATE SEQUENCE KMBNUM_SEQ START WITH 13 INCREMENT BY 1;

COMMIT;










