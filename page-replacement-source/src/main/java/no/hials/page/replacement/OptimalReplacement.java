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
                //Tests which one of the frames who has a page which is not uesd 
                //for the longest time
                test(pageReferences, page, i);
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
    * @param pageReferences The list of incoming pages
    * @param page The current page which is not loaded
    * @param current The current position in the list
    */
    public void test(List<Integer> pageReferences, int page, int current) {

        int longestAway = 0;
        //Checks if some of the frames are empty
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == -1) {
                currentFrame = i;
                return;
            }
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
                } else {
                    //If the page in the current fram is not used later, then
                    //this is the frame which gets switched out
                    if(x == (pageReferences.size() - 1)) {
                        currentFrame = i;
                        return;
                    } 
                }
            }
  
        }

    }
}


