import java.util.ArrayList;
import java.util.Random;
//cards(ArrayList)為做製作排所要塞入的arrayList
//usedCard(ArrayList) 放getoneCard後的牌
//nUsed作為計算抽掉了幾張牌
public class Deck {
	private ArrayList<Card> cards;
	public ArrayList<Card> usedCard ;
	private ArrayList<Card> openCard = new ArrayList<Card>();
	public int nUsed;

	//Deck方法中 實體化 cards和usedCard，之後進行三個for迴圈，
	//第一層為有幾副牌執行幾次(nDeck)
	//第二層為enhanced for loop將Card 裡面的suit的值印出來(Club, Diamand, Heart, Spade)
	//第三層為1-13(rank)
	//  迴圈功能為 利用Class Card 實體化card，進入Class Card 裡的Card 方之後將實體化後的card放入Arraylist-cards
	// 最後再呼叫shuffle方法
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		
		for (int deck = 1; deck <= nDeck; deck++) {
		for (Card.Suit s : Card.Suit.values()) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(s, rank);
				cards.add(card);
			}
		}
		shuffle();
	}}

	
	//此方法尚未在此次作業用到，不過printDeck方法是將 cards(Arraylist) 利用 Enhanced for Loop 印出值來 (Class Card裡面的printCard)
	public void printDeck() {

		for (Card allcards : cards) {
			allcards.printCard();
		}

	}
		
     //此方法尚未在此次作業用到，但getAllCards方法為回傳cards(Arraylist)
	public ArrayList<Card> getAllCards() {
		return cards;
	}

	//1.getOneCard方法是 先設一個 a 到時候 要放入 從cards(Arraylist)裡放值
	//2. 如果整個cards(ArrayList)是空的，就會先執行shuffle的方法，再執行 抽取cards的第一張牌，放入一個變數a，並將此排丟入usedCard裡，並移除cards的第一張牌，useCard++表示執行過一次
	//3. 若不是空的就會執行:抽取cards的第一張牌，放入一個變數a，並將此排丟入usedCard裡，並移除cards的第一張牌，useCard++表示執行過一次
	//最後 回傳a 值，回傳資料型態為Card
	//4. 判斷如果是isOpened=true,則把牌加入Arraylist openCard裡
	public Card getOneCard(boolean isOpened) {
		Card a = null;
		if (cards.isEmpty()) {
			shuffle();
			a = cards.get(0);
			usedCard.add(a);
			cards.remove(0);
			nUsed++;
			if(isOpened=true){openCard.add(a);}

		} else {
			a = cards.get(0);
			usedCard.add(a);
			cards.remove(0);
			nUsed++;
			if(isOpened=true){openCard.add(a);}
		}
		return a;

	}

	//先實體化亂數產生方法，並將usedCard-ArrayList裡的卡全部加入cards-ArrayList裡
	//做一個for迴圈 從0開始到整個cards-ArrayList的範圍前(0<=i<cards.size())
	//迴圈內容:  設一個變數a為照順序的第i個，再設一個暫存變數temp為a; 在利用 rnd.nextInt(cards.size())把j設為介於(0<=j<cards.size())的1個數，
	//			設一個變數b 取在cards 裡第j的位置，  最後將第i個位置取到為b；第j位置取代為暫存函數(原先第i個位置的卡)
	//  最後清掉所有在usedCard-ArrayList裡的卡,將nUsed重置為0
	public void shuffle() {

		Random rnd = new Random();
		cards.addAll(usedCard);
		for (int i = 0; i < cards.size(); i++) {
			Card a = cards.get(i);
			Card temp = a;

			int j = rnd.nextInt(cards.size());
			Card b = cards.get(j);

			cards.set(i, b);
			cards.set(j, temp);

		}
		usedCard.clear();
		nUsed = 0;
		openCard.clear();//把Arraylist openCard清空
	}
	public ArrayList<Card>getOpenedCard(){return openCard;}//回傳ArrayList openCard
}


	