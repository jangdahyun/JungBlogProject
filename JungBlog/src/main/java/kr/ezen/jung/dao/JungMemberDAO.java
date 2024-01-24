package kr.ezen.jung.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.jung.vo.JungMemberVO;

@Mapper
public interface JungMemberDAO {
	JungMemberVO selectByUsername(String username) throws SQLException;
	JungMemberVO selectByIdx(int idx) throws SQLException;
	
	JungMemberVO findByNameAndbirthDate(HashMap<String, Object> map) throws SQLException;
	
	JungMemberVO findByuserNameAndbirthDate(HashMap<String, Object> map) throws SQLException;
	List<JungMemberVO> selectAll() throws SQLException;
	void insert(JungMemberVO jungMemberVO) throws SQLException;
	void update(JungMemberVO jungMemberVO) throws SQLException;
	void updateRole(JungMemberVO jungMemberVO) throws SQLException;
	void updatePassword(JungMemberVO jungMemberVO) throws SQLException;
	void deleteByIdx(int idx) throws SQLException;
	void deleteByUsername(String username ) throws SQLException;
	int selectCountByUsername(String username) throws SQLException;
	
}
