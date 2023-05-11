package repository;

import java.util.List;

import domain.ProductVO;

public interface DAO {

	int insert(ProductVO pvo);

	List<ProductVO> selectList();

	ProductVO selectOne(int pno);

	ProductVO update(int pno2);
	
	int delete(int pno1);

	int editUpdate(ProductVO editPvo);


}
