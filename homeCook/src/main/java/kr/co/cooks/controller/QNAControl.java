package kr.co.cooks.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.cooks.service.QNAService;
import kr.co.cooks.vo.QNAVO;
import kr.co.cooks.vo.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QNAControl {
	// 1212
	private static final Logger logger = LoggerFactory.getLogger(NoticeControl.class);
	
	@Autowired QNAService qnaService;
	QNAVO qnaVO;
	
//	@RequestMapping(value = "/QNAlist.app")
//	public ModelAndView list(HttpSession session) {
//		
//		ModelAndView mav= new ModelAndView();	
//		
//		HashMap<String,Object> hm = qnaService.qnalist();
//		
//		mav.addObject("qnaList", (List<QNAVO>)hm.get("qnaList"));
//		mav.addObject("count",hm.get("count") );
//		mav.addObject("pagecode", (String)hm.get("pagecode"));
//		mav.setViewName("QNA/qna_list");
//		return mav;
//	}
	
	@RequestMapping(value="/QNAWriteForm.app")
	public ModelAndView QNAWriteForm(@RequestParam String r_num, @RequestParam int f_num){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.setViewName("QNA/qna_writeForm");
		return mav;
	}
	
	@RequestMapping(value="/QNAWrite.app")
	public ModelAndView QNAWrite(@RequestParam String r_num, @RequestParam int f_num,
								 @ModelAttribute QNAVO qnaVO, 
								 HttpSession session){
		
		UserVO userVO = (UserVO)session.getAttribute("loginUser");
		qnaVO.setId(userVO.getId());
		qnaVO.setF_Num(f_num);
		qnaService.qnaWrite(qnaVO);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.addObject("status", "success");
		mav.setViewName("JSON");
		
		return mav;
	}
	
	@RequestMapping(value="/QNAContent.app")
	public ModelAndView QNAContent(int q_Num, String r_num, int f_num){
		
		ModelAndView mav = new ModelAndView();
	 
		qnaVO = qnaService.qnaContent(q_Num);
		
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.addObject("qnaVO", qnaVO);
		mav.setViewName("QNA/qna_content");
		return mav;
	}
	
	@RequestMapping(value="/QNAupdateForm.app")
	public ModelAndView QNAupdateForm(@RequestParam int q_Num, String r_num, int f_num){
		
		ModelAndView mav = new ModelAndView();
		
		qnaVO=qnaService.getQNAUpdate(q_Num);
		
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.addObject("qnaVO", qnaVO);
		mav.setViewName("QNA/qna_updateForm");
		
		return mav;
	}
	
	@RequestMapping(value="/QNAUpdate.app")
	public ModelAndView QNAUpdate(@ModelAttribute QNAVO qnaVO, String r_num, int f_num){
	
		ModelAndView mav = new ModelAndView();
		
		qnaService.qnaUpdate(qnaVO);
		mav.addObject("q_Num", qnaVO.getQ_Num());
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.addObject("status", "success");
		mav.setViewName("JSON");				
		
		return mav;
	}
	
	@RequestMapping(value="/QNADelete.app")
	public ModelAndView QNADelete(@RequestParam int q_Num, String r_num, int f_num){
		ModelAndView mav = new ModelAndView();
		qnaService.qnaDelete(q_Num);
		mav.setViewName("redirect:/getFoodDetail.app?r_num="+r_num+"&f_num="+f_num);
		return mav;
	}
	
	@RequestMapping(value="/QNAreplyForm.app")
	public ModelAndView replyForm(@RequestParam int q_Num, String r_num, int f_num){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.addObject("q_Num", q_Num);
		mav.setViewName("QNA/qna_replyForm");
		return mav;
	}
	
	@RequestMapping(value="/QuestionReply.app")
	public ModelAndView QNAreply(@RequestParam String r_num, int f_num,
								 @ModelAttribute QNAVO qnaVO,
								 HttpSession session) {
		
		System.out.println("요청 : "+qnaVO);
		UserVO userVO = (UserVO)session.getAttribute("loginUser");
		
		qnaVO.setId(userVO.getId());
		qnaVO.setQ_Position(qnaVO.getQ_Num());
		qnaVO.setQ_Dept(1);
		qnaVO.setF_Num(f_num);
		
		System.out.println("set 한 후에 요청 : "+qnaVO);
		
		ModelAndView mav = new ModelAndView();
		
		qnaService.qnaReply(qnaVO);
		
		mav.addObject("r_num", r_num);
		mav.addObject("f_num", f_num);
		mav.addObject("q_Num", qnaVO.getQ_Num());
		mav.addObject("status", "success");
		mav.setViewName("JSON");
		return mav;
	}
	
	
}
