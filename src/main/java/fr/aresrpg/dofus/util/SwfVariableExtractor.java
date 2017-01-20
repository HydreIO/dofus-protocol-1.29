/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class SwfVariableExtractor extends TagHandler {

	private Map<String, Object> variables = new LinkedHashMap<>();

	public Map<String, Object> getVariables() {
		return variables;
	}

	@Override
	public void doAction(DoAction tag) {
		tag.actionList.visitAll(new SwfActionVariableExtractor());
	}

	private class SwfActionVariableExtractor extends ActionHandler {
		private Stack<Object> stack = new Stack<>();
		private String[] pool;

		@Override
		public void push(Push action) {
			if (pool != null && action.value instanceof Short)
				stack.push(pool[(short) action.value]);
			else
				stack.push(action.value);
		}

		@Override
		public void constantPool(ConstantPool action) {
			this.pool = action.pool;
		}

		@Override
		public void getMember(Action action) {
			putPath();
		}

		@Override
		public void getVariable(Action action) {
			putPath();
		}

		private void putPath() {
			if(stack.size() > 1) {
				Object first = stack.pop();
				stack.push(stack.pop() + "." + first);
			}
		}

		@Override
		public void setMember(Action action) {
			putVariable();
		}

		@Override
		public void setVariable(Action action) {
			putVariable();
		}

		@Override
		public void callMethod(Action action) {
			putPath();
			//Swap last 2 values
			Object first = stack.pop();
			Object second = stack.pop();
			stack.push(first);
			stack.push(second);
			putVariable();
		}

		private void putVariable() {
			Object value = stack.pop();
			String name = String.valueOf(stack.pop());
			variables.put(name, value);
		}

		@Override
		public void newObject(Action action) {
			Object type = stack.pop();
			stack.pop(); //Id
			putPath();
			stack.push(type);
		}

		@Override
		public void initObject(Action action) {
			Map<String, Object> members = new HashMap<>();
			int size = (int) stack.pop();
			for (int i = 0; i < size; i++) {
				Object data = stack.pop();
				String name = stack.pop().toString();
				members.put(name, data);
			}
			putPath();
			stack.push(members);
		}

		@Override
		public void initArray(Action action) {
			int size = (int) stack.pop();
			Object[] array = new Object[size];
			for(int i = 0 ; i < size ; i++)
				array[i] = stack.pop();
			stack.push(array);
		}
	}

	public static Map<String, Object> extractVariable(InputStream in) throws IOException {
		TagDecoder t = new TagDecoder(in);
		SwfVariableExtractor extractor = new SwfVariableExtractor();
		t.parse(extractor);
		return extractor.getVariables();
	}
}
