CREATE sequence jung_comment_seq_idx;

CREATE TABLE jung_comment(
	idx NUMBER PRIMARY KEY,
	boardRef NUMBER NOT NULL,
	memberRef NUMBER NOT NULL,
	reply varchar2(250) NOT NULL,
	regDate timestamp DEFAULT sysdate
)

SELECT * FROM jung_comment;
