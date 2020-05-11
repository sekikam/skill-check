package q002;

import java.util.*;

/**
 * Q002 ���בւ���
 *
 * dataList�� "ID,����" �̌`����20�̃f�[�^������܂��B
 * �����ID���ɕ��ׂĕ\������v���O�������L�q���Ă��������B
 *
 * dataList�̒�`��ύX���Ă͂����܂���B
 *
 *
 [�o�͌��ʃC���[�W]
 1,�ɓ�
 2,���
 �i�ȗ��j
 9,����
 10,���
 11,����
 �i�ȗ��j
 20,�n��
 */
public class Q002 {
    /**
     * �f�[�^�ꗗ
     */
    private static final String[] dataList = {
            "8,����",
            "10,���",
            "11,����",
            "12,�c��",
            "20,�n��",
            "1,�ɓ�",
            "18,�R�{",
            "13,����",
            "5,����",
            "3,����",
            "19,�g�c",
            "17,�R�c",
            "7,���X��",
            "16,�R��",
            "6,�ē�",
            "15,���{",
            "2,���",
            "4,�ؑ�",
            "14,��",
            "9,����"
    };
    public static void main(String[] args) {
        List<String> formatDataList = new ArrayList<>();
        // 0�p�f�B���O���ă��X�g�ɓo�^
        for (String str : dataList) {
            String[] no = str.split(",");
            formatDataList.add(String.format("%02d", Integer.parseInt(no[0])).toString() + "," + no[1]);
        }
        Collections.sort(formatDataList);

        // 0�p�f�B���O�������ďo��
        for (String str : formatDataList) {
            String[] no = str.split(",");
            System.out.println(no[0].replaceFirst("^0+","") + "," + no[1]);
        }
    }
}
// �����܂ł̎���: 01���� 00��