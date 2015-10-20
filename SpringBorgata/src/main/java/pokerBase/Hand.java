package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	
	//Checks for Joker in Hand
	public boolean Joker_In_Hand(){
		if(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()== eRank.JOKER){
			
		JokerInHand=true;
	}else{
		 JokerInHand=false;}
		
		return JokerInHand;
	}

	private static boolean JokerInHand;
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private int Kicker;

	@SuppressWarnings("unused")
	private boolean bScored = false;

	private boolean Flush;
	private boolean Natural_Flush;
	private boolean Straight;
	private boolean Ace;
	
	private boolean One;
	private boolean Two;
	private boolean Three;
	private boolean Four;
	
	private boolean Straight_Joker;
	private boolean Flush_Joker;
	private boolean Straight_Flush;
	
	
	@SuppressWarnings("unused")
	private static Deck dJoker = new Deck();

	public Hand()
	{}
	
	public void  AddCardToHand(Card c)
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}
	
	public Card  GetCardFromHand(int location)
	{
		return CardsInHand.get(location);
	}
	
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;


	}


	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}


	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}

	

	
	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}
		
		//Checks for natural Flush
		if(JokerInHand==false){
			if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {Natural_Flush = true;
			}
		
		if (Natural_Flush== true){
			
			ScoreHand(eHandStrength.Flush, 0, 0, 0);
		}

		// Straight Evaluation
		else if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo())
							.getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			
			// Looks for straight without Ace
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluate Royal Flush
		if (Straight == true
				&& Flush == true
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, 0);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
							.getRank(), 0, 0);
		}
		
	
		
		// Four a Kind
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
			
			|| CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()){
			
			Four=true;
			ScoreHand(eHandStrength.FourOfAKind, 0, 0, 0);
		}
		
		// Full House
		else if ((CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank())==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() 
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() 
				
				|| (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() )
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank())		
		{
			ScoreHand(eHandStrength.FullHouse, 0, 0, 0);
		}

		// Flush
		else if (Flush==true){
			ScoreHand(eHandStrength.Flush, 0, 0, 0);
		}
		
		// Straight
		else if(Straight==true){
			ScoreHand(eHandStrength.Straight, 0, 0, 0);
		}

		// Three of a Kind
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				
				|| CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() 
				
				|| CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() ){
			
			Three=true;
			ScoreHand(eHandStrength.ThreeOfAKind, 0, 0, 0);
		}
		
		// Two Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&&CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()
				
				||CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&&CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&&CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()){
			
			Two=true;
			ScoreHand(eHandStrength.TwoPair, 0, 0, 0);
		}
		

		// Pair
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				
				||CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()
				
				||CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() ==CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()
				
				||JokerInHand){
		
		One=true;
		ScoreHand(eHandStrength.Pair, 0, 0, 0);
		}
		
		// High Card
		//	I'll give you this one :)
		else if(JokerInHand != true){
			ScoreHand(eHandStrength.HighCard,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank());}
		

	
		
		//This section Evaluates the New possible with Joker:
	
		//Checks for one pair
		else if(JokerInHand){
			ScoreHand(eHandStrength.HighCard,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());}
		
		//Checks for Two Pair
		else if (One && JokerInHand){
			
			Two=true;
			ScoreHand(eHandStrength.TwoPair,0,0,0);
		}
		
		//Checks for 3-of-a-Kind
		else if (Two==true && JokerInHand){
			ScoreHand(eHandStrength.ThreeOfAKind,0,0,0);
		}
		
		//Four of a Kind
		else if(Three==true && JokerInHand){
			ScoreHand(eHandStrength.FourOfAKind,0,0,0);
		}
		
		//Five of a Kind
		else if(Four==true && JokerInHand){
			ScoreHand(eHandStrength.FiveOfAKind, 0, 0, 0);
		}
		
		//Straight W/ Joker
		else if (JokerInHand){
			if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()+1 
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()+1
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()+1
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()== eRank.JOKER
						
				//Checks if 4th card is Joker
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()+1 
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()+1
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()+2
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 3rd card is joker
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()+1 
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()+2
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()+1
						&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 2nd card is joker
				||CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank()+2 
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()+1
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()+1
						&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 1st card is joker
				||CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()+1 
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()+1
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()+1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()== eRank.JOKER){
				
				ScoreHand(eHandStrength.Straight, 0, 0, 0);
				Straight_Joker=true;
				}
		}
		
		//Flush W/Joker
		else if (JokerInHand){
			if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 4th card is Joker
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 3rd card is joker
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 2nd card is joker
				||CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()== eRank.JOKER
				
				//Checks if 3rd card is joker
				||CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getSuit()==CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()== eRank.JOKER){
				
				ScoreHand(eHandStrength.Flush, 0, 0, 0);
				Flush_Joker=true;
				}
			
		}
		
		else if (Straight_Joker &&Flush_Joker){
			Straight_Flush=true;
		}
		
		else if (Straight_Flush){
			if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()== eRank.TEN
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()==eRank.ACE
				
				||CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()== eRank.TEN
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()==eRank.JOKER
				
				||CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()== eRank.JACK
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()==eRank.ACE){
				
				ScoreHand(eHandStrength.RoyalFlush, 0, 0, 0);
			}
		}
		
		
		
		else{}
		}
	
	}

	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, int Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getKicker() - h1.getKicker();
			if (result != 0) {
				return result;
			}

			return 0;
		}
	};
}
