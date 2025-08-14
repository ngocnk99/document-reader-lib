

package   com.ahmadullahpk.alldocumentreader.widgets.tableview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import   com.ahmadullahpk.alldocumentreader.widgets.tableview.adapter.AbstractTableAdapter;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.adapter.recyclerview.CellRecyclerView;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.filter.Filter;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.handler.ColumnSortHandler;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.handler.FilterHandler;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.handler.ScrollHandler;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.handler.SelectionHandler;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.handler.VisibilityHandler;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.layoutmanager.CellLayoutManager;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.layoutmanager.ColumnHeaderLayoutManager;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.listener.ITableViewListener;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.listener.scroll.HorizontalRecyclerViewListener;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.listener.scroll.VerticalRecyclerViewListener;
import   com.ahmadullahpk.alldocumentreader.widgets.tableview.sort.SortState;



public interface ITableView {

    void addView(View child, ViewGroup.LayoutParams params);

    boolean hasFixedWidth();

    boolean isIgnoreSelectionColors();

    boolean isShowHorizontalSeparators();

    boolean isShowVerticalSeparators();

    boolean isAllowClickInsideCell();

    boolean isSortable();

    @NonNull
    Context getContext();

    @NonNull
    CellRecyclerView getCellRecyclerView();

    @NonNull
    CellRecyclerView getColumnHeaderRecyclerView();

    @NonNull
    CellRecyclerView getRowHeaderRecyclerView();

    @NonNull
    ColumnHeaderLayoutManager getColumnHeaderLayoutManager();

    @NonNull
    CellLayoutManager getCellLayoutManager();

    @NonNull
    LinearLayoutManager getRowHeaderLayoutManager();

    @NonNull
    HorizontalRecyclerViewListener getHorizontalRecyclerViewListener();

    @NonNull
    VerticalRecyclerViewListener getVerticalRecyclerViewListener();

    @Nullable
    ITableViewListener getTableViewListener();

    @NonNull
    SelectionHandler getSelectionHandler();

    @Nullable
    ColumnSortHandler getColumnSortHandler();

    @NonNull
    VisibilityHandler getVisibilityHandler();

    @NonNull
    DividerItemDecoration getHorizontalItemDecoration();

    @NonNull
    DividerItemDecoration getVerticalItemDecoration();

    @NonNull
    SortState getSortingStatus(int column);

    @Nullable
    SortState getRowHeaderSortingStatus();

    void scrollToColumnPosition(int column);

    void scrollToColumnPosition(int column, int offset);

    void scrollToRowPosition(int row);

    void scrollToRowPosition(int row, int offset);

    void showRow(int row);

    void hideRow(int row);

    boolean isRowVisible(int row);

    void showAllHiddenRows();

    void clearHiddenRowList();

    void showColumn(int column);

    void hideColumn(int column);

    boolean isColumnVisible(int column);

    void showAllHiddenColumns();

    void clearHiddenColumnList();

    int getShadowColor();

    int getSelectedColor();

    int getUnSelectedColor();

    int getSeparatorColor();

    void sortColumn(int columnPosition, @NonNull SortState sortState);

    void sortRowHeader(@NonNull SortState sortState);

    void remeasureColumnWidth(int column);

    int getRowHeaderWidth();

    void setRowHeaderWidth(int rowHeaderWidth);

    boolean getShowCornerView();

    enum CornerViewLocation {
        TOP_LEFT(0),
        TOP_RIGHT(1),
        BOTTOM_LEFT(2),
        BOTTOM_RIGHT(3);
        int id;

        CornerViewLocation(int id) {
            this.id = id;
        }

        static CornerViewLocation fromId(int id) {
            for (CornerViewLocation c : values()) {
                if (c.id == id) return c;
            }
            // If enum not found return default of Top Left
            return TOP_LEFT;
        }
    }

    CornerViewLocation getCornerViewLocation();

    void setCornerViewLocation(CornerViewLocation cornerViewLocation);

    int getGravity();

    boolean getReverseLayout();

    void setReverseLayout(boolean reverseLayout);

    @Nullable
    AbstractTableAdapter getAdapter();

    /**
     * Filters the whole table using the provided Filter object which supports multiple filters.
     *
     * @param filter The filter object.
     */
    void filter(@NonNull Filter filter);

    /**
     * Retrieves the FilterHandler of the TableView.
     *
     * @return The FilterHandler of the TableView.
     */
    @Nullable
    FilterHandler getFilterHandler();

    /**
     * Retrieves the ScrollHandler of the TableView.
     *
     * @return The ScrollHandler of the TableView.
     */
    @NonNull
    ScrollHandler getScrollHandler();
}
