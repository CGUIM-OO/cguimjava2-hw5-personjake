
public   class Player extends Person {
	private String name;

	private int chips;
	private int bet;

	//this.name���W�����ܼ� �אּ�[�J���Ѽ�name (chips��p��)
	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}
	//�^��name
	public String getName() {
		return name;
	}
	//���]�쥻bet��1 �p�G �{�����w�X�p���`�w�X �h���U�A�Y�w�X�S�F�A�h���|��`�A��L�h���w�X�h�֩�h��
	public int makeBet() {
		bet=1;
		if (chips < bet)
			return chips;
		else if(chips == 0)
			return 0;
		else {
			
			return bet;
		}
	}


   //�Q�Ψ�getTotalValue����k�A�p�G�P�p��16�h�^��true�~��hitMe�n�P�A��l�^��false���n�P�A����override
	public boolean hit_me(Table tbl) {
		
		if (getTotalValue() < 16) {
			return true;
		}
	
		return false;

	}
	

//�^��chips
	public int getCurrentChips() {
		return chips;
	}
//�w�X�[�WĹ�o���[�`(diff���Ƭ�Ĺ�A�t�Ƭ���)	
	public void increaseChips(int diff) {
		chips += diff;
	}
//�L�X���a�W�٩M�w�X�h�֡C
	public void sayHello() {
		getName();
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	
}
