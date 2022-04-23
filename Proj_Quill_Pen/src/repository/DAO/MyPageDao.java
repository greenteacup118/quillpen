package repository.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import myBatis.MyBatis;
import repository.DTO.BoardBean;
import repository.DTO.CmntBean;
import repository.DTO.LikeBean;
import repository.DTO.MemberBean;
import repository.DTO.SubsBean;
import repository.DTO.WriterBean;

public class MyPageDao {
	private static String namespace = "myBatis.mapper.myPageMapper.";

	SqlSessionFactory sqlSessionFactory = MyBatis.getSqlSessionFactory();

	//////////////////////////// 로그인 유효성 검사 시작///////////////////////////////
	// 로그인 여부 확인하기 (select)
	public boolean isLogin(String uId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		boolean check = false;

		try {
			// 결과가 1이면 true 아니면 false
			int result = sqlSession.selectOne(namespace + "isLogin", uId);
			check = result == 1 ? true : false;

			return check;
		} finally {
			sqlSession.close();
		}
	}
	//////////////////////////// 로그인 유효성 검사 끝////////////////////////////////
	
	//////////////////////////// 아이디 통해서 작가명 확인 시작///////////////////////////////
	// 작가명 가져오기
	public String findWriter(String uId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.selectOne(namespace + "findWriter", uId);
		} finally {
			sqlSession.close();
		}
	}
	//////////////////////////// 아이디 통해서 작가명 확인 끝////////////////////////////////
	
	//////////////////////////// 회원정보 관련 시작 ////////////////////////////////
	// 회원정보 보여주기
	public MemberBean selectMember(String uId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		
		try {
			return sqlSession.selectOne(namespace + "selectMember", uId);

		} finally {
			sqlSession.close();
		}
	}
	
	// 회원정보 수정하기
	public int updateMember(MemberBean bean) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		
		try {
			return sqlSession.update(namespace + "updateMember", bean);

		} finally {
			sqlSession.close();
		}
	}
	
	//////////////////////////// 회원정보 관련 끝 //////////////////////////////////
	
	//////////////////////////// 프로필 관련 시작 ////////////////////////////////
	// 프로필 보여주기 (writer 테이블)
	public WriterBean selectWriter(String writer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		
		try {
			return sqlSession.selectOne(namespace + "selectWriter", writer);

		} finally {
			sqlSession.close();
		}
	}
	
	// 프로필 생성하기 (writer 테이블)
	public int insertWriter(WriterBean bean) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		
		try {
			return sqlSession.insert(namespace + "insertWriter", bean);

		} finally {
			sqlSession.close();
		}
	}
	
	// 프로필 수정하기 (writer 테이블)
	public int updateWriter(WriterBean bean) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		
		try {
			return sqlSession.update(namespace + "updateWriter", bean);

		} finally {
			sqlSession.close();
		}
	}
	//////////////////////////// 프로필 관련 끝 //////////////////////////////////
	
	//////////////////////////// 목록 관련 시작 //////////////////////////////////
	// 내가 쓴 게시물
	public List<BoardBean> selectMyBoardList(String writer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();	
		
		try {
			return sqlSession.selectList(namespace+"selectMyBoardList", writer);
		} finally {
			sqlSession.close();
		}
	}
	
	// 구독한 작가 목록
	public List<SubsBean> selectSubWriterList(String uId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();	
		
		try {
			return sqlSession.selectList(namespace+"selectSubWriterList", uId);
		} finally {
			sqlSession.close();
		}
	}	
	
	// 추천한 글목록
	public List<LikeBean> selectLikeBoardList(String uId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();	
		
		try {
			return sqlSession.selectList(namespace+"selectLikeBoardList", uId);
		} finally {
			sqlSession.close();
		}
	}	
	
	// 내가 쓴 댓글 목록
	public List<CmntBean> selectMyCmntList(String uId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();	
		
		try {
			return sqlSession.selectList(namespace+"selectMyCmntList", uId);
		} finally {
			sqlSession.close();
		}
	}
	//////////////////////////// 목록 관련 끝 ////////////////////////////////////
}
