/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public abstract class Player {
    
    public String name;
    private Map<Integer,Integer>dices;
    private int dicesleft;
    protected GameState state;
    protected int wins;
    protected int lost;
    
    public Player(String name,GameState st){
        this.name = name;
        state = st;
        dicesleft=5;
        dices = new HashMap<Integer,Integer>(dicesleft);
        Random ran = new Random();
        wins = 0;
        lost = 1;
        for(int i=0;i<6;i++){
            dices.put(new Integer(i),0);
        }
        
        for(int i=0;i<dicesleft;i++){
            dices.put(new Integer(ran.nextInt(6)),new Integer(dices.get(i)+1));
            
        }
     
    }
    
    public void addDice(){
        if(dicesleft<5)dicesleft++;
    }
    
    public void getFiveDices(){
        dicesleft=5;
        shuffleDices();
    }
    
    public int countDice(int d){
        return dices.get(d);
    }
    
    public void removeDice(){
        if(dicesleft > 0){
            dicesleft--;
        }
    }
    
    public double stats(){
        return 1.0*wins/(wins+lost);
    }
    
    public void reInitStats(){
        wins=1;
        lost=1;
    }
    
    public void shuffleDices(){
        Random ran = new Random();
        dices = new HashMap<Integer,Integer>(dicesleft);
        for(int i=0;i<6;i++){
            dices.put(new Integer(i),0);
        }
        
        for(int i=0;i<dicesleft;i++){
            dices.put(new Integer(ran.nextInt(6)),new Integer(dices.get(i)+1));
        }
    }
    
    public int dicesLeft(){
        return dicesleft;
    }
    
    public void addGameResult(boolean win){
        if(win){
            wins++;
        }else{
            lost++;
        }
    }
    
    public String toString(){
return name+" "+dices+" "+hashCode();
    }
    
    @Override
    public int hashCode(){
        int mult=1;
        int res=0;
        for(Integer i:this.dices.values()){
            res+=i*mult;
            mult*=5;
        }
        return res;
    }
    
    public abstract Bet decide();
    
    public abstract void updateTurn(int win);
    
    public abstract void updateGame(int win);
    
    public abstract void endOfGame();
    
}
