package com.jawang.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jawang.board.BoardDAO;
import com.jawang.board.BoardDTO;
import com.jawang.util.DBConnector;
import com.jawang.util.Pager;

@Repository
public class NoticeDAO implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="noticeMapper.";
	
	public int getNum() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getNum");
	}
	
	@Override
	public int totalCount(Pager pager) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice where "+pager.getKind()+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+pager.getSearch()+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int totalCount = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		return totalCount;
	}

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice where "+pager.getKind()+" like ? order by num desc) N)"
						+ " where R between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+pager.getSearch()+"%");
		st.setInt(2, pager.getStartRow());
		st.setInt(3, pager.getLastRow());
		ResultSet rs = st.executeQuery();
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		while(rs.next()) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			boardDTO.setHit(rs.getInt("hit"));
			ar.add(boardDTO);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	@Override
	public BoardDTO select(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		NoticeDTO noticeDTO = new NoticeDTO();
		if(rs.next()) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		DBConnector.disConnect(rs, st, con);
		return noticeDTO;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into notice values(notice_seq.nextval, ?, ?, ?, sysdate, 0)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getWriter());
		st.setString(3, boardDTO.getContents());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update notice set title=?,contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete from notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

}
