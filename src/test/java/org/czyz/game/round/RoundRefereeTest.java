package org.czyz.game.round;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test
public class RoundRefereeTest {

   @Test(dataProvider = "randomBoards", dataProviderClass = RandomBoardsProvider.class)
    public void shouldWinOnRow(Triplet triplet) {
        //given
        RoundReferee roundReferee = new RoundRefereeBuilder(triplet).build();
        MovesHistory movesHistory = new MovesHistory();
        movesHistory.addObserver(roundReferee);

        //when
        new MoveFeeder(movesHistory)
                .fillRow(triplet);

        //then
        assertWin(roundReferee);
    }

    @Test(dataProvider = "randomBoards", dataProviderClass = RandomBoardsProvider.class)
    public void shouldWinOnColumn(Triplet triplet) {
        //given
        RoundReferee roundReferee = new RoundRefereeBuilder(triplet).build();
        MovesHistory movesHistory = new MovesHistory();
        movesHistory.addObserver(roundReferee);

        //when
        new MoveFeeder(movesHistory)
                .fillColumn(triplet);

        //then
        assertWin(roundReferee);
    }



    @Test(dataProvider = "randomBoards", dataProviderClass = RandomBoardsProvider.class)
    public void shouldWinOnAscendingDiagonal(Triplet triplet) {
        //given
        RoundReferee roundReferee = new RoundRefereeBuilder(triplet).build();
        MovesHistory movesHistory = new MovesHistory();
        movesHistory.addObserver(roundReferee);

        //when
        new MoveFeeder(movesHistory)
                .fillAscending(triplet);

        //then
        assertWin(roundReferee);
    }

    @Test(dataProvider = "randomBoards", dataProviderClass = RandomBoardsProvider.class)
    public void shouldWinOnDescendingDiagonal(Triplet triplet) {
        //given
        RoundReferee roundReferee = new RoundRefereeBuilder(triplet).build();
        MovesHistory movesHistory = new MovesHistory();
        movesHistory.addObserver(roundReferee);

        //when
        new MoveFeeder(movesHistory)
                .fillDescending(triplet);

        //then
        assertWin(roundReferee);
    }


    private void assertWin(RoundReferee roundReferee) {
        assertTrue(!roundReferee.canBeContinued(), "Game cannot be continued - sb won or board is full");
        assertTrue(!roundReferee.score().isDraw(), "sb won");
    }


}