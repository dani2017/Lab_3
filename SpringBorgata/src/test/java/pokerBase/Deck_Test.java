package pokerBase;

import static org.junit.Assert.*;
import org.junit.Test;

public class Deck_Test extends Deck {

	@Test
	public void TestFullDeck() {
		
		//Tests that Deck has 52 cards
		assertTrue(getTotalCards() == 52);
		
		assertFalse(getTotalCards()==51);
		
	}

}
