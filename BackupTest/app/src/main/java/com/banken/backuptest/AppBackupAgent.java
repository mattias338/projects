package com.banken.backuptest;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;

public class AppBackupAgent extends BackupAgentHelper{
    // A key to uniquely identify the set of backup data
    static final String FILES_BACKUP_KEY = "myfiles";

    // Allocate a helper and add it to the backup agent
    public void onCreate() {
        FileBackupHelper helper = new FileBackupHelper(this, BackupTestActivity.FILENAME);
        addHelper(FILES_BACKUP_KEY, helper);
    }
}