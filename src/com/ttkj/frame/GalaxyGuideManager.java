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
 * 银河指南处理中心 启动类
 * 
 * @author fengtao
 */
public class GalaxyGuideManager {
	/**
	 * 输入管理器
	 */
	private InputManager inputManager;
	/**
	 * 输出管理器
	 */
	private OutputManger outputManger;
	/**
	 * 全局符号集合
	 */
	private List<Symbol> symbols;
	public static void main(String[] args) {
		/**
		 * 中央处理器
		 */
		GalaxyGuideManager galaxyGuideManager = new GalaxyGuideManager();
		/**
		 * 初始化
		 */
		galaxyGuideManager.init();
		/**
		 * 读入数据
		 */
		galaxyGuideManager.inputManager.resolverMetal();
		/**
		 * 语句列表
		 */
		List<Sentence> sentences = galaxyGuideManager.inputManager.getSentences();
		/**
		 * 金属以及 符号集合
		 */
		HashMap<String, String> metalMap = galaxyGuideManager.inputManager.getMetalMap();
		/**
		 * 输出结果
		 */
		galaxyGuideManager.outputManger.outputConsole(metalMap, sentences);
		
		/**
		 * 输出处理结果
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
