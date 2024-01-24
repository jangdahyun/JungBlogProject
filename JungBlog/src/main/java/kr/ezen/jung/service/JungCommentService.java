package kr.ezen.jung.service;

import java.util.List;

import kr.ezen.jung.vo.JungCommentVO;

public interface JungCommentService {
	List<JungCommentVO> selectByRef(int idx);
	
	void insert(JungCommentVO jungCommentVO);
	
	void update(JungCommentVO jungCommentVO);
	
	void delete(int idx);
}
