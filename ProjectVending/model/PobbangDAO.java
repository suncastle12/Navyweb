package model;

import java.util.ArrayList;
import java.util.Random;

public class PobbangDAO {
	ArrayList<PobbangVO> arr=new ArrayList<PobbangVO>();
	private int pk=101;

	public PobbangDAO() { // 샘플데이터
		String[] name = 	{"피카츄빵 피카피카 촉촉 치즈케익",
				"파이리빵 파이리의 화르륵 핫소스팡",
				"꼬부기빵 꼬부기의 달콤파삭 꼬부기빵",
				"로켓단빵 돌아온 로켓단 초코롤",
				"푸린빵 푸린의 폭신폭신 딸기 크림빵",
				"디그다빵 디그다의 딸기 카스타드빵",
				"고오스빵 돌아온 고오스 초코케익",
		"발챙이빵 발챙이의 빙글빙글 밀크요팡"};

		for(String datas:name) {
			PobbangVO vo=new PobbangVO();
			vo.setName(datas);
			vo.setCnt(5);
			vo.setPrice(1500);
			vo.setNum(pk++);
			arr.add(vo);
		}

	}

	//	totalCnt()-모든 빵의 재고가 0이 되면 뽑기 불가능을 알리기 위함
	public boolean totalCnt(PobbangVO vo) {
		int total=0;
		for (int i = 0; i < arr.size(); i++) {
			total+=arr.get(i).getCnt(); // 재고량 총 갯수
		}
		if(total!=0) { // 전체 재고량이 0이 아니라면
			return true; // true
		}
		return false; // 젠처 재고량이 0이면 false

	}

	//	insert()-메뉴추가에 사용
	public boolean insert(PobbangVO vo) {
		if(vo.getName()!=null) { // C파트에서 이름과 재고량만 추가
			arr.add(vo);
			vo.setPrice(1500); // 금액은 모든 빵 1500원 통일
			return true;
		}
		return false; // 이름값이 null 이면 insert 실패
	}

	//	update()-재고설정 // boolean값으로 return 
	//	cnt 수량 추가 후 return true / 수행실패 return false
	public boolean update(PobbangVO vo) {
		for (int i = 0; i < arr.size(); i++) {
			if(vo.getNum()==arr.get(i).getNum()) { // 입력받은 num과 같은 pk를 가진 리스트 요소 검색
				arr.get(i).setCnt(arr.get(i).getCnt()+vo.getCnt()); // 해당 요소에 입력받은 vo의 cnt 추가
				return true; // 업데이트 성공
			}
		}
		return false; // 업데이트 실패
	}

	//	delete()-메뉴삭제에 사용
	public boolean delete(PobbangVO vo) {
		for (int i = 0; i < arr.size(); i++) {
			if(vo.getNum()==arr.get(i).getNum()) { // 입력받은 num과 같은 pk를 가진 리스트 요소 검색
				arr.remove(i); // 해당 요소 삭제
				return true; // 삭제 완료
			}
		}
		return false; // 삭제 실패
	}

	//	selectAll()-빵 목록에 사용 // cnt가 0인 빵은 제외하고 리스트에 넣어서 return
	public ArrayList<PobbangVO> selectAll(PobbangVO vo) {
        ArrayList<PobbangVO> datas=new ArrayList<PobbangVO>();
        for(int i = 0; i < arr.size(); i++) { 
            if(vo==null) { // vo가 null이면 재고량 0인 품목 제외하고 모든 품목 추가
                if(arr.get(i).getCnt()!=0) {
                    datas.add(arr.get(i));
                    continue;
                }
            }
             // null이 아니라면 입력받은 재고량 이상을 가진 품목 추가
            if(arr.get(i).getCnt()>=vo.getCnt()) {
                datas.add(arr.get(i));
            }
        }
        return datas; // 해당 리스트 반환
    }

	//	randomBbang()-DB에서 랜덤 요소 1개 뽑기 String으로 빵 이름 return / selectOne()의 기능 수행 후 뽑은 빵의 재고량 -1
	public String randomBbang(PobbangVO vo) {
		Random r=new Random();
		int rand;
		String str="";
		while(true) {
			rand=r.nextInt(arr.size()); // 랜덤값을 리스트의 길이 만큼 부여
			if(arr.get(rand).getCnt()!=0) { // 해당 요소의 cnt가 0이 아닐때만 if문 안으로
				arr.get(rand).setCnt(arr.get(rand).getCnt()-1);  // 해당 요소의 재고를 -1
				break;
			}
		}
		str=arr.get(rand).getName(); // 해당 요소의 이름 저장
		return str; // 이름을 String형태로 반환
	}

	public String cutName(PobbangVO vo) { // 빵 이름이 너무 길어 이름을 잘라줄 메소드
		String str=""; // 빵 이름의 앞부분만 저장할 변수
		if(vo.getName().contains("빵")) { // 제품 이름에 "빵"이 있다면
			for (int i = 0; i < vo.getName().length(); i++) {
				String tmp=vo.getName().charAt(i)+"";
				if(tmp.equals("빵")) {
					str+=vo.getName().charAt(i);
					return str;
				}
				str+=vo.getName().charAt(i);
			}
		}
		return null; // 이름에 "빵"이 포함되어있지 않음
	}
}


