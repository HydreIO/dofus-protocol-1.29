package fr.aresrpg.dofus.util;

import flash.swf.Action;
import flash.swf.ActionHandler;
import flash.swf.TagDecoder;
import flash.swf.TagHandler;
import flash.swf.actions.ConstantPool;
import flash.swf.actions.Push;
import flash.swf.tags.DoAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class SwfVariableExtractor extends TagHandler {

	private Map<String, Object> variables = new HashMap<>();

	public Map<String, Object> getVariables() {
		return variables;
	}

	@Override
	public void doAction(DoAction tag) {
		tag.actionList.visitAll(new SwfActionVariableExtractor());
	}

	private class SwfActionVariableExtractor extends ActionHandler {
		private LinkedList<Object> stack = new LinkedList<>();
		private String path = "";
		private String[] pool;

		@Override
		public void push(Push action) {
			if (pool != null && action.value instanceof Short)
				stack.add(pool[(short) action.value]);
			else
				stack.add(action.value);
			//System.out.println(stack.peek());
			//System.out.println("push");
		}

		@Override
		public void constantPool(ConstantPool action) {
			this.pool = action.pool;
		}

		@Override
		public void getMember(Action action) {
			path += stack.removeLast() + ".";
			//System.out.println(path);
		}

		@Override
		public void getVariable(Action action) {
			path += stack.removeLast() + ".";
			//System.out.println(path);
		}

		@Override
		public void setMember(Action action) {
			setVariable(null);
		}

		@Override
		public void setVariable(Action action) {
			//System.out.println(stack);
			variables.put(path, stack.removeLast());
			path = "";
		}

		@Override
		public void initArray(Action action) {
			//System.out.println("array");
		}

		@Override
		public void initObject(Action action) {
			Map<String , Object> members = new HashMap<>();
			//System.out.println(stack);
			String pop = stack.removeFirst().toString();
			//System.out.println(pop);
			path += pop;
			for(int i = 0 ; i < stack.size() ; i+=2) {
				String name = stack.removeLast().toString();
				Object data = stack.removeLast();
				members.put(name, data);
			}
			//System.out.println(members);
			stack.add(members);
		}
	}

	public static Map<String, Object> extractVariable(InputStream in) throws IOException {
		TagDecoder t = new TagDecoder(in);
		SwfVariableExtractor extractor = new SwfVariableExtractor();
		t.parse(extractor);
		return extractor.getVariables();
	}
}
