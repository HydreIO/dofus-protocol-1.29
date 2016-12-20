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

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import flash.swf.*;
import flash.swf.actions.ConstantPool;
import flash.swf.actions.Push;
import flash.swf.tags.DoAction;

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
		private LinkedList<Object> stack = new LinkedList<>();
		private String path = "";
		private String[] pool;

		@Override
		public void push(Push action) {
			if (pool != null && action.value instanceof Short)
				stack.add(pool[(short) action.value]);
			else
				stack.add(action.value);
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
			path += "." + stack.removeLast();
		}

		@Override
		public void setMember(Action action) {
			putVariable();
		}

		@Override
		public void setVariable(Action action) {
			putVariable();
		}

		private void putVariable() {
			if (path.isEmpty()) {
				Object value = stack.removeLast();
				variables.put(String.valueOf(stack.removeLast()), value);
			} else {
				variables.put(path.substring(1), stack.removeLast());
				path = "";
			}
		}

		@Override
		public void initObject(Action action) {
			Map<String, Object> members = new HashMap<>();
			path += "." + stack.removeFirst().toString();
			int size = stack.size();
			for (int i = 0; i < size / 2; i++) {
				String name = stack.removeFirst().toString();
				Object data = stack.removeFirst();
				members.put(name, data);
			}
			stack.clear();
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
