//The idea here is to first blindly consider "*" in p matches nothing and move forward, if it works, that's good. However, if it doesn't work, we come back to it and match with chars in s 
//To know which place we should come back to, we use pStar and sStar variables to place at * and it's corresponding character in s
//We check all the possible combinations here as well and be exhaustive here using two pointers sp and pp
//Time Complexity: O(m*n) worst case and O(mlogn) average case where m and n are lengths of s and p
//Space Complexity: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sp = 0; int pp = 0;
        int sStar = -1; int pStar = -1;
        while(sp < sl){
            if((pp < pl) && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++; pp++;
            } else if((pp < pl) && p.charAt(pp) == '*'){
                pStar = pp;
                sStar = sp;
                pp++;
            } else if(pStar == -1){
                return false;
            } else {
                sStar = sStar + 1;
                sp = sStar;
                pp = pStar + 1;
            }
        }
        while(pp < pl){
            if(p.charAt(pp) == '*'){
                pp++;
            } else{
                return false;
            }
        }
        return true;
    }
}
