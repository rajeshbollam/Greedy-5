//The idea here is to assign shortest worker bike pair first and continue this till all workers are assigned. 
//For this, we calculate distance among all worker bike pairs and put in hashmap and the we do bucket sort and iterate through the min distance to max distance and find the pairs
//If a pair is assigned, we mark is assigned in both worker assigned and bike occupied arrays
//Time Complexity: O(m*n) where m and n are lengths of bikes and workers arrays
//Space Complexity: O(m*n)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution{
    public int[] assignBikes(int[][] workers, int[][] bikes){
        int n = workers.length;
        int m = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int[] result = new int[n];
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                int dist = findManhattanDistance(workers, bikes, i, j);
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }
        int count = 0; //because we can only have n workers, we can stop for loop when we get all of the workers assigned instead of going through all the distances and pairs in map
        boolean[] assigned = new boolean[n]; //workers
        boolean[] occupied = new boolean[m]; //bikes
        for(int dist = min; dist<=max && count < n; dist++){
            List<int[]> li = map.get(dist);
            if(li != null){
                for(int[] wb: li){
                    int w = wb[0];
                    int b = wb[1];
                    if(!assigned[w] && !occupied[b]){
                        assigned[w] = true;
                        occupied[b] = true;
                        count++;
                        result[w] = b;
                    }
                }
            }            
        }

        return result;
    }

    private int findManhattanDistance(int[][] workers, int[][] bikes, int i, int j){
        int[] w = workers[i];
        int[] b = bikes[j];
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }
}