package com.example.android.tnt_v2.datasource;

public class ListDataSource {
    private int settings_icon;
    private String settings_head, settings_sub;

    public ListDataSource(int settings_icon, String settings_head, String settings_sub){
        this.setSettings_icon(settings_icon);
        this.setSettings_head(settings_head);
        this.setSettings_sub(settings_sub);
    }

    public int getSettings_icon() {
        return settings_icon;
    }

    public void setSettings_icon(int settings_icon) {
        this.settings_icon = settings_icon;
    }

    public String getSettings_head() {
        return settings_head;
    }

    public void setSettings_head(String settings_head) {
        this.settings_head = settings_head;
    }

    public String getSettings_sub() {
        return settings_sub;
    }

    public void setSettings_sub(String settings_sub) {
        this.settings_sub = settings_sub;
    }
}
