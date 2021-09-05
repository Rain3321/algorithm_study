import java.io.*;
import java.util.*;

public class BJ22860 {
    static ArrayList<Folder> folders = new ArrayList<>();
    static HashMap<String, Integer> folderIndex = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 폴더의 총 개수
        int M = Integer.parseInt(st.nextToken()); // 파일의 총 개수

        int idx = 0;
        folders.add(new Folder("main"));
        folderIndex.put("main", idx++);

        ArrayList<String[]> temp = new ArrayList<>();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String upper = st.nextToken();
            String now = st.nextToken();
            String check = st.nextToken();
            // boolean isFolder = Integer.parseInt(st.nextToken()) == 1;

            //if(isFolder) { // 폴더라면
                folders.add(new Folder(now));
                folderIndex.put(now, idx++);
                temp.add(new String[] {upper, now, check});
               // folders.get(folderIndex.get(upper)).addSubFolder(now);
            //}
            //else { // 파일이라면
               // folders.get(folderIndex.get(upper)).addFile(now);
            //}
        }
        for (String[] cur : temp) {
            if(Integer.parseInt(cur[2]) == 1) {
                folders.get(folderIndex.get(cur[0])).addSubFolder(cur[1]);
            } else {
                folders.get(folderIndex.get(cur[0])).addFile(cur[1]);
            }
        }
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            String[] query = br.readLine().split("/");
            allFiles = new HashSet<>();
            fileCnt = 0;
            dfs(folders.get(folderIndex.get(query[query.length - 1]))); // 마지막에 위치한 폴더
            bw.write(allFiles.size() + " " + fileCnt + "\n");

        }
        bw.flush();
        bw.close();
        br.close();

    }
    static HashSet<String> allFiles;
    static int fileCnt;
    private static void dfs(Folder folder) {

        // 파일 추가
        for (String file : folder.files) {
            allFiles.add(file);
            fileCnt++;
        }

        // 현재 폴더에서 하위폴더로 재귀
        for (String sub: folder.subs) {
            dfs(folders.get(folderIndex.get(sub)));
        }
    }

    static class Folder {
        String name;
        List<String> files;
        List<String> subs;

        public Folder(String name) {
            this.name = name;
            files = new ArrayList<>();
            subs = new ArrayList<>();
        }
        public void addFile(String file) {
            files.add(file);
        }

        public void addSubFolder(String folder) {
            subs.add(folder);
        }
    }
}
