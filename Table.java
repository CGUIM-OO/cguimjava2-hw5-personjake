import java.util.ArrayList;

//B0544233 資管2乙   陳冠蓁
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

	// 此為Table的constructor，並把玩家放入allplayer的陣列中
	public void set_player(int pos, Player p) {

		this.pos = pos;
		this.p = p;
		allplayer[pos] = p;

	}

	// 回傳所有玩家
	public Player[] get_player() {
		return allplayer;
	}

	// dealer的setter
	public void set_dealer(Dealer d) {
		dealer = d;
	}

	// 取得dealer的第二張牌作為明牌給大家看
	public Card get_face_up_card_of_dealer() {
		return dealerCard.get(1);
	}

	// 每一個玩家要sayhello,並下注，再將所有玩家個別下的注存在pos_betArray的陣列中
	private void ask_each_player_about_bets() {
		for (int i = 0; i < MAXPLAYER; i++) {
			allplayer[i].sayHello();
			allplayer[i].makeBet();
			pos_betArray[i] = allplayer[i].makeBet();
		}
	}

	// 發牌給玩家和莊家
	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < MAXPLAYER; i++) {
			playerCard = new ArrayList<Card>();// 實體化playerCard和dealerCard
			dealerCard = new ArrayList<Card>();
			playerCard.add(allcard.getOneCard(true));// 玩家取得第一張牌
			playerCard.add(allcard.getOneCard(true));// 玩家取得第二張牌
			allplayer[i].setOneRoundCard(playerCard);// 將取得的排放入各自的OneRoundCard

		}
		dealerCard.add(allcard.getOneCard(true));// 莊家取得第一張牌
		dealerCard.add(allcard.getOneCard(true));// 莊家取得第二張牌
		dealer.setOneRoundCard(dealerCard);// 將取得的排放入莊家的OneRoundCard

		Card a = get_face_up_card_of_dealer();// 呼叫get_face_up_card_of_dealer方法取得第二張牌作為明牌並印出
		System.out.print("Dealer's face up card is: ");
		a.printCard();

	}

	private void ask_each_player_about_hits() {

		for (int i = 0; i < MAXPLAYER; i++) {
			playerCard = new ArrayList<Card>();
			playerCard = allplayer[i].getOneRoundCard();// playerCard設為玩家的getOneRoundCard
			System.out.println(allplayer[i].getName() + "'s Cards now:");
			for (Card c : playerCard) {
				c.printCard();
			}
			do {
				hit = false;// 預先假設hit 為false
				hit = allplayer[i].hit_me(this); // this為這張Table，並傳入hit_me,判斷玩家是否還要牌
				if (hit) {
					
					playerCard.add(allcard.getOneCard(true));// 玩家加入新的一張牌
					allplayer[i].setOneRoundCard(playerCard);// 再將玩家新有的牌設回OneRoundCard
					System.out.print("Hit! ");
					System.out
							.println(allplayer[i].getName() + "'s Cards now:");
					for (Card c : playerCard) {
						c.printCard();
					}
				} else {
					
					System.out
							.println(allplayer[i].getName() + "'s Cards now:");
					for (Card c : playerCard) {
						c.printCard();
					}
					System.out.println("    Pass hit!");
					System.out
							.println(allplayer[i].getName() + ", Final Card:");
					for (Card c : playerCard) {
						c.printCard();
					}
					System.out.println("  " + allplayer[i].getName()
							+ "'s hit is over!");
				}
			} while (hit);// 若hit 為false即跳出dowhile迴圈
		}

	}

	// dealer是否要牌
	private void ask_dealer_about_hits() {

		do {
			hit = false;
			hit = dealer.hit_me(this); // this 表示傳入這張桌子，並進入判斷是否要牌
			if (hit) {
				dealerCard = new ArrayList<Card>();
				dealerCard = dealer.getOneRoundCard();// dealerCard設為莊家的getOneRoundCard
				dealerCard.add(allcard.getOneCard(true));// 莊家加一張牌
				dealer.setOneRoundCard(dealerCard); // 將新的牌回設回dealer的OneRoundCard

			}

		} while (hit);

		System.out.println("Dealer's hit is over!");

	}

	// 判斷贏還輸莊家並計算遊戲後的籌碼
	private void calculate_chips() {
		int dealertotal = dealer.getTotalValue();
		System.out
				.println("Dealer's card value is: " + dealertotal + " Cards:");
		dealer.printAllCard();

		// 6種情形: 1.玩家>21莊家>21則平手 2.玩家<=21莊家>21則玩家贏3.玩家>21莊家<=21
		// 則玩家輸4.玩家點數>莊家點數兩者和小於21玩家贏5.玩家點數<莊家點數兩者和小於21玩家輸6.其餘情形沒有變籌碼，平手狀況
		for (int i = 0; i < MAXPLAYER; i++) {
			System.out.println("");
			System.out.println("Player" + (i + 1) + "'s  Cards:");
			allplayer[i].printAllCard();
			System.out.print("Player" + (i + 1) + " card value is: "
					+ allplayer[i].getTotalValue());
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

	// 回傳pos_betArray陣列(回傳玩家下的籌碼)
	public int[] get_palyers_bet() {
		return pos_betArray;
	}

	// 執行所有方法進行遊戲
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
