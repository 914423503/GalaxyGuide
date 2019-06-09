package com.ttkj.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttkj.frame.input.InputManager;
import com.ttkj.frame.output.OutputManger;
import com.ttkj.frame.po.Sentence;
import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.impl.CSymbol;
import com.ttkj.frame.symbol.impl.DSymbol;
import com.ttkj.frame.symbol.impl.ISymbol;
import com.ttkj.frame.symbol.impl.LSymbol;
import com.ttkj.frame.symbol.impl.MSymbol;
import com.ttkj.frame.symbol.impl.VSymbol;
import com.ttkj.frame.symbol.impl.XSymbol;

/**
 * ����ָ�ϴ������� ������
 * 
 * @author fengtao
 */
public class GalaxyGuideManager {
	/**
	 * ���������
	 */
	private InputManager inputManager;
	/**
	 * ���������
	 */
	private OutputManger outputManger;
	/**
	 * ȫ�ַ��ż���
	 */
	private List<Symbol> symbols;
	public static void main(String[] args) {
		/**
		 * ���봦����
		 */
		GalaxyGuideManager galaxyGuideManager = new GalaxyGuideManager();
		/**
		 * ��ʼ��
		 */
		galaxyGuideManager.init();
		/**
		 * ��������
		 */
		galaxyGuideManager.inputManager.resolverMetal();
		/**
		 * ����б�
		 */
		List<Sentence> sentences = galaxyGuideManager.inputManager.getSentences();
		/**
		 * �����Լ� ���ż���
		 */
		HashMap<String, String> metalMap = galaxyGuideManager.inputManager.getMetalMap();
		/**
		 * ������
		 */
		galaxyGuideManager.outputManger.outputConsole(metalMap, sentences);
		
		/**
		 * ���������
		 */
	}
	
	private void init(){
		this.symbols = new ArrayList<Symbol>();
		// I V X L C D M
		this.symbols.add(new ISymbol());
		this.symbols.add(new VSymbol());
		this.symbols.add(new XSymbol());
		this.symbols.add(new LSymbol());
		this.symbols.add(new CSymbol());
		this.symbols.add(new DSymbol());
		this.symbols.add(new MSymbol());
		this.inputManager = new InputManager(this.symbols);
		
		this.outputManger = new OutputManger(symbols);
	}
	
}
