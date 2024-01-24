package kr.ezen.jung.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.jung.dao.JungBoardDAO;
import kr.ezen.jung.vo.CommonVO;
import kr.ezen.jung.vo.JungBoardVO;
import kr.ezen.jung.vo.PagingVO;

@Service(value = "jungBoardService")
public class JungBoardServiceImpl implements JungBoardService {

	@Autowired
	private JungBoardDAO jungBoardDAO;
	
	@Override
	/** 1. 리스트 보기!
	 * @param commonVO 에는 currentPage, sizeOfPage, sizeOfBloak, search 값이 들어있는 클래스
	 * @return 페이징 처리를 한 PagingVO객체 리턴
	 */
	public PagingVO<JungBoardVO> selectList(CommonVO commonVO) {
		PagingVO<JungBoardVO> pv = null;
		try {
			int totalCount = jungBoardDAO.selectCount(); // 서치가 되면 서치가 되게 수정해함!
			pv = new PagingVO<>(totalCount, commonVO.getCurrentPage(), commonVO.getSizeOfPage(), commonVO.getSizeOfBlock()); // 페이지 계산 완료
			HashMap<String, Object> map = new HashMap<>();
			map.put("startNo", pv.getStartNo());
			map.put("endNo", pv.getEndNo());
			map.put("search", commonVO.getSearch());
			pv.setList(jungBoardDAO.selectList(map));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pv;
	}

	@Override
	/** 2. 글 1개 보기!
	 * @param jung_board의 idx
	 * @return idx에 일치하는 JungBoardVO
	 */
	public JungBoardVO selectByIdx(int idx) {
		JungBoardVO jungBoardVO =null;
		try {
			 jungBoardVO =jungBoardDAO.selectByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jungBoardVO;
	}

	@Override
	/** 3. 글 쓰기!
	 * @param JungBoardVO jungBoardVO
	 */
	public void insert(JungBoardVO jungBoardVO) {
		try {
			jungBoardDAO.insert(jungBoardVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/** 4. 게시물에서 글 숨기기기능!
	 * @param jung_board의 idx
	 */
	public void deleteFake(int idx) {
		try {
			jungBoardDAO.deleteFake(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	/** 6. db에서 글삭제!
	 * @param jung_board의 idx
	 */
	public void deleteReal(int idx) {
		try {
			//개선점: 비번을 입력하여 확인 후 지울 것인지 아님 그냥 지울 것인가?
			jungBoardDAO.deleteReal(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	/** 5. 글 수정하기
	 *  @param JungBoardVO jungBoardVO
	 */
	public void update(JungBoardVO jungBoardVO) {
		try {
			jungBoardDAO.update(jungBoardVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/*내가 쓴 글만 보기
	 *	@param JungMemberVO idx
	 * @return idx와 일치하는 JungBoardVO의 ref
	 */
	public JungBoardVO selectByRef(int idx) {
		JungBoardVO jungBoardVO=null;
		try {
			jungBoardVO=jungBoardDAO.selectByRef(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jungBoardVO;
	}

	

}
	
	



	


