package kr.ezen.jung.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ezen.jung.dao.JungCommentDAO;
import kr.ezen.jung.vo.JungCommentVO;

@Service(value = "jungService")
@Transactional
public class JungCommentServiceImpl implements JungCommentService{
	@Autowired
	private JungCommentDAO jungCommentDAO;

	@Override
	public List<JungCommentVO> selectByRef(int idx) {
		List<JungCommentVO> list = null;
		try {
			list = jungCommentDAO.selectByRef(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(JungCommentVO jungCommentVO) {
		try {
			jungCommentDAO.insert(jungCommentVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(JungCommentVO jungCommentVO) {
		try {
			jungCommentDAO.update(jungCommentVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int idx) {
		try {
			jungCommentDAO.delete(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
