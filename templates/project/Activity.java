/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package __ID__;

import android.os.Bundle;

import org.apache.cordova.*;

public class __ACTIVITY__ extends CordovaActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
        checkClonner();
    }

    int APP_PACKAGE_COUNT = 2;
    private void checkClonner() {
        String path = this.getFilesDir().getAbsolutePath();
        int count = getDotCount(path);
        if (count > APP_PACKAGE_COUNT) {
            throw new RuntimeException("This app does not work in a cloning environment");
        }
    }
    private int getDotCount(String path) {
        int count = 0;
        for (int i = 0; i < path.length(); i++) {
            if (count > APP_PACKAGE_COUNT) {
                break;
            }
            if (path.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    @Override
    public void onBackPressed()
    {
        this.appView.loadUrl("javascript:if (document.webkitExitFullscreen) {document.webkitExitFullscreen();}");
        return;
    }
}
