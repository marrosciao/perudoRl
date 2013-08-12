/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class LearningPlayer extends Player{

    double pow;
    Map<Integer,RlState> states;
    List<RlState> turnStates;
    List<RlState> gameStates;
    
    public LearningPlayer(String name,GameState g,double p){
        super(name,g);
        states = new HashMap<Integer,RlState>();
        turnStates = new ArrayList<RlState>();
        gameStates = new ArrayList<RlState>();
        pow = p;
    }
    
    
    
    @Override
    public Bet decide() {
        RlState rl;
        if(states.containsKey(state.hashCode())){
            rl = states.get(state.hashCode());
       
        }else{
            states.put(state.hashCode(), (rl=new RlState(this.state)));
        }
        
        int indexNext = rl.chooseNextBet(pow);
        Bet bet =null;
        try{
             bet = Action.getPossibleAction(state.getCurrentBet()).get(indexNext);
        }catch(Exception e){
             bet = Action.getPossibleAction(state.getCurrentBet()).get(0);
        }
        turnStates.add(rl);
        gameStates.add(rl);
        return bet;
    }

    public int stateSetSize(){
        return states.size();
    }
    
    @Override
    public void updateTurn(int win) {
        for(RlState r : turnStates){
                r.update(win);
        }
        turnStates.clear();
    }
    
        @Override
    public void updateGame(int win) {
        for(RlState r : gameStates){
                r.update(win);
        }
        
    }

    @Override
    public void endOfGame() {
        gameStates.clear();
    }
    
}
