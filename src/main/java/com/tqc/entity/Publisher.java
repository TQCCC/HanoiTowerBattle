package com.tqc.entity;

import java.util.Observable;

/**
 * 消息模式中的发布者
 */
public class Publisher extends Observable {

	@Override
	public void notifyObservers(Object arg) {
		if (!hasChanged()) {
			setChanged();
		}
		super.notifyObservers(arg);
	}

}
