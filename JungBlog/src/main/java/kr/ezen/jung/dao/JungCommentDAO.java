package kr.ezen.jung.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.jung.vo.JungCommentVO;

@Mapper
public interface JungCommentDAO {
	List<JungCommentVO> selectByRef(int idx) throws SQLException;
	
	void insert(JungCommentVO jungCommentVO) throws SQLException;
	
	void update(JungCommentVO jungCommentVO) throws SQLException;
	//이거 int가 맞나??
	void delete(int idx) throws SQLException;
}
