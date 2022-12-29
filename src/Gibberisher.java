/**
 * @Author: JOSH DICKINSON
 * @Date: 12-17-2022
 * 
 *        Implements random word generation algorithm and model training.
 */
public class Gibberisher {
    // model that holds info on what letters have what probabilities based on
    // segments
    private Trie<CharBag> model;
    // int of what length segments the model uses
    private int segLength;
    // int of how many samples have been created during train
    private int sampleCount;

    public Gibberisher(int segLength) {
        model = new Trie<>();
        this.segLength = segLength;
        this.sampleCount = 0;
    }

    /**
     * trains the model based on an array of strings and letter samples from those
     * strings
     * 
     * @param strArr; array of strings used to train the model
     */
    public void train(String[] strArr) {
        LetterSample[] samples;
        // for each string
        for (int i = 0; i < strArr.length; i++) {
            samples = LetterSample.toSamples(strArr[i], segLength);
            // for each letter sample
            for (int j = 0; j < samples.length; j++) {
                // get the char bag for lettersample segment, then add next letter to it
                // if there is no char bag, create one
                if (model.get(samples[j].getSegment()) == null) {
                    model.put(samples[j].getSegment(), new CharBag());
                }
                model.get(samples[j].getSegment()).add(samples[j].getNextLetter());
                sampleCount++;
            }
        }
    }

    public int getSampleCount() {
        return sampleCount;
    }

    /**
     * generates a string using model and last segLength number of letters in word
     * 
     * @return a generated string
     */
    public String generate() {
        String retVal = "";
        String sample = "";
        char nextLetter = 'A';

        // while not the end of the word
        while (nextLetter != LetterSample.STOP) {
            // get the last segLength letters of the word (or less if they don't exist yet)
            if (retVal.length() < segLength) {
                sample = retVal;
            } else {
                sample = retVal.substring(retVal.length() - segLength);
            }

            // add the letter to the word
            nextLetter = model.get(sample).getRandomChar();
            retVal += nextLetter;
        }

        // return the generated word without the stop letter
        return retVal.substring(0, retVal.length() - 1);
    }
}
