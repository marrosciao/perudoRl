/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Perudo {
    
    public List<Player> players;
    public Bet currentbet;
    public Bet previousbet;
    private int currentplayer;
    public GameState g;
    
    
    public Perudo(){
        players = new ArrayList<Player>();
        currentbet= new Bet(5,0);
        currentplayer = 0;
    }
    
    public void newGame(){
        Random ran = new Random();
        for(Player p:players){
            p.getFiveDices();
            //p.reInitStats();
        }
        currentbet= new Bet(5,0);
        currentplayer = ran.nextInt(players.size());
        Collections.shuffle(players);
    }
    
    public void addPlayer(Player p){
        players.add(p);
    }
    
    public boolean currentPlayerWin(){
        return countDice(previousbet.getDice()) <= previousbet.getNbDice();
    }
    
    public int countDice(int d){
        int count = 0;
        for(Player p:players){
        
            count += p.countDice(d);
        }
        return count;
    }
    
    public void goNextPlayer(){
             
        do{
        currentplayer++;
        currentplayer%=players.size();
        }while(players.get(currentplayer).dicesLeft()==0);
        
    }
    
    public Player currentPlayer(){
        return players.get(currentplayer);
    }
    
    public Player previousPlayer(){
        int c= currentplayer;
        do{
        c--;
        if(c < 0)c = players.size()-1;
        }while(players.get(c).dicesLeft()==0);
        return players.get(c);
    }
            
    public void shuffleAll(){
        for(Player p:players){
            p.shuffleDices();
        }
        
    }
    
    public boolean isGameFinished(){
        int playerStillAlive=0;
        for(Player p:this.players){
            if(p.dicesLeft()>0){
                playerStillAlive++;
            }
        }
        return playerStillAlive<2;
    }
    
    public int playerLeft(){
                int playerStillAlive=0;
        for(Player p:this.players){
            if(p.dicesLeft()>0){
                playerStillAlive++;
            }
        }
        return playerStillAlive;
    }
    
    

    
    public void runOnce(){
     
       
        previousbet= currentbet;
        currentbet=players.get(currentplayer).decide();
        
       if(currentbet.isDudo()){
            if(currentPlayerWin()){
                //System.out.println("Player "+currentplayer+" win");
                previousPlayer().removeDice();
                previousPlayer().updateTurn(0);
                for(Player p : players){
                    if(p!=previousPlayer()&&p!=currentPlayer())p.updateTurn(1);
                    if(p==currentPlayer()){
                        p.updateTurn(1);
                    }
                }
                
                currentplayer = currentplayer-1;
                if(currentplayer<0)currentplayer = players.size()-1;
                currentbet = new Bet(5,0);
            }else{
                currentPlayer().removeDice();
                currentPlayer().updateTurn(0);
                for(Player p : players){
                    if(p!=currentPlayer())p.updateTurn(1);
                }
                //System.out.println("Player "+currentplayer+" lose");
                currentbet = new Bet(5,0);
            }
            shuffleAll();
           
            //System.out.println("--------");
            //System.out.println(players.get(0).state.totalDices());
        }else if(currentbet.isPerudo()){
            if(this.countDice(previousbet.getDice())==previousbet.getNbDice()){
                currentPlayer().addDice();
                currentbet = new Bet(5,0);
            }else{
                currentPlayer().removeDice();
                currentbet = new Bet(5,0);
            }
        }else{
           goNextPlayer();
        }
       

    }
    
    public void run(){
        for(int i=0;i<7500000;i++){
        while(!isGameFinished())runOnce();
                    for(Player p : players){
                     
                if(p.dicesLeft()>0){
                    //if(p.name=="Learning")System.out.println("win");
                   p.updateGame(2);
                    p.addGameResult(true);
                }else{
                    p.updateGame(0);
                    p.addGameResult(false);
                }
                p.endOfGame();   
                    }
        if(i%10000==0){
            //System.out.println("---------"+i+"----------");
            LearningPlayer l=null;
            SmartPlayer s = null;
            for(Player p : players){
                if(p.name=="Learning"){
                    l = (LearningPlayer)p;
                    //System.out.print(i/10000+" "+(int)(l.wins)+" "+l.states.size());
                }
                else if(p.name=="Smart"){
                    s = (SmartPlayer)p;
                    //System.out.print(" "+(int)(p.wins));
                }else{
                    p.reInitStats();
                }
                
            }
            System.out.println(i/10000+" "+(int)(l.wins)+" "+(int)(s.wins)+" "+l.states.size());
            s.reInitStats();
            l.reInitStats();
        }
        if(i==1000000){
            Player remove=null;
            for(Player p:players){
                if(p.name=="Random"){
                    remove=p;
                }
            }
            //players.remove(remove);
            players.add(new SmartPlayer("Random2", g));
        }
        newGame();
        }
       
    }

    
    
    public static void main(String[]argv){
        
        Perudo p = new Perudo();
        GameState g = new GameState(p);
        //p.addPlayer(new RandomPlayer("Random1", g));
        p.addPlayer(new LearningPlayer("Learning", g,10));
        //p.addPlayer(new LearningPlayer("RL1", g,2));
        p.addPlayer(new SmartPlayer("Smart", g));
        p.addPlayer(new RandomPlayer("Random", g));
        /*p.addPlayer(new SmartPlayer("Smart3", g));
        p.addPlayer(new SmartPlayer("Smart4", g));
        p.addPlayer(new SmartPlayer("Smart5", g));*/
       /* p.addPlayer(new RandomPlayer("R1", g));
        p.addPlayer(new RandomPlayer("R2", g));
        p.addPlayer(new RandomPlayer("R3", g));
        p.addPlayer(new RandomPlayer("R4", g));
        p.addPlayer(new RandomPlayer("R5", g));*/
        
        p.run();
        
        

         
        
                
    }
    
}
