package org.zstack.utils.string;

/**
 * Created by mingjian.deng on 2018/11/28.
 */
public class ErrorCodeElaboration {
    private String category;
    private String code;
    private String regex;
    private String message_cn;
    private String message_en;
    private String source = "zstack";
    private double distance = 0;
    private ElaborationSearchMethod method;
    private String formatSrcError;

    public ErrorCodeElaboration() {
    }

    public ErrorCodeElaboration(String code, String en, String cn, double distance, ElaborationSearchMethod method, Object...args) {
        if (args != null) {
            this.message_en = String.format(en, args);
            this.message_cn = String.format(cn, args);
        } else {
            this.message_en = en;
            this.message_cn = cn;
        }
        this.distance = distance;
        this.method = method;
        this.code = code;
    }

    public ErrorCodeElaboration(ErrorCodeElaboration other) {
        category = other.category;
        code = other.code;
        regex = other.regex;
        message_en = other.message_en;
        message_cn = other.message_cn;
        source = other.source;
        distance = other.distance;
        formatSrcError = other.formatSrcError;
        method = other.method;
    }

    public ErrorCodeElaboration addElaborationMessage(ErrorCodeElaboration other) {
        if (message_en != null) {
            message_en = message_en + "," + other.message_en;
        } else {
            message_en = other.message_en;
        }

        if (message_cn != null) {
            message_cn = message_cn + "," + other.message_cn;
        } else {
            message_cn = other.message_cn;
        }
        return this;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getMessage_cn() {
        return message_cn;
    }

    public void setMessage_cn(String message_cn) {
        this.message_cn = message_cn;
    }

    public String getMessage_en() {
        return message_en;
    }

    public void setMessage_en(String message_en) {
        this.message_en = message_en;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getFormatSrcError() {
        return formatSrcError;
    }

    public void setFormatSrcError(String formatSrcError) {
        this.formatSrcError = formatSrcError;
    }

    public ElaborationSearchMethod getMethod() {
        return method;
    }

    public void setMethod(ElaborationSearchMethod method) {
        this.method = method;
    }
}
