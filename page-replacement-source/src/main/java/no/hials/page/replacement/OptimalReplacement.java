package no.hials.page.replacement;

import java.util.List;

/**
 * Optimal Replacement algorithm
 * Fill in your code in this class!
 */
public class OptimalReplacement extends ReplacementAlgorithm {
    
    // TODO - add some state variables here, if you need any
   // private int currentFrame;
    
    
    
    @Override
    protected void reset() {
        // TODO - do preparation/initilization here, if needed
    }
    
    @Override
    public int process(String referenceString) {
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) return 0;
        
       // List<Integer> notLoadedPages;
         
        int replacements = 0; // How many page replacements made
        int lastLoaded = 0;
        
        for(int page : pageReferences) {
            
            int currentFrame = 0;
            
            if(!isLoaded(page)) {
                int longestAway = 0; //the frame that has a page longest away
                
                for(int frame : frames) {
                    
                    for(int i = lastLoaded; i < pageReferences.size(); i++) {
                        
                        int tempPage = pageReferences.get(i);
                        
                       if((frame == tempPage) && (i > longestAway)) {
                           longestAway = i;
                           
                           currentFrame = frame;
                       }
                    }
                    
                }
                pageIn(currentFrame, page);
                lastLoaded = page;
                replacements++;
            } //pageReferences.get(longestAway)555555
            
        }
        
        
        // TODO - process the reference string here. You can see FIFOReplacement
        // as an example. But remember, that FIFO uses a different algorithm.
        // This class should use Optimal Replacement algorithm, described
        // in Section 9.4.
        // Get the reference list as an array
       
        return replacements;
    }

    // TODO - create any helper methods here if you need any
}
