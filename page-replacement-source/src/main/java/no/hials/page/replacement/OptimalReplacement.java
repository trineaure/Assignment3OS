package no.hials.page.replacement;

import java.util.List;

/**
 * Optimal Replacement algorithm Fill in your code in this class!
 */
public class OptimalReplacement extends ReplacementAlgorithm {

    // TODO - add some state variables here, if you need any
    private int currentFrame;

    @Override
    protected void reset() {
        // TODO - do preparation/initilization here, if needed
    }

    @Override
    public int process(String referenceString) {
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) {
            return 0;
        }

       // List<Integer> notLoadedPages;
        int replacements = 0; // How many page replacements made
        currentFrame = 0;
        //Goes through all the pages
        for (int i = 0; i < pageReferences.size(); i++) {
            int page = pageReferences.get(i);
            //Checks if the page is allready loaded in one of the frames
            if (!isLoaded(page)) {
                test(pageReferences, i);
                //Replaces one of the pages with the new page
                if (pageIn(currentFrame, page)) {
                    replacements++;
                }
                System.out.println(getFrameStatus());
            }
        }
        return replacements;
    }

    
    /**
    * Tests which one of the frames who has a page which is not used 
    * for the longest time
    * @param pageReferences The list of incoming pages
    * @param current The current position in the list
    */
    public void test(List<Integer> pageReferences, int current) {
        int longestAway = 0;
        //Checks if some of the frames are empty
        if(emptyFrameCheck()) {
            return;
        }
        //If all frames are used
        for (int i = 0; i < frames.length; i++) {
            for (int x = current; x < pageReferences.size(); x++) {

                int tempPage = pageReferences.get(x);
                
                if (frames[i] == tempPage) { 
                    if (x > longestAway) {
                        longestAway = x;
                        currentFrame = i; 
                    }
                    break;
                }  
                //If the page in the current fram is not used later, then
                //this is the frame which gets switched out
                else if(x == (pageReferences.size() - 1)) {
                    currentFrame = i;
                    return;
                }
            }
        }
    }
    
     /**
      * Checks if some of the frames are empty
      * @return True if frame is empty, and false if not
      */
    public boolean emptyFrameCheck() {
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == -1) {
                currentFrame = i;
                return true;
            }
        }
        return false;
    }
}


