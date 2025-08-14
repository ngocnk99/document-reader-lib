/*
 * MIT License
 *
 * 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ahmadullahpk.alldocumentreader.widgets.tableview.handler;

import androidx.annotation.NonNull;

import com.ahmadullahpk.alldocumentreader.widgets.tableview.TableView;
import com.ahmadullahpk.alldocumentreader.widgets.tableview.preference.Preferences;


public class PreferencesHandler {
    @NonNull
    private final ScrollHandler scrollHandler;
    @NonNull
    private final SelectionHandler selectionHandler;

    public PreferencesHandler(@NonNull TableView tableView) {
        this.scrollHandler = tableView.getScrollHandler();
        this.selectionHandler = tableView.getSelectionHandler();
    }

    @NonNull
    public Preferences savePreferences() {
        Preferences preferences = new Preferences();
        preferences.columnPosition = scrollHandler.getColumnPosition();
        preferences.columnPositionOffset = scrollHandler.getColumnPositionOffset();
        preferences.rowPosition = scrollHandler.getRowPosition();
        preferences.rowPositionOffset = scrollHandler.getRowPositionOffset();
        preferences.selectedColumnPosition = selectionHandler.getSelectedColumnPosition();
        preferences.selectedRowPosition = selectionHandler.getSelectedRowPosition();
        return preferences;
    }

    public void loadPreferences(@NonNull Preferences preferences) {
        scrollHandler.scrollToColumnPosition(preferences.columnPosition, preferences.columnPositionOffset);
        scrollHandler.scrollToRowPosition(preferences.rowPosition, preferences.rowPositionOffset);
        selectionHandler.setSelectedColumnPosition(preferences.selectedColumnPosition);
        selectionHandler.setSelectedRowPosition(preferences.selectedRowPosition);
    }
}
