import java.util.ArrayList;

public class Card {	
	private Suit suit;
	enum Suit {
		Club, Diamand, Heart, Spade
	};//����� 
	
	private int rank; // 1~13

//�L�X�@�i�d�����M��(�̷Ӧ��S�O���X��(AJQK))
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

	//s�����A�令Suit
	public Card(Suit s, int r) {
		suit = s;
		rank = r;
	}

	//getSuit()���Ǧ^suit�� ��ƫ��A��Suit
	public Suit getSuit() {
		return suit;
	}
	//getRank()���Ǧ^rank��,��ƫ��A��int
	public int getRank() {
		return rank;
	}
}
