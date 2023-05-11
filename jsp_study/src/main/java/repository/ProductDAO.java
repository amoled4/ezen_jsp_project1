package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.ProductVO;
import orm.DatabaseConnector;

public class ProductDAO implements DAO {
	// DB Connector
	private Connection conn;
	// DB 연결 후 sql 전송 객체
	private PreparedStatement pst;
	// 쿼리문 작성 변수
	private String query = "";
	
	// 생성자
	public ProductDAO() {
		// 싱글톤 방식의 객체 생성 방식
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		conn = dbc.getConnection();
	}
	
	@Override
	public int insert(ProductVO pvo) {
		System.out.println(">>> DAO 접근 완료");
		query = "insert into product(pname, price, madeby) values(?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, pvo.getPname());
			pst.setInt(2, pvo.getPrice());
			pst.setString(3, pvo.getMadeby());
			// executeUpdate() : insert, update, delete
			// executeQuery() : select
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert error");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ProductVO> selectList() {
		System.out.println(">>> DAO 접근 완료");
		query = "select * from product order by pno desc";
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				list.add(new ProductVO(rs.getInt("pno"), rs.getString("pname"), rs.getString("regdate")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("selectList error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductVO selectOne(int pno) {
		System.out.println(">>> DAO 접근 완료");
		query = "select * from product where pno=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return new ProductVO(rs.getInt("pno"), rs.getString("pname"), 
						rs.getInt("price"), rs.getString("regdate"), rs.getString("madeby"));
			}
		} catch (SQLException e) {
			System.out.println("selectOne error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductVO update(int pno2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int editUpdate(ProductVO editPvo) {
		System.out.println(">>> DAO 접근 완료");
		query = "update product set pname=?, price=?, madeby=? where pno=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, editPvo.getPname());
			pst.setInt(2, editPvo.getPrice());
			pst.setString(3, editPvo.getMadeby());
			pst.setInt(4, editPvo.getPno());
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("edit error");
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int delete(int pno1) {
		System.out.println(">>> DAO 접근 완료");
		query = "delete from product where pno=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno1);
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete error");
			e.printStackTrace();
		}
		return 0;
	}



}
