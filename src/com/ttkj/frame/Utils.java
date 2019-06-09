package com.ttkj.frame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.symbol.SymbolCode;

public class Utils {
	/**
	 * Ĭ�ϱ���
	 */
	public static int DEF_CODE = 0X000099;
	/**
	 * input.txt �ļ������еķָ��������ÿ��������ǰ������,����
	 * ����:glob is I
	 */
	public static String DEF_PREDICATE =" is ";
	
	
	/**
	 * Ĭ�������ʼ how many
	 */
	public static String DEF_HOW_MANY = "how many";
	
	/**
	 * Ĭ�������ʼ how much
	 */
	public static String DEF_HOW_MUCH = "how much";
	/**
	 * Ĭ��ʧ�����
	 */
	public static String DEF_RECKON_ERROR = "I have no idea what you are talking about";
	/**
	 * Ĭ�ϻ����ַ�
	 */
	public static String DEF_CREDITS_NAME = "Credits";
	
	public static void main(String[] args) {
		// System.out.println(convert("M"));
		// System.out.println(convert("MM"));
		// System.out.println(reckon("MMVI"));
		// System.out.println(reckon("MCMXLIV"));
		// System.out.println(reckon("MMM"));
		//System.out.println(reckon("MCMIII"));
		System.out.println(reckon("MMXLII"));
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public static int reckon(String code) {
		int value = Utils.DEF_CODE;
		// ���������ַ���ֻ��һλ ֱ�����
		if (code.length() <= 2) {
			return convert(code);
		}
		int count = 0;
		// ÿǰһ����
		char start = '0';
		// ÿ��һ����
		char end = '0';
		int index = 0;
		int countTemp = 0;
		// ���ж� ����I��X��C��M
		count = reckon3ByCount(code);
		code = reckon3ByCode(code);
		char[] codeArray = code.toCharArray();
		// ����ݹ�
		for (int i = codeArray.length - 1; i >= 0; i--) {
			if (((index = index + 1) % 2) == 1) {
				start = codeArray[i];
			} else {
				end = codeArray[i];
				countTemp = convert(String.valueOf(end) + String.valueOf(start));
				if (countTemp == Utils.DEF_CODE) {
					return value;
				}
				count = count + countTemp;
			}
		}

		// �������Ϊ�攵���һ��û����
		if (codeArray.length % 2 == 1) {
			countTemp = convert(String.valueOf(codeArray[0]));
			if (countTemp == Utils.DEF_CODE) {
				return value;
			}
			count = count + countTemp;
		}
		value = count;
		return value;
	}

	/**
	 * �����ת��������
	 * 
	 * @param code
	 * @return
	 */
	public static Integer convert(String code) {
		int value = Utils.DEF_CODE;
		// ���������ַ���ֻ��һλ ֱ�����
		if (code.length() == 1) {
			value = SymbolCode.getValueByCodeValue(code);
		} else if (code.length() == 2) {
			char[] codeArrays = code.trim().toCharArray();
			int befor = SymbolCode.getValueByCodeValue(String.valueOf(codeArrays[0]));
			int after = SymbolCode.getValueByCodeValue(String.valueOf(codeArrays[1]));
			if (befor != Utils.DEF_CODE && after != Utils.DEF_CODE) {
				if (befor < after) {
					value = after - befor;
				} else {
					value = after + befor;
				}
			}
		}
		return value;
	}

	/**
	 * �������
	 */
	public static class ERROR_CODE {
		/**
		 * ת��ʧ��
		 */
		public int CONVERT_ERROR = 0X0001;

	}

	/**
	 * �ж� ����I��X��C��M ����count
	 * 
	 * @param code
	 * @return count
	 */
	private static int reckon3ByCount(String code) {
		int count = 3 * countString(code, "III");
		count = count + 30 * countString(code, "XXX");
		count = count + 300 * countString(code, "CCC");
		count = count + 3000 * countString(code, "MMM");
		return count;
	}

	/**
	 * �ж� ����I��X��C��M ���� code
	 * 
	 * @param code
	 * @return count
	 */
	public static String reckon3ByCode(String code) {
		code = code.replace("III", "");
		code = code.replace("XXX", "");
		code = code.replace("CCC", "");
		code = code.replace("MMM", "");
		return code;
	}

	/**
	 * �ж��ַ����а����ַ���������
	 * 
	 * @param code
	 * @param s
	 * @return
	 */
	public static int countString(String code, String s) {
		int count = 0;
		while (code.indexOf(s) != -1) {
			code = code.substring(code.indexOf(s) + 1, code.length());
			count++;
		}
		return count;
	}

	/**
	 * �ж϶����Ƿ�Ϊ��
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isEmpty(Object code) {
		boolean isValue = code == null;
		if (!isValue && code instanceof String) {
			isValue = String.valueOf(code).isEmpty();
		}
		return isValue;
	}
	
	
	/**
	 * ��ȡinput.txt �ļ����������ݼ���
	 * input.txt �����Utils����ͬһĿ¼
	 * @return
	 */
	public static List<String> readInInputFile() {
		/**
		 * ���ݼ���
		 */
		List<String> fileValues = new ArrayList<String>();
		/**
		 * ��Ŀ��·��
		 */
		String proPath = Utils.class.getClass().getResource("/").getPath();
		/**
		 * Utils�ļ����·��
		 */
		String utilPath = Utils.class.getPackage().getName().replace(".", "/");
		/**
		 * �ļ���
		 */
		String fileName = "input.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(proPath + "/" + utilPath + "/" + fileName));
			//�ȶ�ȡһ�Σ���ѭ����ȡ��֪����ȡ���
			String lineValue = br.readLine();
			while (!Utils.isEmpty(lineValue)) {
				if(!lineValue.startsWith("#")) {
					fileValues.add(lineValue);
				}
				lineValue = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileValues;
	}

}
