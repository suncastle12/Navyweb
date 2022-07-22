package view;

import java.util.InputMismatchException;
import java.util.Scanner;


public class PobbangView {
	Scanner sc = new Scanner(System.in);
	public int action = 0;
	public final int adminNum = 1234;

	// 자판기 환영 로직==========
	public void welcome() {
		System.out.println();
		System.out.println("☆★☆★포켓몬 빵 자판기★☆★☆");
	}

	// 메인메뉴=================
	public void mainMenu() {
		while(true) {
			System.out.println();

			System.out.println("포켓몬빵 자판기에 잘 왔단다!");
			System.out.println("======================");
			System.out.println("\t1. 빵 목록");
			System.out.println("\t2. 빵 뽑기");
			System.out.println("\t3. 뽑은 빵 목록");
			System.out.println("\t4. 자판기 종료");
			System.out.println("======================");
			System.out.println("이용할 서비스 번호를 입력하도록!");
			// [변경 후]
			System.out.print(">> ");

			try {
				action = sc.nextInt();
				if(action >= 1 || action <= 4 || action == adminNum) {
					break;
				}
			}
			catch(Exception e) {
				sc.nextLine();
				System.out.println("잘못 입력했으니 다시 입력하도록!");
				continue;
			}
		}
	}

	// ID 입력받는 로직=========
	public String insertID() {
		System.out.println();
		System.out.println("당신의 이름은?");
		String id = sc.nextLine();
		return id;
	}
	// 이름 띄우는 로직===========
	public void mainName(String name) {
		System.out.println();
		System.out.println("\t★"+name + "군 어서오게나★");
	}

