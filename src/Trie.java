/**
 * @Author: JOSH DICKINSON
 * @Date: 12-17-2022
 * 
 *        A trie that can locate and update nodes based on a string.
 */
public class Trie<T> {
    private TrieNode<T> root;

    public Trie() {
        this.root = new TrieNode<>();
    }

    /**
     * get the trie node for a given string
     * 
     * @param str; the string to find the node for
     * @return the node for the string
     */
    private TrieNode<T> getNode(String str) {
        TrieNode<T> currChild = root;
        for (int i = 0; i < str.length(); i++) {
            currChild = currChild.getChild(str.charAt(i));
        }
        return currChild;
    }

    public T get(String str) {
        return (T) getNode(str).getData();
    }

    public T put(String str, T dat) {
        getNode(str).setData(dat);
        return dat;
    }
}
