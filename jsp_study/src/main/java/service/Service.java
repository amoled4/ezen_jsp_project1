package service;

import java.util.List;

import domain.ProductVO;

public interface Service {

	int register(ProductVO pvo);

	List<ProductVO> list();

	ProductVO detail(int pno);

	ProductVO modify(int pno2);
	
	int edit(ProductVO editPvo);
	
	int remove(int pno1);



}
