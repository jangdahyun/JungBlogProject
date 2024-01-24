package kr.ezen.jung.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.jung.vo.JungBoardVO;

@Mapper
public interface JungBoardDAO {
	List<JungBoardVO> selectList(HashMap<String,Object> map) throws SQLException;
	JungBoardVO selectByIdx(int idx) throws SQLException;
	int selectCount() throws SQLException;
	void insert(JungBoardVO jungBoardVO) throws SQLException;
	void update(JungBoardVO jungBoardVO) throws SQLException;
	void updateReadCount(int idx) throws SQLException;
	void deleteFake(int idx) throws SQLException;
	void deleteReal(int idx) throws SQLException;
	JungBoardVO selectByRef(int idx) throws SQLException;
	
}
