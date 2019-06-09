package com.ttkj.frame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.symbol.SymbolCode;

public class Utils {
	/**
	 * 默认编码
	 */
	public static int DEF_CODE = 0X000099;
	/**
	 * input.txt 文件内容中的分割符，用于每个条件的前后主语,宾语
	 * 例如:glob is I
	 */
	public static String DEF_PREDICATE =" is ";
	
	
	/**
	 * 默认输出开始 how many
	 */
	public static String DEF_HOW_MANY = "how many";
	
	/**
	 * 默认输出开始 how much
	 */
	public static String DEF_HOW_MUCH = "how much";
	/**
	 * 默认失败语句
	 */
	public static String DEF_RECKON_ERROR = "I have no idea what you are talking about";
	/**
	 * 默认积分字符
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
	 * 计算
	 * 
	 * @return
	 */
	public static int reckon(String code) {
		int value = Utils.DEF_CODE;
		// 如果计算的字符串只有一位 直接输出
		if (code.length() <= 2) {
			return convert(code);
		}
		int count = 0;
		// 每前一個數
		char start = '0';
		// 每后一個數
		char end = '0';
		int index = 0;
		int countTemp = 0;
		// 先判断 三个I、X、C、M
		count = reckon3ByCount(code);
		code = reckon3ByCode(code);
		char[] codeArray = code.toCharArray();
		// 倒序递归
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

		// 如果长度为奇數则第一个没计算
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
	 * 罗马符转换阿拉伯
	 * 
	 * @param code
	 * @return
	 */
	public static Integer convert(String code) {
		int value = Utils.DEF_CODE;
		// 如果计算的字符串只有一位 直接输出
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
	 * 错误编码
	 */
	public static class ERROR_CODE {
		/**
		 * 转换失败
		 */
		public int CONVERT_ERROR = 0X0001;

	}

	/**
	 * 判断 三个I、X、C、M 计算count
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
	 * 判断 三个I、X、C、M 计算 code
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
	 * 判断字符串中包含字符串的数量
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
	 * 判断对象是否为空
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
	 * 读取input.txt 文件，返回内容集合
	 * input.txt 必须和Utils类在同一目录
	 * @return
	 */
	public static List<String> readInInputFile() {
		/**
		 * 内容集合
		 */
		List<String> fileValues = new ArrayList<String>();
		/**
		 * 项目根路径
		 */
		String proPath = Utils.class.getClass().getResource("/").getPath();
		/**
		 * Utils文件相对路径
		 */
		String utilPath = Utils.class.getPackage().getName().replace(".", "/");
		/**
		 * 文件名
		 */
		String fileName = "input.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(proPath + "/" + utilPath + "/" + fileName));
			//先读取一次，再循环读取，知道读取完毕
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
