package com.lovejjfg.powerrecycle;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Joe on 2016-07-26
 * Email: lovejjfg@gmail.com
 */
public interface AdapterLoader<T> {

    @IntDef(flag = true, value = {
            SingleMode,
            MultipleMode
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface ChoiceMode {
    }

    /**
     * state about load more..
     */
    int STATE_LOADING = 1;
    int STATE_LASTED = 2;
    int STATE_ERROR = 3;

    int TYPE_BOTTOM = 400;

    int SingleMode = 1;
    int MultipleMode = 2;

    /**
     * This method should be called  when you load more !
     *
     * @param holder    the current holder.
     * @param loadState the current state.
     */

    void onBottomViewHolderBind(RecyclerView.ViewHolder holder, int loadState);

    /**
     * If you want to create the specified bottom layout,you must call this method to add your specified layout !
     *
     * @param view the specified bottom layout
     */
    void setLoadMoreView(View view);

    /**
     * If you want to create the specified bottom layout ,you should implements this method to create your own bottomViewHolder
     *
     * @param loadMore whether is loadingMore or not..
     */
    RecyclerView.ViewHolder onBottomViewHolderCreate(View loadMore);

    void onRefresh();

    boolean isHasMore();

    void isLoadingMore();

    void loadMoreError();

    /**
     * You can call this method to add data to RecycleView,if you want to append data,you should call {@link #appendList(List)}
     *
     * @param data the data you want to add
     */
    void setList(List<T> data);

    /**
     * @param data the data you want to add
     */
    void appendList(List<T> data);

    /**
     * @param position the current pos .
     * @return the current Type.
     */
    int getItemViewTypes(int position);

    /**
     * @param holder   current holder.
     * @param position current pos.
     */
    void onViewHolderBind(RecyclerView.ViewHolder holder, int position);

    RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType);

    /**
     * Return the current size about {@link RefreshRecycleAdapter#list}.
     *
     * @return current list size!
     */
    int getItemRealCount();

    void performClick(View itemView, int position);

    boolean performLongClick(View itemView, int position);

    /**
     * Interface definition for a callback to be invoked when
     * an item in this view has been selected.
     */
    interface OnItemSelectedListener {
        /**
         * <p>Callback method to be invoked when an item in this view has been
         * selected. This callback is invoked only when the newly selected
         * position is different from the previously selected position or if
         * there was no selected item.</p>
         * <p>
         * Impelmenters can call getItemAtPosition(position) if they need to access the
         * data associated with the selected item.
         *
         * @param view     The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param isSelected The state of isSelected
         */
        void onItemSelected(View view, int position, boolean isSelected);

        /**
         * Callback method to be invoked when the selection disappears from this
         * view. The selection can disappear for instance when touch is activated
         * or when the adapter becomes empty.
         */
        void onNothingSelected();
    }

    interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    interface OnItemLongClickListener {
        boolean onItemLongClick(View itemView, int position);
    }


}
