package com.example.maplist.util;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
/**
 * Data binding component for activities.
 */
public class LifecycleComponent
		implements android.databinding.DataBindingComponent, LifecycleObserver {
	private final Lifecycle lifecycle;

	/**
	 * Activity component constructor.
	 *
	 * @param lifecycle Lifecycle provider to bind the throttling to.
	 */
	public LifecycleComponent(final Lifecycle lifecycle) {
		this.lifecycle = lifecycle;
		this.lifecycle.addObserver(this);
	}


	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	public void dispose() {
		lifecycle.removeObserver(this);
	}
}
