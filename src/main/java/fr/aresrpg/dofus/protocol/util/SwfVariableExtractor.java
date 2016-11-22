package fr.aresrpg.dofus.protocol.util;

import flash.swf.Action;
import flash.swf.ActionHandler;
import flash.swf.TagDecoder;
import flash.swf.TagHandler;
import flash.swf.actions.ConstantPool;
import flash.swf.actions.Push;
import flash.swf.tags.DoAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SwfVariableExtractor extends TagHandler {

	private Map<String , Object> variables = new HashMap<>();


	public Map<String, Object> getVariables() {
		return variables;
	}

	@Override
	public void doAction(DoAction tag) {
		tag.actionList.visitAll(new SwfActionVariableExtractor());
	}

	private class SwfActionVariableExtractor extends ActionHandler {
		private Object[] stack = new Object[2];
		private String[] pool;
		@Override
		public void push(Push action) {
			stack[1] = stack[0]; //Bubble stack
			if(pool != null && action.value instanceof Short)
				stack[0] = (pool[(short) action.value]);
			else
				stack[0] = action.value;
		}

		@Override
		public void constantPool(ConstantPool action) {
			this.pool = action.pool;
		}

		@Override
		public void setVariable(Action action) {
			variables.put(String.valueOf(stack[1]) , stack[0]);
		}

	}

	public static Map<String, Object> extractVariable(InputStream in) throws IOException {
		TagDecoder t = new TagDecoder(in);
		SwfVariableExtractor extractor = new SwfVariableExtractor();
		t.parse(extractor);
		return extractor.getVariables();
	}
}
