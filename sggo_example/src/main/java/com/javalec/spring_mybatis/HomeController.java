package com.javalec.spring_mybatis;

import java.lang.reflect.Member;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import com.javalec.spring_mybatis.dao.ContentDao;
import com.javalec.spring_mybatis.dao.MedicineDao;
import com.javalec.spring_mybatis.dao.MedicineService;
import com.javalec.spring_mybatis.dao.UserDao;
import com.javalec.spring_mybatis.dto.Medicine;
import com.javalec.spring_mybatis.dto.User;





/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes(value= {"sessionUser"})
public class HomeController {

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
/*	ContentDao dao;*/
	
	@Autowired
	private UserDao userdao;
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private MedicineDao medicinedao;
	@Autowired
	private MedicineService medicineService;
	
	
	
	
/*	@Autowired
	public void setDao(ContentDao dao) {
		this.dao = dao;
	}*/
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
//		ArrayList<ContentDto> dtos = dao.listDao();
		UserDao dao = sqlSession.getMapper(UserDao.class);
//		ArrayList<ContentDto> dtos = dao.listDao();
		model.addAttribute("list", dao.listDao());
		
		return "/list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		
		
		dao.writeDao(request.getParameter("name"), request.getParameter("telnumber"), request.getParameter("id"), 
				 request.getParameter("password"), request.getParameter("email"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/view")
	public String view() {
		
		return "/view";
	}
	
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		dao.deleteDao(request.getParameter("mId"));
		return "redirect:list";
	}
	
	
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "redirect:login";
	}
	
	/*
	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.POST)
	
	public void login(@RequestParam HashMap<String, String> param, User user,Model model,HttpServletRequest request,Errors errors){
	//	System.out.println(param);
	//	System.out.println("id id"+param.get("mId"));
	
		String id = request.getParameter("id");			//�엯�젰�븳 Id媛� 諛쏆븘�샂
		String password = request.getParameter("password");
		param.get("id");
		param.get("password");
		  
		System.out.println(id+"+"+password);
		
		//String login_check = userdao.login_Dao(id, password);	//�엯�젰�븳 Id媛믪씠�옉 DB鍮꾧탳�븯�젮怨�
		
		
		String login_check = user.getLogin_check();
		
		if(login_check != null){
			System.out.println(login_check);
			System.out.println("로그인 되었습니다.");
			
			
			if (login_check == null) {
				
				System.out.println("로그인 실패");
			} 
		}
		
				
	}
	*/
	
	//
	//로그인 기능 및 직업 구분
	@RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
	public String login( HttpServletRequest request, Model model, User user) {
		System.out.println(user.getId()+"+"+user.getPassword());
		String login_check;
		String job_check;
		String total_check;
		String id = (String)request.getParameter("id");
		User sessionUser = userdao.login_Dao(user); // sessionUser값에 따라 로그인 성공여부 결정
		String job_session = userdao.job_session_Dao(id);
		if (sessionUser == null) {
			login_check="false";
			
			System.out.println("로그인 실패");
		} else {
			System.out.println("로그인 성공");
			model.addAttribute("sessionUser", sessionUser);
			System.out.println(sessionUser);
			login_check="true";
			
	}
		//서버에 반환할값
		return login_check+"/"+job_session;
	}
	
	//
	//회원가입 및 아이디 중복체크
	@RequestMapping(value="joinusDBinsert", method = RequestMethod.POST)
	@ResponseBody
	public String joinusDBinsert(User user, HttpServletRequest request, Model model) {
		//UserDao dao = sqlSession.getMapper(UserDao.class);
		String chk_id = (String)request.getParameter("id");
		String id_check;
		String sessionUser = userdao.chk_id_Dao(chk_id);
		
		if(sessionUser==null) {
			id_check="true";//아이디 생성가능하면
			System.out.println(id_check);
			System.out.println(user);
			//userdao.joinusDao에 user객체를 넘겨 회원등록
			userdao.joinusDao(user);
			
		}else {
			id_check="false";//아이디 생성불가능
			System.out.println(id_check);
		}
		
		//응답해줄 값
		return id_check;
		
	
		
	
	}
	
