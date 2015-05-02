/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weekendhackers.restaurantmenuapp;

import android.annotation.TargetApi;
import android.test.AndroidTestCase;

import com.weekendhackers.restaurantmenuapp.service.ItemTask;

public class TestItemTask extends AndroidTestCase{
    static final String ADD_MENU_ITEM = "Meat";

    /*
        Students: uncomment testAddLocation after you have written the AddLocation function.
        This test will only run on API level 11 and higher because of a requirement in the
        content provider.
     */
    @TargetApi(11)
    public void testAddItem() {
        // start from a clean state

        ItemTask it = new ItemTask(getContext());
        long itemId = it.addItem("Meat", "", "Crispy Pata", "Deliciously sinful!");

    }
}
