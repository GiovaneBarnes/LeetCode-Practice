class Solution {
    public int numIslands(char[][] grid) {
        int numOfIslands = 0;
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                if (grid[row][col] == '1'){
                    dfs(grid, row, col, numOfIslands);
                    numOfIslands++; 
                }
            }
        }
        return numOfIslands;
    }

    public void dfs(char[][] grid, int row, int col, int numOfIslands){   
        if (row < 0 || col < 0 || col >= grid[0].length || row >= grid.length){
            return;
        }

        if(grid[row][col] == '0'){
            return;
        }

        grid[row][col] = '0';
        dfs(grid, row + 1, col, numOfIslands);
        dfs(grid, row - 1, col, numOfIslands);
        dfs(grid, row, col + 1, numOfIslands);
        dfs(grid, row, col - 1, numOfIslands);

 
    }
}