/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.ArrayList;

import java.util.List;


public class Action {
    
    
    
    public static List<Bet> getPossibleAction(Bet b){
        ArrayList<Bet> hsb=new ArrayList<Bet>();
        if(b.getNbDice()>0)hsb.add(new Bet(6,6));
        //if(b.getNbDice()>0)hsb.add(new Bet(6,5));
        
        for(int i=b.getDice()+1;i<6&&b.getNbDice()>0;i++){
            hsb.add(new Bet(i, b.getNbDice()));
        }
        
        for(int i=0;i<=b.getDice()&&b.getNbDice()>0;i++){
            hsb.add(new Bet(i, b.getNbDice()+1));
   
        }
        
        for(int i=0;i<6;i++){
            hsb.add(new Bet(i, b.getNbDice()+2));
            hsb.add(new Bet(i, b.getNbDice()+3));

        }
        
        for(int i=0;i<5&&b.getNbDice()<1;i++){
            hsb.add(new Bet(i,1));
        }
        
        return hsb;
    }
    
}
