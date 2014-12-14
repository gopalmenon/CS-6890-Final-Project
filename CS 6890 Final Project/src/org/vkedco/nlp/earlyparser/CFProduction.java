package org.vkedco.nlp.earlyparser;
import java.util.ArrayList;
import java.util.Iterator;

import menon.cs6890.FinalProject.Controller.Constants;

/**
 *
 * @author vladimir kulyukin
 */
public class CFProduction {
    int mID;
    CFGSymbol mLHS; // left-hand side
    ArrayList<CFGSymbol> mRHS; // right-hand side
    
    @Override
    public String toString() {
      String rslt = "CFP" + mID + ") " + mLHS.mSymbolName + " ::= ";
      Iterator<CFGSymbol> iter = mRHS.iterator();
      while ( iter.hasNext() ) {
          rslt += iter.next().mSymbolName + " ";
      }
      return rslt;
    }
    
    public String getRhs() {
    	int numberOfParts = this.mRHS.size();
    	StringBuffer returnValue = new StringBuffer();
    	for (int partCounter = 0; partCounter < numberOfParts; ++partCounter) {
    		returnValue.append(this.mRHS.get(partCounter)).append(Constants.SPACER);
    	}
    	return returnValue.toString().trim();
    }

    // rule id defaults to 0
    public CFProduction(CFGSymbol lhs, ArrayList<CFGSymbol> rhs) {
        this.mID = 0;
        this.mLHS = new CFGSymbol(lhs);
        this.mRHS = new ArrayList<CFGSymbol>();
        Iterator<CFGSymbol> iter = rhs.iterator();
        while ( iter.hasNext() ) {
            this.mRHS.add(new CFGSymbol(iter.next()));
        }
    }

    public CFProduction(int id, CFGSymbol lhs, ArrayList<CFGSymbol> rhs) {
        this(lhs, rhs);
        this.mID = id;
    }

    public CFProduction(CFProduction r) {
        this(r.mID, r.mLHS, r.mRHS);
    }

    public static void main(String[] args) {
        CFGSymbol lhs = new CFGSymbol("S");
        ArrayList<CFGSymbol> rhs = new ArrayList<CFGSymbol>();
        rhs.add(new CFGSymbol("NP"));
        rhs.add(new CFGSymbol("VP"));
        CFProduction r1 = new CFProduction(lhs, rhs);
        CFProduction r2 = new CFProduction(r1);
        System.out.println(r1.toString());
        System.out.println(r2.toString());
    }

}