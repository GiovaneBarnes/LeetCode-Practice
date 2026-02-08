class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie myTrie = buildTrie(words);
        Set<String> wordsToBeReturned = new HashSet<>();
        for(int row = 0; row < board.length; row++){
            for(int col = 0;  col < board[0].length; col++){
                dfs(board, myTrie, row, col, wordsToBeReturned);
            }
        }
        return new ArrayList<String>(wordsToBeReturned);
    }

    private void dfs(char[][] board, Trie myTrie, int row, int col, Set<String> wordsToBeReturned){
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length){
            return;
        }
        if (board[row][col] == '#'){
            return; 
        } 
        
        char c =  board[row][col];
        int idx = c - 'a';
        if (myTrie.next[idx] == null){
            return;
        }

        if(myTrie.next[idx].word != null){
           wordsToBeReturned.add(myTrie.next[idx].word);
        }

        myTrie = myTrie.next[idx]; 
        char toBeReplaced = board[row][col];
        board[row][col] = '#';
        dfs(board, myTrie, row + 1, col, wordsToBeReturned);
        dfs(board, myTrie, row - 1, col, wordsToBeReturned);
        dfs(board, myTrie, row, col + 1, wordsToBeReturned);
        dfs(board, myTrie, row, col - 1, wordsToBeReturned);
        board[row][col] = toBeReplaced; 
    }


    private Trie buildTrie(String[] words){
        Trie root = new Trie(); 
        for(String word: words){
            Trie node = root;
            for(char letter: word.toCharArray()){
                if (node.next[letter-'a'] == null){
                    node.next[letter-'a'] = new Trie();
                }
                node = node.next[letter-'a'];
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