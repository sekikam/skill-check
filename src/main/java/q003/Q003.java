package q003;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        // データファイル読み込み
        Map<String, Integer> data = new TreeMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()));
            String line;
            while((line = br.readLine()) != null) {
                String lowerStr;
                int cnt;
                // 分割分ループ
                // 空白、ピリオド、カンマ、セミコロンは除去
                for (String splitLine : line.split("[ .,;]")) {
                    if (splitLine.isEmpty()) continue;
                    // 一旦全て小文字に変換して登録
                    lowerStr = splitLine.toLowerCase();
                    if (data.containsKey(lowerStr)) {
                        cnt = data.get(lowerStr);
                        data.replace(lowerStr, ++cnt);
                    } else {
                        data.put(lowerStr, 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String displayKey;
        for (String key : data.keySet()) {
            displayKey = "i".equals(key) ? "I" : key;
            System.out.println(displayKey + "=" + data.get(key));
        }
    }
}
// 完成までの時間: 01時間 00分