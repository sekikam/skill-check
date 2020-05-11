package q007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * q007 最短経路探索
 *
 * 壁を 'X' 通路を ' ' 開始を 'S' ゴールを 'E' で表現された迷路で、最短経路を通った場合に
 * 何歩でゴールまでたどり着くかを出力するプログラムを実装してください。
 * もし、ゴールまで辿り着くルートが無かった場合は -1 を出力してください。
 * なお、1歩は上下左右のいずれかにしか動くことはできません（斜めはNG）。
 *
 * 迷路データは MazeInputStream から取得してください。
 * 迷路の横幅と高さは毎回異なりますが、必ず長方形（あるいは正方形）となっており、外壁は全て'X'で埋まっています。
 * 空行が迷路データの終了です。
 *

[迷路の例]
XXXXXXXXX
XSX    EX
X XXX X X
X   X X X
X X XXX X
X X     X
XXXXXXXXX

[答え]
14
 */
public class Q007 {

    static String[][] mazeInfo;
    static Stack roadStack;

    public static void main(String[] args) {

        InputStreamReader ir = new InputStreamReader(new MazeInputStream());
        BufferedReader br = new BufferedReader(ir);
        final List<String[]> allList = br.lines()
                .map(line -> line.split(""))
                .collect(Collectors.toList());

        mazeInfo = new String[allList.size()][allList.get(0).length];
        AtomicInteger ai = new AtomicInteger();
        allList.forEach(s -> mazeInfo[ai.getAndIncrement()] = s);

        roadStack = new Stack<>();
        RoadInfo start = getStart();
        if (start == null) {
            System.out.println("Sなし");
            return;
        }
        roadStack.push(start);
        mazeDisplay();
        System.out.println("[答え] : " + execMaze());
    }

    private static RoadInfo getStart() {

        // start地点を探す
        RoadInfo start = null;
        for (int i = 0; i < mazeInfo.length; i++) {
            for (int j = 0; j < mazeInfo[0].length; j++) {
                if ("S".equals(mazeInfo[i][j])) {
                    return new RoadInfo(i, j, mazeInfo[i][j]);
                }
            }
        }
        return null;
    }

    private static int execMaze() {

        while(true){
            searchRoad((RoadInfo) roadStack.peek());

            RoadInfo now = (RoadInfo) roadStack.peek();
            switch(now.getValue()) {
                case "S" :
                    // 最初まで戻ってきた=ゴールまで辿り着くルートが無かった
                    return -1;
                case "E" :
                    mazeDisplay();
                    return roadStack.size();
            }
        }
    }

    /*
     *通れる道があるかどうか
     * あれば
     *  pushする
     *  通過済フラグをtrue
     *  valueに”●”を設定
     * なければpopする
     */
    private static void searchRoad(RoadInfo target) {

        // 左、上、右、下の順序で道を検索
        int[] nextXs = {target.getX() - 1, target.getX()    , target.getX() + 1, target.getX()    };
        int[] nextYs = {target.getY()    , target.getY() - 1, target.getY()    , target.getY() + 1};

        for ( int cnt = 0; cnt < nextXs.length; cnt++ ){
            if (checkTargetValue(nextXs[cnt], nextYs[cnt])) {
                roadStack.push(new RoadInfo(nextXs[cnt], nextYs[cnt], mazeInfo[nextXs[cnt]][nextYs[cnt]]));
                if (!"S".equals(mazeInfo[target.getX()][target.getY()])){
                    mazeInfo[target.getX()][target.getY()] = "@";
                }
                mazeDisplay();
                return;
            }
        }
        if (!"S".equals(mazeInfo[target.getX()][target.getY()])){
            mazeInfo[target.getX()][target.getY()] = " ";
        }
        roadStack.pop();
    }

    private static boolean checkTargetValue(int x, int y) {
        String target;
        try {
            target =  mazeInfo[x][y];
        } catch(IndexOutOfBoundsException e) {
            return false;
        }
        return " ".equals(target);
    }
    private static void mazeDisplay(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String[] i : mazeInfo) {
            for (String j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

}
// 完成までの時間: xx時間 xx分