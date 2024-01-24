package kr.ezen.jung.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import kr.ezen.jung.vo.JungMemberVO;

public interface JungMemberService extends UserDetailsService{
	// 
	JungMemberVO selectByUsername(String username);
	
	JungMemberVO selectByIdx(int idx);
	
	JungMemberVO findByNameAndbirthDate(HashMap<String, Object> map);
	
	
	JungMemberVO findByuserNameAndbirthDate(HashMap<String, Object> map);
	
	List<JungMemberVO> selectAll();
	void insert(JungMemberVO jungMemberVO);
	
	void update(JungMemberVO jungMemberVO);
	
	void updateRole(JungMemberVO jungMemberVO);
	
	void updatePassword(JungMemberVO jungMemberVO);
	
	void deleteByIdx(JungMemberVO jungMemberVO);
	void deleteByUsername(JungMemberVO jungMemberVO);
	
	int selectCountByUsername(String username); 
}
