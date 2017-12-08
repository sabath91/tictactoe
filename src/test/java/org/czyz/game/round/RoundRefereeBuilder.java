package org.czyz.game.round;

import org.czyz.settings.BoardDimensions;
import org.czyz.settings.Settings;

class RoundRefereeBuilder {

    private final RoundReferee roundReferee;

    public RoundRefereeBuilder(Triplet triplet) {
       Settings settings = new Settings(
               new BoardDimensions(triplet.getWidth(), triplet.getHeight()), null, null, null, triplet.getLength());
        roundReferee = new RoundReferee(settings);


    }

    public RoundReferee build() {
        return roundReferee;
    }
}
