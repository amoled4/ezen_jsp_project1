package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import service.ProductService;
import service.Service;


public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 상품등록, 상품리스트, 상품상세, 상품수정, 상품삭제
	// 컨트롤러 -> 서비스, 서비스 -> DAO, DAO -> DBconnection
	private Service svc;
	
	// 생성자
    public ProductController() {
    	svc = new ProductService();
    }

    
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 주 작업 영역
		// get / post 모든 처리는 service에서 처리함
		// post 방식으로 데이터 처리를 할 때 한글이 깨지는 것을 방지
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();        // 요청 경로(원하는 서비스) 컨트롤러 주소/요청 서비스
		System.out.println(">>>uri : "+uri);
		String context = req.getContextPath();   // 프로젝트명
		System.out.println(">>>context : "+context);
		
		// 객체를 보내야 하는 목적지 주소
		String destPage="";
		
		// 오는 요청에 대한 처리
		switch(uri) {
		case "/register.pd":
			// /폴더명/jsp파일명.jsp
			destPage = "/register.jsp";
			break;
			
		case "/insert.pd":
			// DB연결, 등록처리
			// jsp에서 가져온 객체(이름, 가격, 세부정보)를 가지고 왔다
			// service에게 가져온 데이터를 객체화하여 db에 저장해달라고 요청
			// 1. 객체를 생성한다
			String pname = req.getParameter("pname");
			int price = Integer.parseInt(req.getParameter("price"));     // getParameter는 String 처리이므로 parseInt 필요
			String madeby = req.getParameter("madeby");
			ProductVO pvo = new ProductVO(pname, price, madeby);
			
			// 생성자가 없다면
//			ProductVO pvo = new ProductVO();
//			pvo.setPname(pname);
//			...
			
			// 2. service에게 객체 주고 요청
			int isOk = svc.register(pvo);        // 리턴값 : 1개의 행 등록
			System.out.println(">>> 상품등록 > "+(isOk > 0 ? "성공":"실패"));
			
			destPage = "/index.jsp";
			break;
			
		case "/list.pd":
			List<ProductVO> list = new ArrayList<ProductVO>();
			list = svc.list();
			req.setAttribute("list", list);
			System.out.println(">>> 상품리스트 읽어옴");
			destPage = "/list.jsp";
			break;
			
		case "/detail.pd":
			int pno = Integer.parseInt(req.getParameter("pno"));
			ProductVO p = new ProductVO();
			p = svc.detail(pno);
			req.setAttribute("pvo", p);
			System.out.println(">>> 상품 상세 정보 읽어옴");
			destPage = "/detail.jsp";
			break;
			
		case "/modify.pd":
			destPage = "/modify.jsp";
			int pno2 = Integer.parseInt(req.getParameter("pno"));
			req.setAttribute("pvo",svc.detail(pno2));
			System.out.println(">>> 상품 수정 페이지 읽어옴");
			
			break;
			
		case "/edit.pd":
			int pno3 = Integer.parseInt(req.getParameter("pno"));
			String pname1 = req.getParameter("pname");
			int price1 = Integer.parseInt(req.getParameter("price"));     // getParameter는 String 처리이므로 parseInt 필요
			String madeby1 = req.getParameter("madeby");
			ProductVO editPvo = new ProductVO(pname1, price1, madeby1);
			
			int editIsOk = svc.edit(editPvo);
			System.out.println(">>> 상품수정 > "+(editIsOk > 0 ? "성공":"실패"));
			
			destPage = "/list.pd";
			break;
			
		case "/remove.pd":
			int pno1 = Integer.parseInt(req.getParameter("pno"));
			
			int isOk1 = svc.remove(pno1);        // 리턴값 : 1개의 행 등록
			System.out.println(">>> 상품삭제 > "+(isOk1 > 0 ? "성공":"실패"));
			destPage = "/list.pd";
			break;
			
		}
		
		// 웹의 목적지 주소로 연결해주는 객체
		// destPage로 이동시 응답 객체를 싣고 가야 함
		RequestDispatcher rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 오는 값을 체킹
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 오는 값을 체킹
		service(request, response);
	}

}
