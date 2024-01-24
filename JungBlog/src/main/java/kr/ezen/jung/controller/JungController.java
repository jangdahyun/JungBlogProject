package kr.ezen.jung.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ezen.jung.service.JungBoardService;
import kr.ezen.jung.service.JungCommentService;
import kr.ezen.jung.service.JungMemberService;
import kr.ezen.jung.vo.CommonVO;
import kr.ezen.jung.vo.JungBoardVO;
import kr.ezen.jung.vo.JungCommentVO;
import kr.ezen.jung.vo.JungMemberVO;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Controller
@Slf4j
public class JungController {
	@Autowired
	private JungBoardService jungBoardService;
	
	@Autowired
	private JungMemberService jungMemberService;
	
	@Autowired
	private JungCommentService jungCommentService;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@ModelAttribute(value = "cv") CommonVO cv, Model model) {
		model.addAttribute("pv", jungBoardService.selectList(cv));
		return "index";
	}
	@GetMapping(value = "/blog/{idx}")
	public String as(@PathVariable(value = "idx") int idx, Model model) {
		JungBoardVO boardVO = jungBoardService.selectByIdx(idx);
		
//		boardVO.setMember();
		List<JungCommentVO> commentList = jungCommentService.selectByRef(idx);
		
		boardVO.setCommentList(commentList);
		
		model.addAttribute("board",boardVO);
		return "blog"; // 임시값 blog.html
	}
	
	@PostMapping(value = "/blog/comment/{idx}")
	@ResponseBody
	public List<JungCommentVO> asd(@PathVariable(value = "idx") int idx){
		List<JungCommentVO> commentList = null;
		commentList = jungCommentService.selectByRef(idx);
		for(JungCommentVO commentVO : commentList) {
//			commentVO.setMemberVO();
		}
		return commentList;
	}

	@GetMapping(value = { "/login" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		if (error != null)
			model.addAttribute("error", "error");
		if (logout != null)
			model.addAttribute("logout", "logout");
		return "login";
	}
	//회원가입 폼
	@GetMapping(value = {"/join"})
	public String join(HttpSession session) {
		// 현재 로그인이 되어있는데 회원가입을 하려고 한다. 막아야 한다.
		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");// 세션에 회원정보만 지운다.
			session.invalidate();// 세션자체를 끊고 다시 연결한다.
			return "redirect:/";
		}
		return "join";
	}
	@GetMapping(value = "/test/userIdCheck", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String userIdCheck(@RequestParam(value = "username")String username) {
		return jungMemberService.selectByUsername(username)+"";
	}
	//회원가입 완료
	@GetMapping("/joinok")
	public String joinOkGet() {
		return "redirect:/";
	}
	@PostMapping("/joinok")
	public String joinOkPost(@ModelAttribute(value = "vo") JungMemberVO vo,Model model,@RequestParam(value = "bd")String bd ) {
		// 내용 검증을 해줘야 한다.
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(bd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		vo.setBirthDate(date);
		vo.setRole("ROLE_USER");
		model.addAttribute("vo",vo);
//		memberService.insert(vo); // 저장
		return "joinOk";
	}
	
//	@GetMapping(value = "/myblog/{idx}")
//	public String myblog(@PathVariable(value = "idx") int idx, Model model) {
//		JungMemberVO jungMemberVO=jungMemberService.selectByRef
//		
////		boardVO.setMember();
//		List<JungCommentVO> commentList = jungCommentService.selectByRef(idx);
//		
//		boardVO.setCommentList(commentList);
//		
//		model.addAttribute("board",boardVO);
//		return "myblog"; // 임시값 blog.html
//	}
	
	//나만의 글 보기 맞나??
	@PostMapping(value = "/myblog")
	@ResponseBody
	public String myblog2(@PathVariable(value = "idx") int idx, Model mode) {
		JungBoardVO jungBoardVO=null;
		jungBoardVO=jungBoardService.selectByRef(idx);
		return null;
		
	}

	// 파일 업로드 처리 함
	@GetMapping("/ckeditor2")
	public String ckeditor2(Model model) {
		model.addAttribute("today", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd(E) hh:mm:ss")));
		return "ckeditor2";
	}

	@GetMapping("/ckeditorResult2")
	public String ckeditorResult2(Model model) {
		model.addAttribute("today", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd(E) hh:mm:ss")));
		return "redirect:/ckeditor2";
	}

	@PostMapping("/ckeditorResult2")
	public String ckeditorResult2(@RequestParam Map<String, String> param, Model model) {
		// MultipartFile 이것이 파일을 알아서 받아준다.
		model.addAttribute("map", param);
		return "ckeditorResult2";
	}

	@PostMapping("/fileupload")
	@ResponseBody
	public Map<String, Object> fileUpload(HttpServletRequest request,
			@RequestPart(value = "upload", required = false) MultipartFile upload) {
		// 반드시 받는 이름이 "upload"이어야 한다.
		// json 데이터로 등록
		// {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		// 이런 형태로 리턴이 나가야함.
		// --------------------------------------------------------------------------------------
		// 서버의 업로드될 경로 확인
		String uploadPath = request.getServletContext().getRealPath("/ckeditor/");
		// 파일 객체 생성
		File file2 = new File(uploadPath);
		// 폴더가 없다면 폴더를 생성해준다.
		if (!file2.exists()) {
			file2.mkdirs();
		}
		log.info("서버 실제 경로 : " + uploadPath);
		// --------------------------------------------------------------------------------------
		String saveName = "";
		String saveFileName = "";

		if (upload != null && upload.getSize() > 0) { // 파일이 넘어왔다면
			try {
				// 저장파일의 이름 중복을 피하기 위해 저장파일이름을 유일하게 만들어 준다.
				saveFileName = UUID.randomUUID() + "_" + upload.getOriginalFilename();
				// 파일 객체를 만들어 저장해 준다.
				File saveFile = new File(uploadPath, saveFileName);
				// 파일 복사
				FileCopyUtils.copy(upload.getBytes(), saveFile);
				saveName = request.getContextPath() + "/ckeditor/" + saveFileName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * VO를 만들었다면 CkeditorResultVO vo = new CkeditorResultVO();
		 * vo.setFileName(saveFileName); vo.setUploaded(1); vo.setUrl(saveName);
		 * log.info("리턴값 : " + vo); return vo;
		 */
		// VO가 없다면 맵으로 받아 처리
		Map<String, Object> map = new HashMap<>();
		map.put("fileName", saveFileName);
		map.put("uploaded", 1);
		map.put("url", saveName);
		return map;
	}

 //딱! 한번만 실행해야한다!

//	@Autowired	
//	private JdbcTemplate jdbcTemplate;
//	
//		@Autowired
//		private BCryptPasswordEncoder passwordEncoder;
//	
//		@GetMapping("/dbinit") // 기존에 등록된 비번을 암호화 해서 변경한다. 1번만 실행하고 지워줘라~~~
//		public String dbInit() {
//			jdbcTemplate.update("update jung_member set password = ? where username = ?", passwordEncoder.encode("123456"),"admin");
//			jdbcTemplate.update("update jung_member set password = ? where username = ?", passwordEncoder.encode("123456"),"master");
//			jdbcTemplate.update("update jung_member set password = ? where username = ?", passwordEncoder.encode("123456"),"webmaster");
//			jdbcTemplate.update("update jung_member set password = ? where username = ?", passwordEncoder.encode("123456"),"root");
//			jdbcTemplate.update("update jung_member set password = ? where username = ?", passwordEncoder.encode("123456"),"dba");
//			return "redirect:/";
//		}
}
