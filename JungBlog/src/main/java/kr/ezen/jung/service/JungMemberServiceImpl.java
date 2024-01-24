package kr.ezen.jung.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ezen.jung.dao.JungMemberDAO;
import kr.ezen.jung.vo.JungMemberVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JungMemberServiceImpl implements JungMemberService{

	@Autowired
	private JungMemberDAO memberDAO;

	@Override // 리턴 타입을 UserDetails을 구현한 VO로 바꿔주고 DAO에서 Userid로 VO를 얻어 리턴한다.
	public JungMemberVO loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(" : " + username + "으로 호출");
		JungMemberVO memberVO = null;
		try {
			memberVO = memberDAO.selectByUsername(username);
			if (memberVO == null) {
				throw new UsernameNotFoundException("지정 아이디가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(memberVO + "리턴");
		return memberVO;
	}

	@Override
	public JungMemberVO selectByUsername(String username) {
		JungMemberVO jungMemberVO = null;
		try {
			jungMemberVO = memberDAO.selectByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jungMemberVO;
	}

	@Override
	public JungMemberVO selectByIdx(int idx) {
		JungMemberVO jungMemberVO = null;
		try {
			jungMemberVO = memberDAO.selectByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jungMemberVO;
	}

	

	@Override
	public List<JungMemberVO> selectAll() {
		List<JungMemberVO> list = null;
		try {
			list = memberDAO.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(JungMemberVO jungMemberVO) {
		if(jungMemberVO!=null) {
			// 비번을 암호화 한다.
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			jungMemberVO.setPassword(passwordEncoder.encode(jungMemberVO.getPassword()));
			// 여기에서 권한을 지정한다.
			jungMemberVO.setRole("ROLE_USER");
			try {
				memberDAO.insert(jungMemberVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(JungMemberVO jungMemberVO) {
		if(jungMemberVO!=null) {
			try {
				// DB에서 해당번호의 정보를 얻는다.
				// MemberVO vo = memberDAO.selectByIdx(memberVO.getIdx());
				// 비번이 일치하면 수정한다. 여기에서 저장된비번은 암호화 되어있고 내가 입력한 비번은 암호가 되어있지 않다.
				// 이 부분은 나중에 수정해 줘야 한다.?????
				memberDAO.update(jungMemberVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateRole(JungMemberVO jungMemberVO) {
		if(jungMemberVO!=null) {
			try {
				// DB에서 해당번호의 정보를 얻는다.
				// MemberVO vo = memberDAO.selectByIdx(memberVO.getIdx());
				// 비번이 일치하면 수정한다. 여기에서 저장된비번은 암호화 되어있고 내가 입력한 비번은 암호가 되어있지 않다.
				// 이 부분은 나중에 수정해 줘야 한다.
				memberDAO.updateRole(jungMemberVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updatePassword(JungMemberVO jungMemberVO) {
		if(jungMemberVO!=null) {
			try {
				// DB에서 해당번호의 정보를 얻는다.
				// MemberVO vo = memberDAO.selectByIdx(memberVO.getIdx());
				// 비번이 일치하면 수정한다. 여기에서 저장된비번은 암호화 되어있고 내가 입력한 비번은 암호가 되어있지 않다.
				// 이 부분은 나중에 수정해 줘야 한다.
				memberDAO.updateRole(jungMemberVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteByIdx(JungMemberVO jungMemberVO) {
		if(jungMemberVO!=null) {
			try {
				// DB에서 해당번호의 정보를 얻는다.
				// MemberVO vo = memberDAO.selectByIdx(memberVO.getIdx());
				// 비번이 일치하면 수정한다. 여기에서 저장된비번은 암호화 되어있고 내가 입력한 비번은 암호가 되어있지 않다.
				// 이 부분은 나중에 수정해 줘야 한다.
				memberDAO.deleteByIdx(jungMemberVO.getIdx());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteByUsername(JungMemberVO jungMemberVO) {
		if(jungMemberVO!=null) {
			try {
				// DB에서 해당번호의 정보를 얻는다.
				// MemberVO vo = memberDAO.selectByIdx(memberVO.getIdx());
				// 비번이 일치하면 삭제한다. 여기에서 저장된비번은 암호화 되어있고 내가 입력한 비번은 암호가 되어있지 않다.
				// 이 부분은 나중에 수정해 줘야 한다.
				memberDAO.deleteByUsername(jungMemberVO.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public int selectCountByUsername(String username) {
		int count = 0;
		try {
			count = memberDAO.selectCountByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public JungMemberVO findByNameAndbirthDate(String name, Date birthDate) {
		HashMap<String, Object> put = new HashMap<>();
		put.put("name", name);
		put.put("birthDate", birthDate);
		memberDAO.findByNameAndbirthDate(put);
		JungMemberVO jungMemberVO=null;
		
		return null;
	}

	@Override
	public JungMemberVO findByuserNameAndbirthDate(String username, Date birthDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
