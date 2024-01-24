package kr.ezen.jung.service;

import kr.ezen.jung.vo.CommonVO;
import kr.ezen.jung.vo.JungBoardVO;
import kr.ezen.jung.vo.PagingVO;

public interface JungBoardService {
	// 1. 페이징
	PagingVO<JungBoardVO> selectList(CommonVO commonVO);
	
	// 2. 한개얻기
	JungBoardVO selectByIdx(int idx);

	// 3. 저장
	void insert(JungBoardVO jungBoardVO);
	
	// 4. 게시글에서 내리기
	void deleteFake(int idx);

	// 5. 게시글에서 삭제
	void deleteReal(int idx);
	
	// 6.수정
	void update(JungBoardVO jungBoardVO);
	
	// 7. 내가 쓴 글만 보기
	JungBoardVO selectByRef(int idx);
	// 8. 임시저장
	
	
}
