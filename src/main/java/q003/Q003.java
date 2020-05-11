package q003;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q003 �W�v�ƕ��בւ�
 *
 * �ȉ��̃f�[�^�t�@�C����ǂݍ���ŁA�o������P�ꂲ�Ƃɐ����J�E���g���A�A���t�@�x�b�g�������ɕ��ѕς��ďo�͂��Ă��������B
 * resources/q003/data.txt
 * �P��̏����͈ȉ��ƂȂ�܂�
 * - "I"�ȊO�͑S�ď������ň����i"My"��"my"�͓�����"my"�Ƃ��Ĉ����j
 * - �P���`�ƕ����`�̂悤�ɏ����ł������񂪈ق�ΕʒP��Ƃ��Ĉ����i"dream"��"dreams"�͕ʒP��j
 * - �A�|�X�g���t�B�[��n�C�t���t�̒P���1�P��Ƃ��Ĉ����i"isn't"��"dead-end"�j
 *
 * �o�͌`��:�P��=��
 *
[�o�̓C���[�W]
�i�ȗ��j
highest=1
I=3
if=2
ignorance=1
�i�ȗ��j

 * �Q�l
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * �f�[�^�t�@�C�����J��
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        // �f�[�^�t�@�C���ǂݍ���
        Map<String, Integer> data = new TreeMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()));
            String line;
            while((line = br.readLine()) != null) {
                String lowerStr;
                int cnt;
                // ���������[�v
                // �󔒁A�s���I�h�A�J���}�A�Z�~�R�����͏���
                for (String splitLine : line.split("[ .,;]")) {
                    if (splitLine.isEmpty()) continue;
                    // ��U�S�ď������ɕϊ����ēo�^
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
// �����܂ł̎���: 01���� 00��