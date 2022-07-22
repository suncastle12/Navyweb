package model;

public class PersonDAO {
	private PobbangVO pobbangVO;
	
	/*
	private String name; // 사용자 이름
	private int money; // 투입 금액
	private int cnt; // 인당 뽑기 횟수
	 */

	public boolean checkMoney(PersonVO vo) {
        // 포빵 금액보다 남은 금액이 많은지, 횟수가 남았는지 체크
        // 포빵 금액보다 무조건 커야 랜덤을 돌릴 수 있음

        if(vo.getMoney()>= 1500) { // 사람 소지한 돈>=포빵의가격
            return true;
        }

        return false;
    }

    public boolean checkCnt(PersonVO vo) {

        if(vo.getCnt()>0) { // 랜덤돌릴 수 있는 횟수>0
            return true; // 
        }

        return false;
    }

	public void update(PersonVO vo) {
		vo.setMoney(vo.getMoney()-1500);
		vo.setCnt(vo.getCnt()-1);
		
		// 돈과 뽑기횟수 동시 차감
	}
}
	
	/*
	public boolean reCheck(int money, int cnt) {
		// 다시 뽑겠냐고 물어보는 부분에 들어가는 로직

		if(money>=modelVO.getPrice() && cnt>0) {
			return true; // 사람소지돈>=포빵가격&&랜덤돌릴수있는횟수0>0 면, 다시 뽑겠다고 물어보기로 연결
		}

		return false; // 메인화면으로 돌아가기로 연결



		만약에(남은금액이 포빵금액보다 많고 횟수가 0보다 크면) {
			금액을 돌려받겠는지 물어보게끔 해야함
		}
	 */
