import java.util.ArrayList;

public class Card {	
	private Suit suit;
	enum Suit {
		Club, Diamand, Heart, Spade
	};//為花色 
	
	private int rank; // 1~13

//印出一張卡的花色和值(依照有特別號碼的(AJQK))
	public void printCard() {
		
		if (rank == 1) {
			System.out.println(suit + "," + "A");
		} else if (rank == 11) {
			System.out.println(suit + "," + "J");
		} else if (rank == 12) {
			System.out.println(suit + "," + "Q");
		} else if (rank == 13) {
			System.out.println(suit + "," + "K");
		} else {
			System.out.println(suit + "," + rank);
		}
	}

	//s的型態改成Suit
	public Card(Suit s, int r) {
		suit = s;
		rank = r;
	}

	//getSuit()為傳回suit值 資料型態為Suit
	public Suit getSuit() {
		return suit;
	}
	//getRank()為傳回rank值,資料型態為int
	public int getRank() {
		return rank;
	}
}
