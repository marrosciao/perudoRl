/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perudo;


public class Bet implements Comparable<Bet> {
    
    private final int dice;
    private final int nbdice;
    
    public Bet(int d,int n){
        dice = d;
        nbdice = n;
    }
    
    public int getDice(){
        return dice;
    }
    
    public int getNbDice(){
        return nbdice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bet other = (Bet) obj;
        if (this.dice != other.dice) {
            return false;
        }
        if (this.nbdice != other.nbdice) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return (nbdice)*7 + dice;
    }

    @Override
    public int compareTo(Bet t) {
        return this.hashCode() - t.hashCode();
                
    }
    
    public boolean isDudo(){
        return dice==6 && nbdice == 6;
    }
    
    public boolean isPerudo(){
        return dice==6 && nbdice == 5;
    }
    
    public String toString(){
        if(isDudo())return "DUDO";
        if(isPerudo())return "PERUDO";
        return "bet "+nbdice + " on "+dice;
    }
    
}
