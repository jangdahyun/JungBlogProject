package kr.ezen.jung.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class JungBoardVO {
	private int idx;					// 키필드
	private int ref;					// user>idx
	private String title;				// 제목
	private String content;				// 내용
	
	private int readCount;				// 조회수
	private LocalDateTime regDate;		// 게시일
	private boolean	deleted;			// 삭제됨
	// db설계끝
	
	
	
	
	// 제한된 유저정보만 idx, 이름(nickname), 사진, 
	private JungMemberVO member;			
	
	// 댓글을 담을 객체
	private List<JungCommentVO> commentList;
	// ------------------------------------------------------------
	// 추가등등
	// ------------------------------------------------------------
	// 태그
	// 좋아요!
	// ------------------------------------------------------------
	
}
