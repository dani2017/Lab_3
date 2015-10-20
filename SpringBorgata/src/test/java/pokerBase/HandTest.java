package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class HandTest {

	@Test
	public void testNatFlush(){
		ArrayList<Card> NatFlush = new ArrayList<Card>();
		
		NatFlush.add(new Card(eSuit.HEARTS, eRank.TWO));
		NatFlush.add(new Card(eSuit.HEARTS, eRank.FOUR));
		NatFlush.add(new Card(eSuit.HEARTS, eRank.NINE));
		NatFlush.add(new Card(eSuit.HEARTS, eRank.SEVEN));
		NatFlush.add(new Card(eSuit.HEARTS, eRank.THREE));
		
		Hand hand = Hand.EvalHand(NatFlush);
		
		assertEquals(eHandStrength.Flush.getHandStrength(), hand.getHandStrength());
		
	}
	
	@Test
	public void testJokNatFlush(){
		ArrayList<Card> JokNatFlush = new ArrayList<Card>();
		JokNatFlush.add(new Card(eSuit.JOKER, eRank.JOKER));
		JokNatFlush.add(new Card(eSuit.HEARTS, eRank.FOUR));
		JokNatFlush.add(new Card(eSuit.HEARTS, eRank.NINE));
		JokNatFlush.add(new Card(eSuit.HEARTS, eRank.SEVEN));
		JokNatFlush.add(new Card(eSuit.HEARTS, eRank.TWO));
		Hand hand = Hand.EvalHand(JokNatFlush);
		assertEquals(eHandStrength.Flush.getHandStrength(), hand.getHandStrength());
		
	}
	
	@Test
	public void testStraightAceLow(){
		ArrayList<Card> StraightAceLow = new ArrayList<Card>();
		StraightAceLow.add(new Card(eSuit.HEARTS, eRank.ACE));
		StraightAceLow.add(new Card(eSuit.SPADES, eRank.FOUR));
		StraightAceLow.add(new Card(eSuit.SPADES, eRank.THREE));
		StraightAceLow.add(new Card(eSuit.CLUBS, eRank.FIVE));
		StraightAceLow.add(new Card(eSuit.SPADES, eRank.TWO));
		Hand hand = Hand.EvalHand(StraightAceLow);
		assertEquals(eHandStrength.Straight.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testStraightAceHigh(){
		ArrayList<Card> StraightAceHigh = new ArrayList<Card>();
		StraightAceHigh.add(new Card(eSuit.HEARTS, eRank.ACE));
		StraightAceHigh.add(new Card(eSuit.SPADES, eRank.KING));
		StraightAceHigh.add(new Card(eSuit.SPADES, eRank.QUEEN));
		StraightAceHigh.add(new Card(eSuit.CLUBS, eRank.JACK));
		StraightAceHigh.add(new Card(eSuit.SPADES, eRank.TEN));
		Hand hand = Hand.EvalHand(StraightAceHigh);
		assertEquals(eHandStrength.Straight.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testStraightNoAce(){
		ArrayList<Card> StraightNoAce = new ArrayList<Card>();
		StraightNoAce.add(new Card(eSuit.HEARTS, eRank.NINE));
		StraightNoAce.add(new Card(eSuit.SPADES, eRank.KING));
		StraightNoAce.add(new Card(eSuit.SPADES, eRank.QUEEN));
		StraightNoAce.add(new Card(eSuit.CLUBS, eRank.JACK));
		StraightNoAce.add(new Card(eSuit.SPADES, eRank.TEN));
		Hand hand = Hand.EvalHand(StraightNoAce);
		assertEquals(eHandStrength.Straight.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testRoyalFlush(){
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.KING));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.QUEEN));
		RoyalFlush.add(new Card(eSuit.JOKER, eRank.JOKER));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.TEN));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.ACE));
		Hand hand = Hand.EvalHand(RoyalFlush);
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testNatRoyalFlush(){
		ArrayList<Card> NatRoyalFlush = new ArrayList<Card>();
		NatRoyalFlush.add(new Card(eSuit.HEARTS, eRank.KING));
		NatRoyalFlush.add(new Card(eSuit.HEARTS, eRank.QUEEN));
		NatRoyalFlush.add(new Card(eSuit.HEARTS, eRank.JACK));
		NatRoyalFlush.add(new Card(eSuit.HEARTS, eRank.TEN));
		NatRoyalFlush.add(new Card(eSuit.HEARTS, eRank.ACE));
		Hand hand = Hand.EvalHand(NatRoyalFlush);
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testNatStraightFlush(){
		ArrayList<Card> NatStraightFlush = new ArrayList<Card>();
		NatStraightFlush.add(new Card(eSuit.HEARTS, eRank.KING));
		NatStraightFlush.add(new Card(eSuit.HEARTS, eRank.QUEEN));
		NatStraightFlush.add(new Card(eSuit.HEARTS, eRank.JACK));
		NatStraightFlush.add(new Card(eSuit.HEARTS, eRank.TEN));
		NatStraightFlush.add(new Card(eSuit.HEARTS, eRank.NINE));
		Hand hand = Hand.EvalHand(NatStraightFlush);
		assertEquals(eHandStrength.StraightFlush.getHandStrength(), hand.getHandStrength());
	}

	@Test
	public void testNatStraightFlushLow(){
		ArrayList<Card> NatStraightFlushLow = new ArrayList<Card>();
		NatStraightFlushLow.add(new Card(eSuit.HEARTS, eRank.TWO));
		NatStraightFlushLow.add(new Card(eSuit.HEARTS, eRank.THREE));
		NatStraightFlushLow.add(new Card(eSuit.HEARTS, eRank.FOUR));
		NatStraightFlushLow.add(new Card(eSuit.HEARTS, eRank.FIVE));
		NatStraightFlushLow.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(NatStraightFlushLow);
		assertEquals(eHandStrength.StraightFlush.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testFourOfAKind(){
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.HEARTS, eRank.THREE));
		FourOfAKind.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		FourOfAKind.add(new Card(eSuit.SPADES, eRank.THREE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.THREE));
		FourOfAKind.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(FourOfAKind);
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testFullHouse(){
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.HEARTS, eRank.THREE));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		FullHouse.add(new Card(eSuit.SPADES, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.SIX));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(FullHouse);
		assertEquals(eHandStrength.FullHouse.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testFullHouse2(){
		ArrayList<Card> FullHouse2 = new ArrayList<Card>();
		FullHouse2.add(new Card(eSuit.HEARTS, eRank.THREE));
		FullHouse2.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		FullHouse2.add(new Card(eSuit.SPADES, eRank.SIX));
		FullHouse2.add(new Card(eSuit.CLUBS, eRank.SIX));
		FullHouse2.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(FullHouse2);
		assertEquals(eHandStrength.FullHouse.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testFiveOfAKind(){
		ArrayList<Card> FiveOfAKind = new ArrayList<Card>();
		FiveOfAKind.add(new Card(eSuit.HEARTS, eRank.THREE));
		FiveOfAKind.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		FiveOfAKind.add(new Card(eSuit.SPADES, eRank.THREE));
		FiveOfAKind.add(new Card(eSuit.CLUBS, eRank.THREE));
		FiveOfAKind.add(new Card(eSuit.JOKER, eRank.JOKER));
		Hand hand = Hand.EvalHand(FiveOfAKind);
		assertEquals(eHandStrength.FiveOfAKind.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testThreeOfAKind(){
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.TWO));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		ThreeOfAKind.add(new Card(eSuit.SPADES, eRank.SIX));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.SIX));
		ThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(ThreeOfAKind);
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testTwoPair(){
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.HEARTS, eRank.TWO));
		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		TwoPair.add(new Card(eSuit.SPADES, eRank.THREE));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.SIX));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(TwoPair);
		assertEquals(eHandStrength.TwoPair.getHandStrength(), hand.getHandStrength());
	}
	
	@Test
	public void testPair(){
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.HEARTS, eRank.TWO));
		Pair.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		Pair.add(new Card(eSuit.SPADES, eRank.THREE));
		Pair.add(new Card(eSuit.CLUBS, eRank.SIX));
		Pair.add(new Card(eSuit.HEARTS, eRank.SIX));
		Hand hand = Hand.EvalHand(Pair);
		assertEquals(eHandStrength.Pair.getHandStrength(), hand.getHandStrength());
	}
	
	
}
