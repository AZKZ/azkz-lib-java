package com.azkz.lib.csv.replaceable;

import java.util.List;

class ReplaceableCsvRecord {

    /**
     * 読み込み時の1行の文字列
     */
    private String textLineOrigin;
    /**
     * カンマ分割された1行
     */
    private List<String> separatedLineOrigin;
    /**
     * 読み込み時の1行の文字列
     */
    private String textLineReplaced;

    public String getTextLineReplaced() {
        return textLineReplaced;
    }

    public ReplaceableCsvRecord(String textLineOrigin, List<String> separatedLineOrigin) {
        this.textLineOrigin = textLineOrigin;
        this.separatedLineOrigin = separatedLineOrigin;
        this.textLineReplaced = String.valueOf(this.textLineOrigin);
    }

    /**
     * 置換
     * @param targetIndex
     */
    public void replace(int targetIndex) {
        String before = this.separatedLineOrigin.get(targetIndex);
        String after = String.valueOf(before.hashCode());
        this.textLineReplaced = this.textLineReplaced.replace(before,after);
    }
}