	// money 입력받는 로직=======
	public int insertMoney() {
		while(true) {
			System.out.println("포켓머니를 투입");
			System.out.println("얼마를 넣을까?");
			System.out.print(">> ");
			try {
				// 돈을 String으로 받아서 int로 변경
				//				int money = sc.nextInt();
				String money = sc.nextLine();

				int moneyInt = Integer.parseInt(money);

				if(moneyInt < 1500) {
					System.out.println("기본금액 1500원 "
							+ "이상 입력해주세요..");
					System.out.println();
					continue;
				}
				return moneyInt;
			}
			catch(InputMismatchException e) {
				System.out.println("원을 제외한 "
						+ "숫자(금액)만 입력해주세요..");
				continue;
			}
			catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
	}

	public void printString(String i) {
		System.out.println(i);
	}

	// 빵 목록 로직===============
	public void breadList() {
		System.out.println();
		System.out.println("마음에 드는 포켓몬빵이"
				+ " 있었으면 좋겠구나");

		System.out.println("====================");
	}

	// 빵 뽑기 전 로직===============
	public void beforeGacha() {
		System.out.println("오늘의 랜덤빵은 뭘까~요?");
		System.out.println("피~피카츄~");
	}

	// 빵을 뽑은 후 로직=============
	public void afterGacha(String bread) {
		System.out.println(bread + "이로구나~");
	}

	// 뽑은 빵 리스트 로직===========
	public void gachaList() {
		System.out.println("☆★☆뽑은 빵 목록☆★☆");
		System.out.println();
	}

	// 빵을 확정적으로 뽑은 후의 로직======
	public void customerBuy(String bread) {
		System.out.println();
		System.out.println("\t☆★"+bread +" 넌 내꺼야!!★☆");
	}

	// 다시 뽑을지 묻는 로직=====
	public void againGacha() {
		System.out.println("다시 뽑겠나?");
	}

	// yes or no 로직=======
	public void yesOrNo() {
		System.out.println("YES / NO");
		// Y / N이 보기 좋긴한데..
	}

	// 다시뽑을때 y/n 입력받는 로직========
	public boolean selectTryGame() {
		while (true) {
			sc.nextLine();
			String choice = sc.nextLine();
			choice = choice.toUpperCase();
			if(choice.charAt(0)=='Y') {
				return true;
			}
			else if (choice.charAt(0)=='N') {
				return false;
			}
			System.out.println("다시 입력하게나");
		}
	}

	// 종료로직과 함께 돈 돌려받는 로직========
	public void returnMoney(int money) {
		System.out.println(money + "원 남았네.");
		System.out.println("돌려주겠네");
	}

	// 남은 금액 + 남은 횟수출력 로직========
	public void leftMoneyCnt(int money, int cnt) {
		System.out.println("남은 포켓머니는 " + money + "이고,");
		System.out.println("남은 횟수는 " + cnt + "이네");
	}

	// 포켓머니가 부족할 경우==========
	public void moneyleft(int money) {
		System.out.println("포켓머니가 " + money + "원 남아 더 돌릴 수 없네..");
	}

	// 횟수가 없을 때의 경우
	public void cntZero() {
		System.out.println("남은 횟수가 없네..");
	}
	
	// 뽑은 빵 리스트 없을 때=======
	public void gachaListZero() {
		System.out.println("뽑은 빵이 없네! 뽑기를 하고 오게나");
	}
	
	// 빵 재고 제로
	public void breadZero() {
		System.out.println("빵의 재고가 없네...");
		System.out.println("곧 채워놓도록 하겠네..");
	}
	

	// 구매자 종료 로직===============
	public void customerEnd() {
		System.out.println("나중에 또 보도록 하지!");
	}

	// 이스터에그 로직=============
	public void roketEst() {
		// [변경 후]
		System.out.println();
		System.out.println("무슨 빵이냐고 물어보신다면..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("대답해드리는게 인지상정!");
		// [변경 후]
		System.out.println();
	}

	// ...를 delay 시킨 버전==========
	public void dotDelay() {
		for(int i = 0; i < 3; i++) {
			System.out.print(".");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// 빵 삭제 로직(관리자모드)==============
	public void deleteBread() {
		System.out.println("===포켓몬빵 삭제 메뉴===");
		System.out.print("삭제할 빵 번호 입력 ");
	}

	/*
	// 빵 삭제 로직 실행 결과(성공시)========
	public void deleteT() {
		System.out.println(" 삭제 성공!");
	}

	// 빵 삭제 로직 실행 결과(실패시)========
	public void deleteF() {
		System.out.println(" 삭제 실패..");
	}
	 */
	// 빵 삭제 번호 값이 옳지 않을 경우=======
	public void deleteNumWrong() {
		System.out.println("번호를 다시 확인해주세요..");
	}

	// int / String 입력문======
	public int inputInt() {
		System.out.print(">> ");
		int num = sc.nextInt();
		return num;
	}

	// 엔터삭제
	public String inputString() {
		System.out.print(">> ");
		sc.nextLine();
		String str = sc.nextLine();
		return str;
	}

	// 관리자메뉴 =====
	// 관리자메뉴 =====
	public void adminMenu(){
		while(true) {
			// while문
			System.out.println();

			//System.out.println("================");
			System.out.println("관리자모드입니다."); 
			System.out.println("================");
			System.out.println("1.빵추가");
			System.out.println("2.재고추가");
			System.out.println("3.빵 삭제");
			System.out.println("4. 종료");
			System.out.println("================");
			System.out.print(">> ");

			try{
				action = sc.nextInt();
				if(action >= 1 || action <= 4){
					break;
				}
				System.out.println("범위 외 입력");
			}
			catch(Exception e){
				sc.nextLine();
				System.out.println("정수를 입력해주세요");
				continue;
			}
		}
	}

	// 재고 추가 로직
	public int breadcntNum() {
		System.out.println("재고를 추가할 빵의 번호를 입력해주세요");
		int breadnum=sc.nextInt();
		return breadnum;
	}
	public int breadcntPlus() {
		System.out.println("추가할 빵의 갯수를 입력해주세요");
		int breadcntup=sc.nextInt();
		return breadcntup;
	}
	// 빵추가 로직
	public String breadAdd () {
		System.out.println("빵의 이름을 입력해주세요");
		sc.nextLine();
		String breadname = sc.nextLine();
		return breadname;
	}
	// 수행을 성공했다/ 실패했다
	public void success() {
		System.out.println("수행 성공");
	}
	public void fail(){
		System.out.println("수행 실패");
	}
	// 이미존재하는 빵 로직
	public void overlap() {
		System.out.println("이미 존재하는 빵입니다");
	}
	//관리자 종료 로직
	public void adminEnd(){
		System.out.println("관리자모드를 종료합니다.");
		// 나온 빵 이름 출력해주는 로직
	}
}
