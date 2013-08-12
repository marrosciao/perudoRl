/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;

import java.util.List;
import java.util.Random;


public class RlState implements Comparable<RlState> {
    
    public int cpt[];
    public double fq[];
    public int totalCpt;
    public int hashCode;
    public int lastActionIndex;
    
    public RlState(GameState g){
        List<Bet> actions = Action.getPossibleAction(g.getCurrentBet());
        cpt = new int[actions.size()];
        fq = new double[actions.size()];
        hashCode = g.hashCode();
        for(int i = 0;i<cpt.length;i++){
            cpt[i]=1;
            fq[i]=1;
        }
        totalCpt=0;
    }
    
    public void update(int score){
        fq[lastActionIndex]+=score;
    }
    
    public int chooseNextBet(double p){
        Random ran=new Random();
        int index = 0;
        double max = 0;
        double total=0;
        
        for(int i = 0;i<cpt.length;i++){
            total=Math.pow(1.0*fq[i]/cpt[i],1)*1.5+Math.sqrt(Math.log(totalCpt)/cpt[i]);
            //total+=Math.pow((1.0*fq[i]/cpt[i]),p);
            if(total>max){
                max = total;
                index = i;
            }
        }
        /*double choose = ran.nextDouble()*total;
        total=0;
        for(int i = 0;i<cpt.length;i++){
            total+=(1.0*fq[i]/cpt[i])+Math.sqrt(Math.log(2*Math.log(totalCpt)/Math.log(cpt[i])));
            //total+=Math.pow((1.0*fq[i]/cpt[i]),p);
            if(choose<total){
                index=i;
                break;
            }
        }*/
        int next = index;
  
        cpt[next]++;
        totalCpt++;
   
        lastActionIndex=next;
        return next;
    }

    @Override
    public int compareTo(RlState t) {
        return this.hashCode-t.hashCode;
    }
    
}
