/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.Random;


public class RandomPlayer extends Player{
    
    public RandomPlayer(String name,GameState g){
        super(name,g);
    }
    
    @Override
    public Bet decide() {
        Random ran= new Random();
        if(state.getCurrentBet().getNbDice()>state.totalDices()/3)
            return new Bet(6,6);
        int it = ran.nextInt(Action.getPossibleAction(state.getCurrentBet()).size());
        return Action.getPossibleAction(state.getCurrentBet()).get(it);
    }
    
 

    @Override
    public void updateTurn(int win) {
            
    }
    
        @Override
    public void updateGame(int win) {
            
    }

    @Override
    public void endOfGame() {
       
    }
}
