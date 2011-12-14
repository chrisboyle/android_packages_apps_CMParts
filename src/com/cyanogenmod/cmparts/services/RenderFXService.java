/*
 * Copyright (C) 2011 The CyanogenMod Project
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

package com.cyanogenmod.cmparts.services;

import com.cyanogenmod.cmparts.R;
import com.cyanogenmod.cmparts.provider.FlingerPinger;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RenderFXService extends Service {

	public static final String MSG_TAG = "RenderFXService";
    private Notification mNotification;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			FlingerPinger.writeRenderEffect(intent.getIntExtra("widget_render_effect", 1));
		}
		
		mNotification = new Notification(R.drawable.notification_icon, getResources().getString(R.string.notify_render_effect),
                                System.currentTimeMillis());

        startForeground(0, mNotification);
		
		return START_STICKY;
	}
	
	public void onDestroy() {
	    FlingerPinger.writeRenderEffect(0);
		stopForeground(true);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
