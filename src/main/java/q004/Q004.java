package q004;

import java.util.stream.IntStream;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        ListManager data = new ListManager();

        int cnt = data.size();
        for (int index1 = 0; index1 < cnt; index1++) {
            for (int index2 = index1 + 1; index2 < cnt; index2++) {
                if (data.compare(index1, index2) == 1) data.exchange(index1, index2);
            }
        }
        data.checkResult();
    }
}
// 完成までの時間: 00時間 30分