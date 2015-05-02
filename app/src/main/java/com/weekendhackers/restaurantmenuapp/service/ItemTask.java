package com.weekendhackers.restaurantmenuapp.service;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.weekendhackers.restaurantmenuapp.data.RestaurantMenuAppContract;

/**
 * Adds, Edits, Deletes, and Bulk inserts Fashion items.
 * Created by vickysy on 3/10/15.
 */
public class ItemTask extends AsyncTask<String, Void, Void> {

    private final String LOG_TAG = ItemTask.class.getSimpleName();

    private final Context mContext;

    private static final String[] ITEM_COLUMNS = {
            RestaurantMenuAppContract.ItemEntry.TABLE_NAME + "." + RestaurantMenuAppContract.ItemEntry._ID,
            RestaurantMenuAppContract.ItemEntry.COLUMN_ITEM_TYPE,
            RestaurantMenuAppContract.ItemEntry.COLUMN_IMG_PATH,
            RestaurantMenuAppContract.ItemEntry.COLUMN_NAME,
            RestaurantMenuAppContract.ItemEntry.COLUMN_DESCRIPTION
    };

    public ItemTask(Context context) {
        mContext = context;
    }

    private boolean DEBUG = true;

    /**
     * Helper method to handle insertion of a new item in the RestaurantMenu database.
     *
     * @param itemType
     * @return the row ID of the added item.
     */
    public long addItem(String itemType, String imagePath, String name, String description) {
        long itemId;
        ContentValues itemValues = new ContentValues();
        itemValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_ITEM_TYPE, itemType);
        itemValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_IMG_PATH, imagePath);
        itemValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_NAME, name);
        itemValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_DESCRIPTION, description);
        Uri insertedUri = mContext.getContentResolver().insert(
                RestaurantMenuAppContract.ItemEntry.CONTENT_URI,
                itemValues
        );
        itemId = ContentUris.parseId(insertedUri);
        return itemId;
    }

    /**
     * Helper method to handle deletion of an item in the RestaurantMenu database.
     *
     * @param itemId
     * @return the rows deleted.
     */
    public int deleteItem(long itemId) {

        String mSelectionClause = RestaurantMenuAppContract.ItemEntry._ID+ " = ?";
        String[] mSelectionArgs = {"" + itemId};

        // Defines a variable to contain the number of rows deleted
        int mRowsDeleted = 0;

        mRowsDeleted = mContext.getContentResolver().delete(
                RestaurantMenuAppContract.ItemEntry.CONTENT_URI,
                mSelectionClause,                       // the column to select on
                mSelectionArgs                          // the value to compare to
        );

        return mRowsDeleted;
    }

    public int deleteItems(long[] itemIds) {
        String mSelectionClause = RestaurantMenuAppContract.ItemEntry._ID+ " IN (";
        String[] mSelectionArgs = new String[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            mSelectionClause += "?";
            if (i != itemIds.length -1) {
                mSelectionClause += ",";
            }
            mSelectionArgs[i] = "" + itemIds[i];
        }
        mSelectionClause += ")";

        // Defines a variable to contain the number of rows deleted
        int mRowsDeleted = 0;

        mRowsDeleted = mContext.getContentResolver().delete(
                RestaurantMenuAppContract.ItemEntry.CONTENT_URI,
                mSelectionClause,                       // the column to select on
                mSelectionArgs                          // the value to compare to
        );

        return mRowsDeleted;
    }

    /**
     * Helper method to handle deletion of an item in the RestaurantMenu database.
     *
     * @param itemId
     * @return the rows deleted.
     */
    public int editItem(long itemId, String itemType,  String imagePath, String brand, String condition, String color, String material) {
        String mSelectionClause = RestaurantMenuAppContract.ItemEntry._ID+ " = ?";
        String[] mSelectionArgs = {"" + itemId};

        int mRowsUpdated = 0;

        // Defines an object to contain the updated values
        ContentValues mUpdateValues = new ContentValues();
        mUpdateValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_ITEM_TYPE, itemType);
        mUpdateValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_IMG_PATH, imagePath);
        mUpdateValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_NAME, brand);
        mUpdateValues.put(RestaurantMenuAppContract.ItemEntry.COLUMN_DESCRIPTION, condition);

        mRowsUpdated = mContext.getContentResolver().update(
                RestaurantMenuAppContract.ItemEntry.CONTENT_URI,
                mUpdateValues,                          // the columns to update
                mSelectionClause,                       // the column to select on
                mSelectionArgs                          // the value to compare to
        );

        return mRowsUpdated;
    }

    /**
     * Gets the items with condition
     * @param itemIds
     * @return
     */
    public Cursor getItems(long[] itemIds) {

        String selectClause =  RestaurantMenuAppContract.ItemEntry.TABLE_NAME+
                "." + RestaurantMenuAppContract.ItemEntry._ID + " IN (";
        String[] selectionArgs = new String[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            selectClause += "?";
            if (i != itemIds.length -1) {
                selectClause += ",";
            }
            selectionArgs[i] = "" + itemIds[i];
        }
        selectClause += ")";

        Cursor cursor = mContext.getContentResolver().query(
                RestaurantMenuAppContract.ItemEntry.CONTENT_URI,
                ITEM_COLUMNS,
                selectClause,
                selectionArgs,
                null
        );

        return cursor;
    }

    @Override
    protected Void doInBackground(String... params) {
        return null;
    }
}
