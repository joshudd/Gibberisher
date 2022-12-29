/**
 * @Author: JOSH DICKINSON
 * @Date: 12-17-2022
 * 
 *        A trie node generic class.
 *        For Gibberisher, holds data and child nodes for each of the 26 letters
 *        in alphabet.
 */
public class TrieNode<T> {
    // the data of the node, generic but charbag for gibberisher
    private T data;
    // the child nodes
    private TrieNode<T>[] children;

    public TrieNode() {
        this.data = null;
        this.children = new TrieNode[26];

        // initialize array data to null
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * get child trie node for specified char
     * if the child at that char is null, create a new trie node
     * 
     * @param c, char for desired child trie node
     * @return the trie node for the passed char
     */
    public TrieNode<T> getChild(char c) {
        if (Character.isAlphabetic(c) && Character.isLowerCase(c)) {
            int index = (int) (c - 'a');

            if (children[index] == null) {
                children[index] = new TrieNode<>();
            }
            return children[index];
        } else {
            return null;
        }
    }
}
