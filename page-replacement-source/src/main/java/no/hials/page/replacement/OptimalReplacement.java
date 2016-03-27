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
        int lastLoaded = 0;
        currentFrame = 0;

        for (int i = 0; i < pageReferences.size(); i++) {
            int page = pageReferences.get(i);

            if (!isLoaded(page)) {

                test(pageReferences, page, i);

                if (pageIn(currentFrame, page)) {
                    replacements++;
                }
                System.out.println(getFrameStatus());
            }
        }
        return replacements;
    }

    public void test(List<Integer> pageReferences, int page, int current) {

        int longestAway = 0;
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == -1) {
                currentFrame = i;
                return;
            }
        }

        for (int i = 0; i < frames.length; i++) {
            boolean nextFound = false;
            for (int x = current; x < pageReferences.size(); x++) {

                int tempPage = pageReferences.get(x);

                if (frames[i] == tempPage) {
                    nextFound = true;
                    if (x >= longestAway) {
                        longestAway = x;
                        currentFrame = i;
                    }
                } else {
                    if(x == (pageReferences.size() - 1)) {
                        currentFrame = i;
                        return;
                    } 
                }
            }
            if (nextFound) {
                break;
            }
        }

    }
}
    // TODO - create any helper methods here if you need any

