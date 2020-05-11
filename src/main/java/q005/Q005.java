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
 * Q005 �f�[�^�N���X�Ɨl�X�ȏW�v
 *
 * �ȉ��̃t�@�C����ǂݍ���ŁAWorkData�N���X�̃C���X�^���X���쐬���Ă��������B
 * resources/q005/data.txt
 * (�擪�s�̓^�C�g���Ȃ̂œǂݎ����X�L�b�v����)
 *
 * �ǂݍ��񂾃f�[�^���ȉ��ŏW�v���ďo�͂��Ă��������B
 * (1) ��E�ʂ̍��v��Ǝ���
 * (2) P�R�[�h�ʂ̍��v��Ǝ���
 * (3) �Ј��ԍ��ʂ̍��v��Ǝ���
 * ��L���ړ��ł̏o�͏��͖₢�܂���B
 *
 * ��Ǝ��Ԃ� "xx����xx��" �̌`���ɂ��Ă��������B
 * �܂��AWorkData�N���X�͎��R�ɏC�����Ă��������B
 *
[�o�̓C���[�W]
����: xx����xx��
�ے�: xx����xx��
���: xx����xx��
Z-7-31100: xx����xx��
I-7-31100: xx����xx��
T-7-30002: xx����xx��
�i�ȗ��j
194033: xx����xx��
195052: xx����xx��
195066: xx����xx��
�i�ȗ��j
 */
public class Q005 {
    /**
     * �f�[�^�t�@�C�����J��
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {

        // data�o�^
        final List<WorkData> WorkDataList;
        try {
            WorkDataList = regData();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // �W�v
        // (1) ��E�ʂ̍��v��Ǝ���
        calcWorkTimePosition(WorkDataList);
        // (2) P�R�[�h�ʂ̍��v��Ǝ���
        calcWorkTimePCode(WorkDataList);
        // (3) �Ј��ԍ��ʂ̍��v��Ǝ���
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
                String.format("%s: %d����%d��", key, value / 60, value % 60)));
    }

    private static List<WorkData> regData() throws IOException {

        List<WorkData> workDataList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()));
        // 1�s��΂�
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            WorkData workData = new WorkData(line.split(","));
            workDataList.add(workData);
        }
        return workDataList;
    }
}
// �����܂ł̎���: 02���� 00��