import java.io.*;
import java.util.*;

public class BJ4358 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 나무의 정보를 map으로 저장해준다.
        Map<String, Integer> map = new HashMap<>();
        int tot = 0; // 총 나무의 수를 cnt할 변수, map.size()는 안됨.

        String s = "";
        // 입력이 들어올때까지 반복
        while ((s = br.readLine()) != null) {
            if (map.containsKey(s)) {
                // 이미 들어가있는 나무라면 갯수를 +1
                map.put(s, map.get(s) + 1);
            } else {
                // 처음 들어가는 나무라면 갯수는 1
                map.put(s, 1);
            }
            tot++; // 총 나무 갯수 증가
        } // end Input

        // map의 내용을 key값 순서로 정렬
        // map은 순서가 존재하지 않기 때문에 List에 담거나 배열로 만들어줘야 한다
        Object[] mapkey = map.keySet().toArray();
        Arrays.sort(mapkey);

        if (tot > 0) {
            for (Object key : mapkey) {
                System.out.println(key + " " + String.format("%.4f", 100.0 * map.get(key) / tot));
            }
        } // end Print

    } // end main()
} // end Class