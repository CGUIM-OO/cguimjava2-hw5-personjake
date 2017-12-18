import java.util.ArrayList;
//B0544233 ���2�A   ���a�
public class Table {
	private Deck allcard;
	private Player[] allplayer;
	private Dealer dealer;

	private final int MAXPLAYER = 4;
	private int[] pos_betArray = new int[MAXPLAYER];
	private int pos;
	private Player p;

	private ArrayList<Card> playerCard;
	private ArrayList<Card> dealerCard;
	boolean hit = false;

	public Table(int nDeck) {
		allcard = new Deck(nDeck);
		allplayer = new Player[MAXPLAYER];
	}
//����Table��constructor�A�ç⪱�a��Jallplayer���}�C��
	public void set_player(int pos, Player p) {

		this.pos = pos;
		this.p = p;
		allplayer[pos] = p;

	}
//�^�ǩҦ����a
	public Player[] get_player() {
		return allplayer;
	}
//dealer��setter
	public void set_dealer(Dealer d) {
		dealer = d;
	}
//���odealer���ĤG�i�P�@�����P���j�a��
	public Card get_face_up_card_of_dealer() {
		return dealerCard.get(1);
	}
//�C�@�Ӫ��a�nsayhello,�äU�`�A�A�N�Ҧ����a�ӧO�U���`�s�bpos_betArray���}�C��
	private void ask_each_player_about_bets() {
		for (int i = 0; i < MAXPLAYER; i++) {
			allplayer[i].sayHello();
			allplayer[i].makeBet();
			pos_betArray[i] = allplayer[i].makeBet();
		}
	}
//�o�P�����a�M���a
	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < MAXPLAYER; i++) {
			playerCard = new ArrayList<Card>();//�����playerCard�MdealerCard
			dealerCard = new ArrayList<Card>();
			playerCard.add(allcard.getOneCard(true));//���a���o�Ĥ@�i�P
			playerCard.add(allcard.getOneCard(true));//���a���o�ĤG�i�P
			allplayer[i].setOneRoundCard(playerCard);//�N���o���Ʃ�J�U�۪�OneRoundCard
			
			for (Card a : playerCard) {
				System.out.print("player face up card is");
				a.printCard();//�L�X�P
		}}
		dealerCard.add(allcard.getOneCard(true));//���a���o�Ĥ@�i�P
		dealerCard.add(allcard.getOneCard(true));//���a���o�ĤG�i�P
		dealer.setOneRoundCard(dealerCard);//�N���o���Ʃ�J���a��OneRoundCard

		Card a=get_face_up_card_of_dealer();//�I�sget_face_up_card_of_dealer��k���o�ĤG�i�P�@�����P�æL�X
		System.out.print("Dealer's face up card is: ");
		a.printCard();
		
		}
	

	private void ask_each_player_about_hits() {
		
		for (int i = 0; i < MAXPLAYER; i++) {playerCard = new ArrayList<Card>();
			do {hit = false;//�w�����]hit ��false
				hit = allplayer[i].hit_me(this); // this���o�iTable�A�öǤJhit_me,�P�_���a�O�_�٭n�P
				if (hit) {
					playerCard=allplayer[i].getOneRoundCard();//playerCard�]�����a��getOneRoundCard
					playerCard.add(allcard.getOneCard(true));//���a�[�J�s���@�i�P
					allplayer[i].setOneRoundCard(playerCard);//�A�N���a�s�����P�]�^OneRoundCard
					System.out.print("Hit! ");
					System.out.println(allplayer[i].getName() + "'s Cards now:");
					for (Card c : playerCard) {
						c.printCard();
					}
				} else {
					System.out.println(allplayer[i].getName() + ", Pass hit!");
					System.out.println(allplayer[i].getName() + ", Final Card:");
					for (Card c : playerCard) {
						c.printCard();
					}
				}
			} while (hit);//�Yhit ��false�Y���Xdowhile�j��
		}
		
	}

	//dealer�O�_�n��
	private void ask_dealer_about_hits() {
		
		do {hit = false;
		hit = dealer.hit_me(this); // this ��ܶǤJ�o�i��l�A�öi�J�P�_�O�_�n�P
		if (hit) {dealerCard = new ArrayList<Card>();
			dealerCard=dealer.getOneRoundCard();//dealerCard�]�����a��getOneRoundCard
			dealerCard.add(allcard.getOneCard(true));//���a�[�@�i�P
			dealer.setOneRoundCard(dealerCard);	//�N�s���P�^�]�^dealer��OneRoundCard
			
			}
		 else {
			for (Card c : dealerCard) {
				c.printCard();
			}//�L�X�P
		}
	} while (hit);
		
		System.out.println("Dealer's hit is over!");
	}
//�P�_Ĺ�ٿ���a�íp��C���᪺�w�X
	private void calculate_chips() {
		int dealertotal = dealer.getTotalValue();
		System.out.println("Dealer's card value is: " + dealertotal+" Cards:");
		dealer.printAllCard();
		
		//6�ر���: 1.���a>21���a>21�h���� 2.���a<=21���a>21�h���aĹ3.���a>21���a<=21 �h���a��4.���a�I��>���a�I�ƨ�̩M�p��21���aĹ5.���a�I��<���a�I�ƨ�̩M�p��21���a��6.��l���ΨS�����w�X�A���⪬�p
		for (int i = 0; i < MAXPLAYER; i++) { System.out.println("");
			System.out.println("Player"+(i+1)+"'s  Cards:");
			allplayer[i].printAllCard();
			System.out.print("Player"+(i+1)+" card value is: " + allplayer[i].getTotalValue());
			if (allplayer[i].getTotalValue() > 21 && dealertotal > 21) {
				System.out.println(",chips have no change! The Chips now is: "
						+ allplayer[i].getCurrentChips());
			} else if (allplayer[i].getTotalValue() <= 21 && dealertotal > 21) {
				allplayer[i].increaseChips(+pos_betArray[i]);
				System.out.println(",Get:" + pos_betArray[i]
						+ " Chips, the Chips now is: "
						+ allplayer[i].getCurrentChips());

			} else if (allplayer[i].getTotalValue() > 21 && dealertotal <= 21) {
				allplayer[i].increaseChips(-pos_betArray[i]);
				System.out.println(",Lose:" + pos_betArray[i]
						+ " Chips, the Chips now is: "
						+ allplayer[i].getCurrentChips());

			} else if (allplayer[i].getTotalValue() > dealertotal
					&& allplayer[i].getTotalValue() <= 21) {
				allplayer[i].increaseChips(+pos_betArray[i]);
				System.out.println(",Get:" + pos_betArray[i]
						+ " Chips, the Chips now is: "
						+ allplayer[i].getCurrentChips());

			} else if (allplayer[i].getTotalValue() < dealertotal
					&& dealertotal <= 21) {
				allplayer[i].increaseChips(-pos_betArray[i]);
				System.out.println(",Lose:" + pos_betArray[i]
						+ " Chips, the Chips now is: "
						+ allplayer[i].getCurrentChips());

			} else {
				System.out.println(",chips have no change! The Chips now is: "
						+ allplayer[i].getCurrentChips());
			}
		
		}
	}
//�^��pos_betArray�}�C(�^�Ǫ��a�U���w�X)
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
//����Ҧ���k�i��C��
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
