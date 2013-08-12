/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.List;
import java.util.Random;


public class SmartPlayer extends Player {

    public SmartPlayer(String name,GameState g){
        super(name,g);
    }
    
    @Override
    public Bet decide() {
        Random ran= new Random();
        double count[] = new double[6];
        double max =0;
        int indexBest=0;
        for(int i =0;i<6;i++){
            count[i] = 1.0*(this.state.totalDices()-this.dicesLeft())/6.0;
            count[i] += this.countDice(i);
            if(count[i]>max){
                max=count[i];
                indexBest = i;
            }
            
        }
        
        if(count[state.getCurrentBet().getDice()]<state.getCurrentBet().getNbDice()){
            return new Bet(6, 6);
        }else{
            //return new Bet(indexBest,(int)count[indexBest]);
            /*if((int)count[indexBest]>state.getCurrentBet().getNbDice()){
                return new Bet(indexBest,(int)count[indexBest]);
            }else{
                return new Bet(6, 6);
            }*/
            if(indexBest>state.getCurrentBet().getDice()){
                return new Bet(indexBest,state.getCurrentBet().getNbDice());
            }else{
                return new Bet(indexBest,state.getCurrentBet().getNbDice()+1);
            }
        }
        

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
