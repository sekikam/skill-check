package q004;

import java.util.Random;

/**
 * ���X�g�Ǘ��N���X
 * ���̃N���X��ύX���Ă͂����܂���
 */
public class ListManager {
    private int[] dataList;
    private int compareCount;
    private int exchangeCount;

    public ListManager() {
        // �f�[�^�������_���ɍ쐬����
        Random random = new Random();
        dataList = new int[100];
        for (int i = 0; i < dataList.length; i++) {
            dataList[i] = random.nextInt(10000);
        }
    }

    /**
     * 2�̃f�[�^���r����
     *
     * @param index1
     * @param index2
     * @return -1:index1�̃f�[�^��������, 1:index2�̃f�[�^��������, 0:�����f�[�^
     */
    public int compare(int index1, int index2) {
        compareCount++;
        return (int) Math.signum(dataList[index1] - dataList[index2]);
    }

    /**
     * 2�̃f�[�^�����ւ���
     *
     * @param index1
     * @param index2
     */
    public void exchange(int index1, int index2) {
        exchangeCount++;
        int tmp = dataList[index1];
        dataList[index1] = dataList[index2];
        dataList[index2] = tmp;
    }

    /**
     * �\�[�g���������s��ꂽ�����`�F�b�N����
     */
    public void checkResult() {
        int data = dataList[0];
        for (int i = 1; i < dataList.length; i++) {
            if (data > dataList[i]) {
                throw new RuntimeException("�\�[�g����Ă��Ȃ�: [" + (i - 1) + "]=" + data + ", [" + i + "]=" + dataList[i]);
            }
            data = dataList[i];
        }
        System.out.println("�\�[�gOK: ��r=" + compareCount + ", ����ւ�=" + exchangeCount);
    }

    /**
     * �f�[�^�̃T�C�Y���擾����
     *
     * @return
     */
    public int size() {
        return dataList.length;
    }
}
