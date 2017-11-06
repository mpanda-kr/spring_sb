package com.topkst.beacon;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topkst.beacon.DAO.beaconDAO;
import com.topkst.beacon.DTO.Beacon_scan;
import com.topkst.beacon.DTO.Enroll_beacon;
import com.topkst.beacon.Service.EnrollService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController 
{
	@Autowired
	beaconDAO beacon_dao;
	
	@Autowired
	EnrollService enrollService;
	
	
	//Beacon_scan beacon_scan;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	
	
	 @RequestMapping(value="test")	
	   public String beacon_sacn(Beacon_scan beacon_scan,Model model) throws ParseException {
		 //  String mac_addr;
		 //  mac_addr = request.getParameter("mac_addr");
		 //  System.out.println(mac_addr+"넘어온 값");		 
		 
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date parsed = format.parse("2017-10-24");
		 java.sql.Date sql = new java.sql.Date(parsed.getTime());
		 
		 String s = "18:26:17";
		 SimpleDateFormat sdf = new SimpleDateFormat("hh:ss:mm");
		 long ms = sdf.parse(s).getTime();
		 Time t = new Time(ms);

		  Beacon_scan test = new Beacon_scan();
		  test.setCreatedate(sql);	
		  test.setCreatetime(t);
		  test.setDetailtime("18:26:17.365586");
		  test.setMac_address("d0:95:c7:04:85:f1");
		  test.setMajor(21332);
		  test.setMinor(20302);
		  test.setRssi(69);
		  test.setTx_power(-91);
		  test.setUuid("6a60f18a11e590f70002a5d5c51b0609");		  
		
		  
		  for(int i=0;i<2;i++)
			  beacon_dao.scanDao(test);		   
		  
		  return "test";
	      
	   }
	
	   
	   //선생님 투약의뢰서 리스트뷰에 뿌려줄 내용
	      @RequestMapping(value="center_enrollbeacon")
	      @ResponseBody
	      public List<Enroll_beacon> enroll_list (HttpServletRequest request, Model model) {
	         
	         String center_id = "2109059_01";
	         //center_id = (String)request.getParameter("center_id");
	         
	         
	         String gateway_id = "2109059_01_G0_01";
	         //gateway_id = (String)request.getParameter("gateway_id");
	         
	         System.out.println(center_id+" 넘어온 센터ID값");
	         List<Enroll_beacon> enroll_beacon_list = enrollService.center_getEnrollBeacon(center_id,gateway_id);
	         
	         return enroll_beacon_list;
	         
	      }
}
