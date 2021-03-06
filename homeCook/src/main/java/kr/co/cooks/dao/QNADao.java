package kr.co.cooks.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.cooks.vo.NoticeVO;
import kr.co.cooks.vo.QNAVO;

public interface QNADao {
	public int getQNACount(int f_num);
	public List<QNAVO> getQNA(int f_num);
	public void qnaWrite(QNAVO qnaVO);
	public QNAVO qnaContent(int q_Num);
	public QNAVO getQNAUpdate(int q_Num);
	public void qnaUpdate(QNAVO qnaVO);
	public void qnaDelete(int q_Num);
	public void qnaDelete1(int q_Num);
	public void qnaReply(QNAVO qnaVO);
}
