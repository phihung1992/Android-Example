package com.hungnp3.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.hungnp3.common.ISimpleCalcu;

/**
 * Created by HUNG on 11/7/2017.
 */

public class BoundService extends Service {
    private ISimpleCalcu iSimpleCalcu = new ISimpleCalcu.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iSimpleCalcu.asBinder();
    }
}
