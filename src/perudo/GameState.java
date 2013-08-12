/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;


public class GameState {
    
    private Perudo game;
    
    public GameState(Perudo p){
        game=p;
        p.g=this;
    }
    
    public int totalDices(){
        int count=0;
        for(Player p:game.players){
            count+=p.dicesLeft();
        }
        return count;
    }
    
    public Bet getCurrentBet(){
        return game.currentbet;
    }
    
    @Override
    public int hashCode(){
        return (getCurrentBet().hashCode()+game.currentPlayer().hashCode()*100)+(totalDices()/2)*3000+game.playerLeft()*100000;
    }
    
}
