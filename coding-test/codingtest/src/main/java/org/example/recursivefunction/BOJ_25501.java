package org.example.recursivefunction;

import java.io.*;

public class BOJ_25501 {
    public static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfTest = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < numOfTest; tc++) {
            String word = br.readLine();
            bw.write(isPalindrome(word) + " " + cnt + "\n");
            cnt = 0;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int recursion(String s, int l, int r){
        cnt++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }
}
