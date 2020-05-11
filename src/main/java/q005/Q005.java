package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {

        // data登録
        final List<WorkData> WorkDataList;
        try {
            WorkDataList = regData();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // 集計
        // (1) 役職別の合計作業時間
        calcWorkTimePosition(WorkDataList);
        // (2) Pコード別の合計作業時間
        calcWorkTimePCode(WorkDataList);
        // (3) 社員番号別の合計作業時間
        calcWorkTimeNumber(WorkDataList);
    }

    private static void calcWorkTimePosition(List<WorkData> workDataList) {
        Map<String, Integer> calcWorkTime = workDataList.stream()
                .collect(Collectors.groupingBy(
                        WorkData::getPosition,
                        Collectors.summingInt(WorkData::getWorkTime))
                );
        display(calcWorkTime);
    }

    private static void calcWorkTimePCode(List<WorkData> workDataList) {
        Map<String, Integer> calcWorkTime = workDataList.stream()
                .collect(Collectors.groupingBy(
                        WorkData::getpCode,
                        Collectors.summingInt(WorkData::getWorkTime))
                );
        display(calcWorkTime);
    }
    private static void calcWorkTimeNumber(List<WorkData> workDataList) {
        Map<String, Integer> calcWorkTime = workDataList.stream()
                .collect(Collectors.groupingBy(
                        WorkData::getNumber,
                        Collectors.summingInt(WorkData::getWorkTime))
                );
        display(calcWorkTime);
    }

    private static void display(Map<String, Integer> calcWorkTime) {
        calcWorkTime.forEach((key, value) -> System.out.println(
                String.format("%s: %d時間%d分", key, value / 60, value % 60)));
    }

    private static List<WorkData> regData() throws IOException {

        List<WorkData> workDataList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()));
        // 1行飛ばす
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            WorkData workData = new WorkData(line.split(","));
            workDataList.add(workData);
        }
        return workDataList;
    }
}
// 完成までの時間: 02時間 00分