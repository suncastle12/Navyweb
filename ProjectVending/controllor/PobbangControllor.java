package controllor;

import java.util.ArrayList;
import model.PersonDAO;
import model.PersonVO;
import model.PobbangDAO;
import model.PobbangVO;
import view.PobbangView;

public class PobbangControllor {
	private PobbangDAO pobbang;
	private PersonDAO person;
	private PobbangView view;

	public PobbangControllor() {
		pobbang = new PobbangDAO();
		person = new PersonDAO();
		view = new PobbangView();

	}

	// 1.빵 목록 2.빵 뽑기 3.뽑은 빵 목록 4.자판기 종료
	public void startApp() { 

		PersonVO pvo = new PersonVO();
		PobbangVO bvo = new PobbangVO();

		String dummy = "";

		String id = view.insertID();
		pvo.setName(id);
		int money = view.insertMoney();
		pvo.setMoney(money);
		pvo.setCnt(5);
		view.mainName(id);

		while(true) { // 무한반복

			view.welcome();
			view.mainMenu(); // 사용자에게 보여줄 첫 화면 출력

			if(view.action == 1) { // 빵 목록
				view.breadList();

				ArrayList<PobbangVO> datas = pobbang.selectAll(bvo);
				for (int i = 0; i < datas.size(); i++) {
					view.printString(datas.get(i).getName()); // 빵 목록
				} 

				continue; // 다시 메인메뉴
				// 전체 목록 출력, 재고가 0이면 출력 x 

			}else if(view.action == 2) { // 빵 뽑기

				if(person.checkMoney(pvo)) { // 만약에 소지금이 1500원 이상이니?

					if(person.checkCnt(pvo)) { // 횟수도 0 이상이니?

						person.update(pvo); // money-1500, cnt -1

						view.beforeGacha(); // 오박사 대사 출력
						String name = pobbang.randomBbang(bvo); // name에 랜덤 빵 담기
						view.afterGacha(name); // 랜덤으로 담은 빵 출력
						dummy += name + "\n"; // dummy에 담기 
						// 순차적으로 뽑힌 빵은 dummy에 계속 쌓임

						if(name.substring(0,4).equals("로켓단빵")) {
							view.dotDelay();
							view.roketEst();
						}
						
						view.customerBuy(name); // 넌 내꺼야 대사 출력
						view.againGacha(); // 다시 뽑겠니? 대사 출력
						view.yesOrNo(); // YES / NO ? 대사 출력
						view.selectTryGame(); // y or n 로직
				

					}else {						
						view.cntZero(); // 횟수가 없으면
						continue;	
					}
				}else {
					view.moneyleft(pvo.getMoney()); // 돈이 부족하면
					continue;
				}

			}else if(view.action == 3) { // 뽑은 빵 목록
				// 지금까지 뽑은 빵 리스트 확인
				if(dummy=="") {
					view.gachaListZero();
					continue;
				}			
					view.gachaList();
					view.printString(dummy);
				

			}else if(view.action == 4) { // 자판기 종료

				view.leftMoneyCnt(money, pvo.getCnt());
				view.returnMoney(pvo.getMoney());
				view.customerEnd();
				view.dotDelay();
				break;

			}else if(view.action == 1234) { // 관리자모드

				while(true) {

					view.adminMenu(); // 관리자 메뉴

					if(view.action == 1) { // 빵 추기

						String name = view.breadAdd(); // 빵 이름 입력받기
						bvo.setName(name); // 입력받은 이름 값 넣어주기
						int cnt = view.breadcntNum(); // 빵 재고 입력받기
						bvo.setCnt(cnt); // 빵 재고 값 넣어주기

						if(pobbang.insert(bvo)) { // 만약에 추가를 잘 했으면
							view.success(); // 성공
						}else { // 아니면
							view.fail(); // 실패
						}
						continue; // 다시 관리자 메뉴로 돌아가기
					}



					else if(view.action == 2) { // 빵 재고 추가

						int num = view.breadcntNum(); // 빵 번호 입력받기
						bvo.setNum(num); // 입력받은 번호 넣어주기
						int cnt = view.breadcntPlus(); // 빵 재고 입력받기
						bvo.setCnt(cnt); // 입력받은 재고 넣어주기

						if(pobbang.update(bvo)) { // update 수행을 잘 했다면
							view.success(); // 성공
						}else { // 아니면
							view.fail(); // 실패
						}

					}else if(view.action == 3) { // 빵 삭제
						
						
						view.deleteBread();							
						int num = view.inputInt();
						bvo.setNum(num);
						
						boolean flag = pobbang.delete(bvo);
						
						if(flag) {
							view.success();
						}else {
							view.fail();
						}
						

					}else if(view.action == 4) {
						view.adminEnd();
						break;
					}
				} // 관리자모드 while 끝
			} // action 1234 
		}

	}
}









