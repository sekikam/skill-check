package q005;

/**
 * 作業時間管理クラス
 * 自由に修正してかまいません
 */
public class WorkData {
    /** 社員番号 */
    private String number;

    /** 部署 */
    private String department;

    /** 役職 */
    private String position;

    /** Pコード */
    private String pCode;

    /** 作業時間(分) */
    private int workTime;

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getpCode() {
        return pCode;
    }

    public int getWorkTime() {
        return workTime;
    }

    WorkData(String[] line) {
        this.number = line[0];
        this.department = line[1];
        this.position = line[2];
        this.pCode = line[3];
        this.workTime = Integer.parseInt(line[4]);
    }
}
