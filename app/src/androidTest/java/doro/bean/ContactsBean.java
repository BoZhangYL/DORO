package doro.bean;

import java.util.List;

/**
 * Created by qiang.zhang on 2017/1/12.
 */
public class ContactsBean {
    public List<TypeBean> getNumber_list() {
        return number_list;
    }

    public void setNumber_list(List<TypeBean> number_list) {
        this.number_list = number_list;
    }

    public List<TypeBean> getEmail_list() {
        return email_list;
    }

    public void setEmail_list(List<TypeBean> email_list) {
        this.email_list = email_list;
    }

    private List<TypeBean> email_list;
    //step4
    private String street;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public boolean isEdit_picture() {
        return edit_picture;
    }

    public void setEdit_picture(boolean edit_picture) {
        this.edit_picture = edit_picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail_type() {
        return email_type;
    }

    public void setEmail_type(String email_type) {
        this.email_type = email_type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRing_tone() {
        return ring_tone;
    }

    public void setRing_tone(String ring_tone) {
        this.ring_tone = ring_tone;
    }

    public boolean is_play_ring() {
        return is_play_ring;
    }

    public void setIs_play_ring(boolean is_play_ring) {
        this.is_play_ring = is_play_ring;
    }

    public String getMessage_tome() {
        return message_tome;
    }

    public void setMessage_tome(String message_tome) {
        this.message_tome = message_tome;
    }

    public boolean is_play_message() {
        return is_play_message;
    }

    public void setIs_play_message(boolean is_play_message) {
        this.is_play_message = is_play_message;
    }
    //step2
    private List<TypeBean> number_list;
    //step3
    private String email_type;

    //step1
    private boolean star;
    private boolean edit_picture;
    private String name;
    private String birthday;

    @Override
    public String toString() {
        return "ContactsBean{" +
                "email_list=" + email_list.toString() +
                ", \nstreet='" + street + '\'' +
                ", \nnumber_list=" + number_list.toString() +
                ", \nemail_type='" + email_type + '\'' +
                ", \nstar=" + star +
                ", \nedit_picture=" + edit_picture +
                ", \nname='" + name + '\'' +
                ", \nbirthday='" + birthday + '\'' +
                ", \nnote='" + note + '\'' +
                ", \nring_tone='" + ring_tone + '\'' +
                ", \nis_play_ring=" + is_play_ring +
                ", \nmessage_tome='" + message_tome + '\'' +
                ", \nis_play_message=" + is_play_message +
                '}';
    }

    private String note;
    //step5
    private String ring_tone;
    private boolean is_play_ring;
    private String message_tome;
    private boolean is_play_message;
}