	//
	//부모 투약의뢰서 작성 시 데이터 삽입 부분
	@RequestMapping(value="medicine")
	@ResponseBody
	public void Medicine(Medicine medicine, HttpServletRequest request, Model model) {
	//	MedicineDao dao = sqlSession.getMapper(MedicineDao.class);
	medicinedao.medicine_Dao(medicine);
	//	dao.medicine(request.getParameter("request_date"), request.getParameter("symptom"), request.getParameter("medicine_category"),
	//			request.getParameter("medicine_amount"), request.getParameter("medicine_count"), request.getParameter("medicine_time"),
	//			request.getParameter("medicine_storage"), request.getParameter("medicine_content"), 
	//			request.getParameter("sign_bitmap_bytecode"));
		
	}
	
	
	//
	//부모 투약의뢰서 리스트뷰에 뿌려줄 내용
	@RequestMapping(value="parent_list_medicine")
	@ResponseBody
	public List<com.javalec.spring_mybatis.dto.Medicine> parent_medicineList(HttpServletRequest request, Model model) {
		
		String parent_id;
		parent_id = (String)request.getParameter("parent_id");
		
		System.out.println(parent_id+"넘어온 부모아이디값");
		List<Medicine> parent_medicineList = medicineService.parent_getMedicineList(parent_id);
		
		
		System.out.println("결과출력");
		for(int i=0; i<parent_medicineList.size();i++) {
			
			System.out.println(parent_medicineList.get(i).getNum());
		}
		model.addAttribute("parent_medicineList", parent_medicineList);
		
		
		return parent_medicineList;
		
	}
	
	
	//선생님 투약의뢰서 리스트뷰에 뿌려줄 내용
		@RequestMapping(value="teacher_list_medicine")
		@ResponseBody
		public List<com.javalec.spring_mybatis.dto.Medicine> teacher_medicineList(HttpServletRequest request, Model model) {
			
			String teacher_id;
			teacher_id = (String)request.getParameter("teacher_id");
			
			System.out.println(teacher_id+"넘어온 선생님아이디값");
			List<Medicine> teacher_medicineList = medicineService.teacher_getMedicineList(teacher_id);
			
			
			System.out.println("결과출력");
			for(int i=0; i<teacher_medicineList.size();i++) {
				
				System.out.println(teacher_medicineList.get(i).getNum());
			}
			model.addAttribute("teacher_medicineList", teacher_medicineList);
			
			
			return teacher_medicineList;
			
		}
	
	
	
	//원래 회원가입 기능
	/*
	@ResponseBody
	@RequestMapping("/joinusDBinsert")
	public void joinusDBinsert(HttpServletRequest request, Model model) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		
		
		
		
		dao.joinusDao(request.getParameter("name"), request.getParameter("phone"), request.getParameter("id"), 
				 request.getParameter("password"), request.getParameter("email"));
		
	
	}
	
	*/

	
	
	


	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@RequestMapping("/login")
	//public String login_Dao(HttpServletRequest request, Model model)
	//{
	//	UserDao dao = sqlSession.getMapper(UserDao.class);
	//	dao.login_Dao(request.getParameter("id"), request.getParameter("password"));
		
	//	System.out.println(request.getParameter("id")+ "+"+ request.getParameter("password"));
	//	return null;
		
	//}
	
	
	
	

	
	
	
	
	
	//int loginIdentify = loginService.loginCheck(1);

	
//	if(loginIdentify==1) {
	//	model.addAttribute("msg", "true");
	//}else {
	//	model.addAttribute("msg", "false");
	//}
	
	//return loginIdentify;
		
	
	
	
	//@RequestMapping("/login")
	//public void login(HttpServletRequest request, Model model) {
	//	IDao dao = sqlSession.getMapper(IDao.class);
		
	//	dao.loginDao(request.getParameter("id"), request.getParameter("password"));
		
		
	//}
	
	//원래 회원가입 기능
		/*
		@ResponseBody
		@RequestMapping("/joinusDBinsert")
		public void joinusDBinsert(HttpServletRequest request, Model model) {
			UserDao dao = sqlSession.getMapper(UserDao.class);
			
			
			
			
			dao.joinusDao(request.getParameter("name"), request.getParameter("phone"), request.getParameter("id"), 
					 request.getParameter("password"), request.getParameter("email"));
			
		
		}
		
		*/
	
		
		
		
	
	
		
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//@RequestMapping("/login")
		//public String login_Dao(HttpServletRequest request, Model model)
		//{
		//	UserDao dao = sqlSession.getMapper(UserDao.class);
		//	dao.login_Dao(request.getParameter("id"), request.getParameter("password"));
			
		//	System.out.println(request.getParameter("id")+ "+"+ request.getParameter("password"));
		//	return null;
			
		//}
		
		
		
		
	
		
		
		
		
		
		//int loginIdentify = loginService.loginCheck(1);
	
		
	//	if(loginIdentify==1) {
		//	model.addAttribute("msg", "true");
		//}else {
		//	model.addAttribute("msg", "false");
		//}
		
		//return loginIdentify;
			
		
		
		
		//@RequestMapping("/login")
		//public void login(HttpServletRequest request, Model model) {
		//	IDao dao = sqlSession.getMapper(IDao.class);
			
		//	dao.loginDao(request.getParameter("id"), request.getParameter("password"));
			
			
		//}
		
	
	
	
	

	
	
}
