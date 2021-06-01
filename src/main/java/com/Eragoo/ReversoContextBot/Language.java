package com.Eragoo.ReversoContextBot;

import lombok.Getter;

@Getter
public enum Language {
    EN_RU("english-russian"),
    RU_EN("russian-english"),
    GE_RU("german-russian"),
    RU_GE("russian-german"),
    JP_RU("japanese-russian"),
    RU_JP("russian-japanese");

    private String fullName;
    Language(String fullName) {
        this.fullName = fullName;
    }

    public static boolean isSupported(String lang) {
        try {
            Language.valueOf(lang);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
