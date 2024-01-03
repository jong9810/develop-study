package org.example.math;

import java.io.*;
import java.math.BigInteger;

// BigInteger 클래스를 사용해보았다.
// https://velog.io/@gayeong39/%EB%B0%B1%EC%A4%80-4134-%EB%8B%A4%EC%9D%8C-%EC%86%8C%EC%88%98-JAVA 참고
public class BOJ_4134 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        long[] numbers = new long[T];
        for (int i = 0; i < T; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        print(numbers);
        br.close();
    }

    private static void print(long[] numbers) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < numbers.length; i++) {
            BigInteger bigInteger = new BigInteger(String.valueOf(numbers[i]));
            bw.write(findPrimeNumber(bigInteger) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static BigInteger findPrimeNumber(BigInteger bigInteger) {
        if (bigInteger.isProbablePrime(10)) {
            return bigInteger;
        }
        else {
            return bigInteger.nextProbablePrime();
        }
    }
}
