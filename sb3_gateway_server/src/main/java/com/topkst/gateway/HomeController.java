package com.topkst.gateway;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topkst.gateway.dao.EnrollBeaconService;
import com.topkst.gateway.dao.ScanBeaconDAO;
import com.topkst.gateway.dao.ScanBeaconService;
import com.topkst.gateway.dto.EnrollBeacon;
import com.topkst.gateway.dto.ScanBeacon;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	EnrollBeaconService beaconService;
	
	@Autowired
	ScanBeaconService scanBeaconService;
	
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

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	// 유효비콘 서버 > 게이트웨이
	@RequestMapping(value = "center_enrollbeacon_select")
	@ResponseBody
	public List<EnrollBeacon> enroll_list(HttpServletRequest request, Model model) {

		// String center_code = "2109059_01";
		//center_id = (String)request.getParameter("center_id");
		String center_code = (String)request.getParameter("center_id");
		
		System.out.println("[" + center_code + "] 센터의 유효 비콘 값");
		List<EnrollBeacon> enroll_beacon_list = beaconService.center_getEnrollBeaconList(center_code);

		return enroll_beacon_list;

	}

	@RequestMapping(value = "/saveChartConfig")
	public @ResponseBody String processSaveChartConfig(@RequestBody String json)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> jsonToMap = new ObjectMapper().readValue(json, Map.class);
		String templateName = (String) jsonToMap.get("templateName"); // here you get the parameters
		return templateName; // For example
	}

	// 게이트웨이 json 데이터 > 서버
	@RequestMapping(value = "enrollbeacon_insert")
	@ResponseBody
	public String scan_beacon(HttpServletRequest request,  Model model) throws org.json.simple.parser.ParseException, ParseException, IOException {
		
		//	dao.medicine(request.getParameter("request_date"), request.getParameter("symptom"), request.getParameter("medicine_category"),
		//			request.getParameter("medicine_amount"), request.getParameter("medicine_count"), request.getParameter("medicine_time"),
		//			request.getParameter("medicine_storage"), request.getParameter("medicine_content"), 
		//			request.getParameter("sign_bitmap_bytecode"));
		
		System.out.println("=======================================");
		//String jsonInfo = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(jsonInfo);
		String jsonInfo =	"{\"enroll_beacon\":[{\"gateway\":\"2109059_01_G0_01\",\"center\":\"2109059_01\",\"createtime\":\"2017-11-06 15:16:36.580655\",\"mac_address\":\"e4:e3:2b:90:cb:8c\",\"uuid\":\"b9407f30f5f8466eaff925556b57fe6d\",\"major\":6304,\"minor\":23260,\"rssi\":-72,\"tx_power\":-73},{\"gateway\":\"2109059_01_G0_01\",\"center\":\"2109059_01\",\"createtime\":\"2017-11-06 15:17:08.724904\",\"mac_address\":\"e4:e3:2b:90:cb:8c\",\"uuid\":\"b9407f30f5f8466eaff925556b57fe6d\",\"major\":6304,\"minor\":23260,\"rssi\":-72,\"tx_power\":-73},{\"gateway\":\"2109059_01_G0_01\",\"center\":\"2109059_01\",\"createtime\":\"2017-11-06 15:17:11.169187\",\"mac_address\":\"e4:e3:2b:90:cb:8c\",\"uuid\":\"b9407f30f5f8466eaff925556b57fe6d\",\"major\":6304,\"minor\":23260,\"rssi\":-72,\"tx_power\":-69}]}\n" ;

		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);

		// books의 배열을 추출
		JSONArray beaconInfoArray = (JSONArray) jsonObject.get("enroll_beacon");
		ScanBeacon beacon = new ScanBeacon();

		for (int i = 0; i < beaconInfoArray.size(); i++) {

			// 배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
			JSONObject baconObject = (JSONObject) beaconInfoArray.get(i);

			beacon.setGateway((String) baconObject.get("gateway"));
			beacon.setCenter((String) baconObject.get("center"));
		
			String date = (String) baconObject.get("createtime"); // YYYY-MM-DD
			System.out.println(date);
		
			beacon.setCreatetime(date);
					
			beacon.setMac_address((String) baconObject.get("mac_address"));
			beacon.setUuid((String) baconObject.get("uuid"));
			beacon.setMajor(((Long) baconObject.get("major")).intValue());
			beacon.setMinor(((Long) baconObject.get("minor")).intValue());
			beacon.setRssi(((Long) baconObject.get("rssi")).intValue());
			beacon.setTx_power(((Long) baconObject.get("tx_power")).intValue());
			
			System.out.println("=================== enroll_beacon " + (i+1) + " =======================");

			System.out.println("beacon.getCenter() = " + beacon.getCenter() + 
								"\nbeacon.getGateway() = " +beacon.getGateway() + 
								"\nbeacon.getCratedate() = " + beacon.getCreatetime() +
								"\nbeacon.getMac_address() = " + beacon.getMac_address() +
								"\nbeacon.getUuid() = " + beacon.getUuid() +
								"\nbeacon.getMajor() = " + beacon.getMajor() +
								"\nbeacon.getMinor() = " + beacon.getMinor() +
								"\nbeacon.getRssi() = " + beacon.getRssi() +
								"\nbeacon.getTx_power() = " + beacon.getTx_power() 
								);
			
			scanBeaconService.setScanBeacon(beacon);
		}

		return "home";
	}

}
