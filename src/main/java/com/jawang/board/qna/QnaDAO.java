package com.jawang.board.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jawang.board.BoardDAO;
import com.jawang.board.BoardDTO;
import com.jawang.util.DBConnector;
import com.jawang.util.Pager;

@Repository
public class QnaDAO implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE="qnaMapper.";
	
	@Override
	public int totalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"totalCount", pager);
	}

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		/*List<QnaDTO> ar = new ArrayList<QnaDTO>();
		Connection con = DBConnector.getConnect();
		String sql = "";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();*/
		
		return sqlSession.selectList(NAMESPACE+"list", pager);
	}

	@Override
	public BoardDTO select(int num) throws Exception {
		/*Connection con = DBConnector.getConnect();
		String sql = "select * from qna where num =?";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		st.setInt(1, num);
		QnaDTO qnaDTO = new QnaDTO();
		if(rs.next()) {
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setRef(rs.getString("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getString("depth"));
		}
		DBConnector.disConnect(rs, st, con);*/
		
		return sqlSession.selectOne(NAMESPACE+"select",num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
	/*	Connection con = DBConnector.getConnect();
		String sql = "insert into qna value(notice_seq.nextval, ?, ?, ?, sysdate, 0,?,?,0)";
		PreparedStatement st = con.prepareStatement(sql);
		QnaDTO qnaDTO = new QnaDTO();
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getWriter());
		st.setString(3, boardDTO.getContents());
		st.setString(4, qnaDTO.getRef());
		st.setInt(5, qnaDTO.getStep());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);*/
		
		return sqlSession.insert(NAMESPACE+"insert", boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		/*Connection con = DBConnector.getConnect();
		String sql = "update from qna set title=?,contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		int result = st.executeUpdate();*/
		
		return sqlSession.update(NAMESPACE+"update",boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		/*Connection con = DBConnector.getConnect();
		String sql = "delete from qna where num =?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);*/
		
		return sqlSession.delete(NAMESPACE+"delete",num);
	}
	
	public int reply(QnaDTO qnaDTO) throws Exception{
		/*Connection con = DBConnector.getConnect();
		String sql = "";
		PreparedStatement st = con.prepareStatement(sql);*/

		return sqlSession.insert(NAMESPACE+"reply", qnaDTO);
	}
	
	public int replyUpdate(QnaDTO qnaDTO) throws Exception{
		return sqlSession.update(NAMESPACE+"replyUpdate", qnaDTO);
		
		
	}
	

}
