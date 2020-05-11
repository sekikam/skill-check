package q006;

import q006.value.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Q006 ��C��ǂ�ŉ��C
 *
 * �W�����͂���u�t�|�[�����h�L�@�v�ŋL�ڂ��ꂽ1�s�̓��͂��󂯎��A���̌v�Z���ʂ��o�͂��鏈�����������Ă��������B
 * ��������͎̂l�����Z�i+ - * /�j�ł��B
 *
 * https://ja.wikipedia.org/wiki/%E9%80%86%E3%83%9D%E3%83%BC%E3%83%A9%E3%83%B3%E3%83%89%E8%A8%98%E6%B3%95
 *
 * �������A����͈ȉ��̎������I����Ă��܂��B
 * - �t�|�[�����h�L�@�𕪉����āA�v�Z���₷���l���X�g�ɕϊ����鏈���̈ꕔ�iQ006.parseLine�j
 * - �v�Z���₷���l�Ƃ��ĊǗ����邽�߂̃N���X�Q�̈ꕔ�iIValue,DecimalValue,PlusValue�j
 *
 * �r���܂ŏI����Ă����������肭���p���Ȃ���A�c��̏������������Ă��������B
 * �G���[���͂̃`�F�b�N�͕s�v�ł��B
 *
 * ���s��F
 *
 * ���́j 3 1.1 0.9 + 2.0 * -
 * �o�́j -1
 * �i�܂��� -1.00 �ȂǁA�����_��0�����Ă��悢�j
 */
public class Q006 {

    public static void main(String[] args) {

        // �W�����͏���
        String lineText = getInputLine();
        // �v�Z����
        BigDecimal result = calcRPN(lineText);
        // ���ʏo��
        System.out.println("�o�́j " + result);
    }

    /* �W�����͏���
     * @return ���͂��ꂽ������i���s�܂Łj
     */
    private static String getInputLine() {
        Scanner scan = new Scanner(System.in);
        System.out.print("���́j ");
        String line = scan.nextLine();
        scan.close();
        return line;
    }

    private static BigDecimal calcRPN(String lineText) {

        // ����
        List<IValue> parseList = parseLine(lineText);
        Stack<BigDecimal> rpnStack = new Stack<>();
//        for (IValue rpm : parseList) {
//            rpm.execute(rpnStack);
//        }
        // �����_���Ŏ���
        parseList.stream().forEach((que) -> que.execute(rpnStack));
        return rpnStack.pop();
    }

    /**
     * �t�|�[�����h�ŋL�ڂ��ꂽ1�s�̃e�L�X�g�𕪉�����
     * @param lineText 1�s�e�L�X�g
     * @return �������ꂽ�l���X�g
     */
    private static List<IValue> parseLine(String lineText) {
        List<IValue> resultList = new ArrayList<>();
        // �󔒕����ŋ�؂��ă��[�v����
        for (String text: lineText.split("[\\s]+")) {
            switch (text) {
                case "+":   // �����Z
                    resultList.add(new PlusValue());
                    break;
                case "-":   // �����Z
                    resultList.add(new SubtractValue());
                    break;
                case "*":   // �|���Z
                    resultList.add(new MultiplyValue());
                    break;
                case "/":   // ����Z
                    resultList.add(new DivideValue());
                    break;
                default:    // ���̑��͐��l�Ƃ��Ĉ���
                    resultList.add(new DecimalValue(text));
                    break;
            }
        }
        return resultList;
    }
}
// �����܂ł̎���: 02���� 00��