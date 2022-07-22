package contoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.EmartMallDAO;
import model.EmartMallVO;
import model.PersonDAO;
import model.PersonVO;
import view.View;
import view.Program;

public class Controller {

	View eview;
	EmartMallVO evo;
	EmartMallDAO edao;
	PersonVO pvo;
	PersonDAO pdao;
	PersonVO uservo;

	ArrayList<PersonVO> arr = new ArrayList<PersonVO>();


	final String sql_binsert="INSERT INTO BUYLIST VALUES((SELECT NVL(MAX(BID),0)+1 FROM BUYLIST),?,?)";
	//   final String sql_bupdate="UPDATE BUYLIST SET BITEM = ? WHERE BNAME = ?";
	//   final String sql_select="SELECT BUYLIST.*,USERDATA.NAME,EPRODUCT.* FROM BUYLIST,USERDATA,EPRODUCT WHERE BUYLIST.BNAME=? AND BUYLIST.BNAME=USERDATA.SID  AND BUYLIST.BITEM=EPRODUCT.EID";

	// final String sql_buyproduct="SELECT EPRODUCT.* ,CATEGORY FROM EPRODUCT,ECATEGORY WHERE EPRODUCT.ECATEGORY=ECATEGORY.CID";

	Connection conn = null;
	PreparedStatement pstmt = null;

	public Controller() {
		eview = new View();
		evo = new EmartMallVO();
		edao = new EmartMallDAO();
		pvo = new PersonVO();
		pdao = new PersonDAO();
		uservo = new PersonVO();

//		String str1="https://emart.ssg.com/category/main.ssg?dispCtgId=6000095739"; //과일
//		String str2="https://emart.ssg.com/category/main.ssg?dispCtgId=6000095740";//채소
//		String str3="https://emart.ssg.com/category/main.ssg?dispCtgId=6000095499"; //정육
//		String str4="https://emart.ssg.com/category/main.ssg?dispCtgId=6000095500"; //수산물
//		String str5="https://emart.ssg.com/category/main.ssg?dispCtgId=6000095505"; //과자
//
//		eview.Crawling(str1,101);
//		this.deley();
//		eview.Crawling(str2,201);
//		this.deley();
//		eview.Crawling(str3,301);
//		this.deley();
//		eview.Crawling(str4,401);
//		this.deley();
//		eview.Crawling(str5,501);
	}

	public void deley() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void appStart() { // 로그인화면
		while(true) {
			eview.startView();

			if(eview.action==1) { // 로그인 화면
				eview.logIn();
				String id = eview.inputID();
				pvo.setUid(id);
				//System.out.println(pvo.getUid() + "로그 : id 입력");

				String pw = eview.loginPw();
				pvo.setUpw(pw);
				//System.out.println(pvo.getUpw() + "로그 : pw 입력");

				if(pdao.logincheck_user(pvo)) {
					//System.out.println("  로그 : id,pw 입력 후 로그인");
					uservo = pdao.loginCurret_user(pvo);
					mainMenu();
				}else {
					eview.chId();
					continue;
				}
			}
			else if(eview.action==2) { // 회원가입 화면
				eview.join();
				String name = eview.joinName(); // 이름 입력
				pvo.setName(name);
				while(true) {
					String id = eview.inputID(); // id 입력
					pvo.setUid(id);
					if(pdao.idcheck_user(pvo)) {
						eview.exId();
						continue;
					}
					else {
						break;
					}
				}
				while(true) { // pw 입력
					String pw = eview.inpuPw();
					if(pw==null) {
						continue;
					}
					else {
						pvo.setUpw(pw);
						break;
					}
				}
				if(pdao.insert_user(pvo)) {
					eview.successJoin();
					continue;
				}
				else {
					eview.fail();
					continue;
				}
			}
			else {
				eview.exit();
				break;
			}
		}
	}

	public void mainMenu() { // 메인메뉴
		while(true) {
			eview.welcome(uservo);
			eview.mainView();
			if(eview.action==1) {
				// 목록
				EmartMallVO vo = new EmartMallVO(); 
				ArrayList<EmartMallVO> datas = edao.selectAll(vo);
				// 목록 출력
				eview.printList(datas);
			}
			else if(eview.action==2) {
				// 검색 -> 구매까지
				evo.setMinPrice(eview.inputMinPrice());
				evo.setMaxPrice(eview.inputMaxPrice());
				ArrayList<EmartMallVO> datas = edao.selectCon(evo);
				eview.printList(datas);
				// 구매할 상품번호 입력
				evo.seteId(eview.inputNum());
				EmartMallVO vo2=edao.selectOne(evo);
				new Program(vo2);

				if(eview.YorN().equals("Y")) {
					vo2.seteReview(vo2.geteReview()+1);
					edao.updateEmartMall(vo2);
					eview.success();
					if(edao.binsert(uservo,evo)){
						eview.insSucc();
						continue;
					}
					else {
						eview.insFail();
						continue;
					}
					//continue;
				}
				else {
					eview.fail();
					continue;
				}
			}
			else if(eview.action==3) {
				myPage();
			}
			else {
				eview.exit();
				uservo = null;
				break;
			}
		}
	}
	public void myPage() { // 유저 마이페이지
		while(true) {
			//         eview.welcome(uservo);
			eview.mypageView(uservo);

			if(eview.action==1) {
				eview.myProduct();
//				System.out.println(uservo.getSid());
				ArrayList<EmartMallVO> datas=pdao.buylist_user(uservo);
				
				if(datas.size()==0) {
					eview.buyZero();
					continue;
				}

				eview.buyList(datas);
				continue;
			}
			else if(eview.action==2) {
				eview.changePw();
				while(true) { // pw 입력
					String pw = eview.inpuPw();
					if(pw==null) {
						continue;
					}
					else {
						uservo.setUpw(pw);
						break;
					}
				}
				if(pdao.update_user(uservo)) {
					eview.success();
					continue;
				}else {
					eview.fail();
					continue;
				}
			}
			else if(eview.action==3) {
				eview.deleteUser();
				if(pdao.delete_user(uservo)) {
					eview.success();
					uservo = null;
					appStart();
				}
				else {
					eview.fail();
					continue;
				}
			}
			else {
				break;
			}
		}
	}
}