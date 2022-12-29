/**
 * @Author: JOSH DICKINSON
 * @Date: 12-17-2022
 * 
 *        CharBag is a bag ADT that holds character values for 'a'-'z' and '.'
 */
public class CharBag {
    // map of letters based on index, STOP being index 26
    private int[] bag;
    // tracker for how many characters are in the bag
    private int size;

    public CharBag() {
        this.bag = new int[27];
        this.size = 0;
    }

    /**
     * adds a char to the char bag
     * 
     * @param c, the char to be added
     */
    public void add(char c) {
        if (Character.isAlphabetic(c)) {
            c = Character.toLowerCase(c);

            int index = c - 'a';
            bag[index] = bag[index] + 1;
        } else {
            // 26 is stop character index
            bag[26] = bag[26] + 1;
        }

        size++;
    }

    /**
     * removes a char from the bag
     * 
     * @param c, the char to be removed
     */
    public void remove(char c) {
        if (Character.isAlphabetic(c)) {
            c = Character.toLowerCase(c);

            int index = c - 'a';
            if (bag[index] > 0) {
                bag[index] = bag[index] - 1;
                size--;
            }
        } else {
            // 26 is stop character index
            if (bag[26] > 0) {
                bag[26] = bag[26] - 1;
                size--;
            }
        }

    }

    /**
     * 
     * @param c, a char to get the count of
     * @return int, the number of char c's in the bag
     */
    public int getCount(char c) {
        if (Character.isAlphabetic(c)) {
            c = Character.toLowerCase(c);

            int index = c - 'a';
            return bag[index];
        } else {
            // 26 is stop character index
            return bag[26];
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    /**
     * returns a string describing the bag of letters and their counts
     */
    public String toString() {
        String retVal = "CharBag{";

        for (int i = 0; i < 26; i++) {
            retVal += (char) (i + 'a') + ":" + bag[i] + ", ";
        }
        retVal += ".:" + bag[26] + "}";

        return retVal;
    }

    /**
     * generates random char
     * 
     * @return a random char based on probablity of letters in bag
     */
    public char getRandomChar() {
        // return stop character if empty
        if (size == 0) {
            return LetterSample.STOP;
        }

        // get random double between 0 and < 1
        // add relative prob of each letter until they are cumalative > rnd
        // return the char that puts it over the edge
        double rnd = Math.random();
        double cmltvProb = 0.0;
        for (int i = 0; i < 26; i++) {
            cmltvProb += ((double) bag[i]) / size;
            if (rnd < cmltvProb) {
                return (char) (i + 'a');
            }
        }

        // never reaches this point
        return LetterSample.STOP;
    }
}