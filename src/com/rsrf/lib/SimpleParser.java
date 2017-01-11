package com.rsrf.lib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.*;
import com.rsrf.lib.util.SimpleConstants;

public class SimpleParser {
	
	private static SimpleParser parser;
	
	private YamlReader reader;
	private Map robotData;
	
	public static SimpleParser getInstance() {
		if (parser == null) {
			parser = new SimpleParser();
		}
		return parser;
	}
	
	private SimpleParser() {
		// Constructor
		try {
			this.reader = new YamlReader(new FileReader(SimpleConstants.robotFile));
			this.robotData = (Map) reader.read();
		} catch (FileNotFoundException | YamlException e) {
			e.printStackTrace();
		}
		
	}
	
	public Map getData() {
		return this.robotData;
	}

}
