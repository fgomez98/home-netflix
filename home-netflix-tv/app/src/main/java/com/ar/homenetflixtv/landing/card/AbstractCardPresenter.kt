package com.ar.homenetflixtv.landing.card

import android.content.Context
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.Presenter


/**
 * This abstract, generic class will create and manage the
 * ViewHolder and will provide typed Presenter callbacks such that you do not have to perform casts
 * on your own.
 *
 * @param <T> View type for the card.
 */
abstract class AbstractCardPresenter<T : BaseCardView, M> : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = onCreateView(parent.context)
        return Presenter.ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val model = item as M
        onBindViewHolder(model, viewHolder!!.view as T)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        onUnbindViewHolder(viewHolder!!.view as T)
    }

    fun onUnbindViewHolder(cardView: T) {
        // Nothing to clean up. Override if necessary.
    }

    /**
     * Invoked when a new view is created.
     *
     * @return Returns the newly created view.
     */
    protected abstract fun onCreateView(context: Context): T

    /**
     * Implement this method to update your card's view with the data bound to it.
     *
     * @param model The model containing the data for the card.
     * @param cardView The cardView the card is bound to.
     * @see Presenter#onBindViewHolder(Presenter.ViewHolder, Object)
     */
    abstract fun onBindViewHolder(model: M, cardView: T)
}