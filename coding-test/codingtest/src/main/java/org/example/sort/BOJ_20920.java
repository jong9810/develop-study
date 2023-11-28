package org.example.sort;

import java.io.*;
import java.util.*;

// TreeMap, TreeSet과 Comparator를 사용하여 HashMap을 정렬해보았다.
public class BOJ_20920 {
    /*/ 방법 1.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() >= M) {
                map.merge(word, 1, Integer::sum);
            }
        }
        Map<String, Integer> sortedMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Objects.equals(map.get(o1), map.get(o2))) {
                    if (o1.length() == o2.length())
                        return o1.compareTo(o2);
                    return o2.length() - o1.length();
                }
                return map.get(o2) - map.get(o1);
            }
        });
        sortedMap.putAll(map);
        for (String s : sortedMap.keySet()) {
            bw.write(s + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    */
    // 방법 2.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfWords = Integer.parseInt(st.nextToken());
        int minLen = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> words = new HashMap<>();
        while (numOfWords-- > 0) {
            String word = br.readLine();
            if (word.length() < minLen) continue;
            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        TreeSet<Word> wordList = new TreeSet<>();
        for (Map.Entry<String, Integer> word : words.entrySet()) {
            wordList.add(new Word(word.getKey(), word.getValue()));
        }

        for (Word word : wordList) {
            bw.write(word.value + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}

class Word implements Comparable<Word> {
    final String value;
    final int freq;
    final int len;

    public Word(String value, int freq) {
        this.value = value;
        this.freq = freq;
        this.len = value.length();
    }

    @Override
    public int compareTo(Word w) {
        if (this.freq == w.freq) {
            if (this.len == w.len) {
                return this.value.compareTo(w.value);
            } else {
                return w.len - this.len;
            }
        } else {
            return w.freq - this.freq;
        }
    }
}
