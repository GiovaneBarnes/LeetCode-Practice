class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie myTrie = buidTrie(words); 
        Set<String> unqiueWords = new HashSet<>();
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                dfs(board, myTrie, row, col, unqiueWords);
            }
        }
        return new ArrayList<>(unqiueWords);
    }

    private void dfs(char[][] board, Trie myTrie, int row, int col, Set<String> unqiueWords){
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length){ 
            return;
        }

        char letter = board[row][col];
        int trieIDX =  letter - 'a';
        
        if (board[row][col] == '#'){ 
            return; 
        }

        Trie nextNode = myTrie.next[trieIDX];

        if (nextNode == null){
            return;
        }


        if (nextNode.word != null){
            unqiueWords.add(nextNode.word);
        }

        board[row][col] = '#';
        myTrie = myTrie.next[trieIDX];

        dfs(board, myTrie, row + 1, col, unqiueWords);
        dfs(board, myTrie, row - 1, col, unqiueWords);
        dfs(board, myTrie, row, col + 1, unqiueWords);
        dfs(board, myTrie, row, col - 1, unqiueWords);

        board[row][col] = letter;
    }

    private Trie buidTrie(String[] words){
        Trie root = new Trie();
        for(String word: words){
            Trie node = root;
            for(char letter: word.toCharArray()) {
                if(node.next[letter-'a'] == null){
                    node.next[letter-'a'] = new Trie();
                }

                node = node.next[letter - 'a'];
            }
            node.word = word;
        }
        return root;
    }

    private class Trie{
        Trie[] next = new Trie[26]; 
        String word;
    }
}